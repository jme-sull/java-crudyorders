package local.jmesull.orders.services;

import local.jmesull.orders.models.Order;
import local.jmesull.orders.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Transactional
@Service(value = "orderService")
public class OrderServicesImpl implements OrderServices
{
    @Autowired
    OrderRepository orderrepos;

    @Transactional
    @Override
    public Order save(Order order)
    {
        Order newOrder = new Order();

        if (order.getOrdnum() != 0)
        {
            orderrepos.findById(order.getOrdnum())
                .orElseThrow(() -> new EntityNotFoundException("Order " + order.getOrdnum() + " Not Found"));

            newOrder.setOrdnum(order.getOrdnum());

        }
        return orderrepos.save(order);
    }


    @Override
    public Order findById(long id)
    {
        Order o = new Order();
        o = orderrepos.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Order " + id + " Not Found"));
        return o;

    }

    @Override
    public List<Order> findByAdvanceAmount()
    {
        List<Order> orders = new ArrayList<>();
        orderrepos.findAllByAdvanceamountGreaterThan(0.0).iterator().forEachRemaining(orders::add);
        return orders;
    }

    @Transactional
    @Override
    public void deleteOrder(long id)
    {
        orderrepos.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Order " + id + " Not Found!"));
        orderrepos.deleteById(id);
    }
}
