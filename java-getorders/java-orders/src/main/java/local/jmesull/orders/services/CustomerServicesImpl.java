package local.jmesull.orders.services;

import local.jmesull.orders.models.Customer;
import local.jmesull.orders.repositories.CustomerRepository;
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
        return customerrepos.save(customer);
    }

    @Transactional
    @Override
    public List<Customer> findAll()
    {
        List<Customer> list = new ArrayList<>();
        customerrepos.findAll().iterator().forEachRemaining(list::add);
        return list;
    }

    @Transactional
    @Override
    public Customer findById(long id)
    {
        Customer c = new Customer();
        c = customerrepos.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Customer " + id + " Not Found"));

        return c;
    }

    @Transactional
    @Override
    public List<Customer> findBySubString(String string)
    {
        List<Customer> customers = new ArrayList<>();
        customerrepos.findAllByCustnameContains(string).iterator().forEachRemaining(customers::add);
        return customers;
    }
}
