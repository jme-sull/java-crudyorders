package local.jmesull.orders.repositories;

import local.jmesull.orders.models.Order;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<Order, Long>
{
    Iterable<Order> findAllByAdvanceamountGreaterThan(double num);

}
