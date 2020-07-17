package local.jmesull.orders.services;

import local.jmesull.orders.models.Customer;
import local.jmesull.orders.models.Order;
import local.jmesull.orders.repositories.CustomerRepository;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Transactional
@Service(value = "customerService")
public class CustomerServicesImpl implements CustomerServices
{
    @Autowired
    private CustomerRepository customerrepos;

    @Transactional
    @Override
    public Customer save(Customer customer)
    {
        Customer newCustomer = new Customer();

        if (customer.getCustcode() != 0)
        {
            customerrepos.findById(customer.getCustcode())
                .orElseThrow(() -> new EntityNotFoundException("Customer " + customer.getCustcode() + " Not Found"));

            newCustomer.setCustcode(customer.getCustcode());
        }

        newCustomer.setCustname(customer.getCustname());
        newCustomer.setCustcity(customer.getCustcity());
        newCustomer.setCustcountry(customer.getCustcountry());
        newCustomer.setWorkingarea(customer.getWorkingarea());
        newCustomer.setGrade(customer.getGrade());
        newCustomer.setOpeningamt(customer.getOpeningamt());
        newCustomer.setOutstandingamt(customer.getOutstandingamt());
        newCustomer.setPaymentamt(customer.getPaymentamt());
        newCustomer.setAgent(customer.getAgent());
        newCustomer.setPhone(customer.getPhone());
        newCustomer.setReceiveamt(customer.getReceiveamt());

        //OneToMany
        newCustomer.getOrders().clear();
        for (Order o: customer.getOrders())
        {
            Order newOrder = new Order(o.getOrdamount(), o.getAdvanceamount(), newCustomer, o.getOrderdescription());
            newCustomer.getOrders().add(newOrder);
        }

        return customerrepos.save(newCustomer);
    }


    @Override
    public List<Customer> findAll()
    {
        List<Customer> list = new ArrayList<>();
        customerrepos.findAll().iterator().forEachRemaining(list::add);
        return list;
    }


    @Override
    public Customer findById(long id)
    {
        Customer c = new Customer();
        c = customerrepos.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Customer " + id + " Not Found"));

        return c;
    }


    @Override
    public List<Customer> findBySubString(String string)
    {
        List<Customer> customers = new ArrayList<>();
        customerrepos.findAllByCustnameContains(string).iterator().forEachRemaining(customers::add);
        return customers;
    }

    @Transactional
    @Override
    public void deleteById(long id)
    {
        customerrepos.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Customer " + id + " Not Found!"));
        customerrepos.deleteById(id);
    }

    @Transactional
    @Override
    public Customer update(
        Customer customer,
        long id)
    {
        Customer currentCustomer = customerrepos.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Customer " + customer.getCustcode() + " Not Found"));

        if (customer.getCustname() != null)
        {
            currentCustomer.setCustname(customer.getCustname());
        }
        if (customer.getCustcity() != null)
        {
            currentCustomer.setCustcity(customer.getCustcity());
        }
        if (customer.getCustcountry() != null)
        {
            currentCustomer.setCustcountry(customer.getCustcountry());
        }
        if (customer.getWorkingarea() != null)
        {
            currentCustomer.setWorkingarea(customer.getWorkingarea());
        }
        if (customer.getGrade() != null)
        {
            currentCustomer.setGrade(customer.getGrade());
        }
        if (customer.hasvalueforopeningamt)
        {
            currentCustomer.setOpeningamt(customer.getOpeningamt());
        }
        if (customer.hasvalueforoutstandingamt)
        {
            currentCustomer.setOutstandingamt(customer.getOutstandingamt());
        }
        if (customer.hasvalueforpaymentamt)
        {
            currentCustomer.setPaymentamt(customer.getPaymentamt());
        }
        if (customer.getAgent() != null)
        {
            currentCustomer.setAgent(customer.getAgent());
        }
        if (customer.getPhone() != null)
        {
            currentCustomer.setPhone(customer.getPhone());
        }

        if (customer.hasvalueforreceiveamt)
        {
            currentCustomer.setReceiveamt(customer.getReceiveamt());
        }

        //OneToMany
        if(customer.getOrders().size() >0)
        {
            currentCustomer.getOrders()
                .clear();
            for (Order o : customer.getOrders())
            {
                Order newOrder = new Order(o.getOrdamount(),
                    o.getAdvanceamount(),
                    currentCustomer,
                    o.getOrderdescription());
                currentCustomer.getOrders()
                    .add(newOrder);
            }
        }

        return customerrepos.save(currentCustomer);

    }
}
