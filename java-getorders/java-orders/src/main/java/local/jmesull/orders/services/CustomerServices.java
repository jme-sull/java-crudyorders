package local.jmesull.orders.services;

import local.jmesull.orders.models.Customer;

import java.util.List;

public interface CustomerServices
{
    Customer save(Customer customer);

    List<Customer> findAll();

    Customer findById(long id);

    List<Customer> findBySubString(String string);

    void deleteById(long id);

    Customer update(Customer customer, long id);
}
