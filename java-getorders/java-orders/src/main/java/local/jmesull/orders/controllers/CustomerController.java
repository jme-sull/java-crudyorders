package local.jmesull.orders.controllers;

import local.jmesull.orders.models.Customer;
import local.jmesull.orders.models.Order;
import local.jmesull.orders.services.CustomerServices;
import local.jmesull.orders.services.OrderServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/customers")
public class CustomerController


{
    @Autowired
    private CustomerServices customerService;

    //http://localhost:2019/customers/orders
    @GetMapping(value = "/orders", produces = {"application/json"})
    public ResponseEntity<?> listAllCustomers()
    {
        List<Customer> myCustomers = customerService.findAll();
        return new ResponseEntity<>(myCustomers, HttpStatus.OK);

    }

    //http://localhost:2019/customers/customer/{id}
    @GetMapping(value = "/customer/{custcode}", produces = {"application/json"})
    public ResponseEntity<?> listCustomerById(@PathVariable long custcode)
    {
        Customer a = customerService.findById(custcode);
        return new ResponseEntity<>(a, HttpStatus.OK);
    }

    //http://localhost:2019/customers/namelike/{likename}
    @GetMapping(value = "/namelike/{likename}", produces = {"application/json"})
    public ResponseEntity<?> findCustomerBySubString(@PathVariable String likename)
    {
        List <Customer> c = customerService.findBySubString(likename);
        return new ResponseEntity<>(c, HttpStatus.OK);
    }








}
