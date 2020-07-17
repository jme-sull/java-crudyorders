package local.jmesull.orders.repositories;

import local.jmesull.orders.models.Customer;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<Customer, Long>
{
    Iterable<Customer> findAllByCustnameContains(String string);

}
