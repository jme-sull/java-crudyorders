package local.jmesull.orders.services;

import local.jmesull.orders.models.Order;

import java.util.List;

public interface OrderServices
{
    Order save(Order order);

    Order findById(long id);

    List<Order> findByAdvanceAmount();

    void deleteOrder(long id);
}
