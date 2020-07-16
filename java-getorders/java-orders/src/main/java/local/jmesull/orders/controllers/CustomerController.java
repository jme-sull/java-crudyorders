package local.jmesull.orders.controllers;

import local.jmesull.orders.models.Customer;
import local.jmesull.orders.models.Order;
import local.jmesull.orders.services.CustomerServices;
import local.jmesull.orders.services.OrderServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.nio.file.Path;
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

    //DELETE http://localhost:2019/customers/customer/{custcode}
    @DeleteMapping(value = "customer/{custcode")
    public ResponseEntity<?> deleteCustomerById(@PathVariable long custcode)
    {
        customerService.deleteById(custcode);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /// POST customers/customer
    // Request Body - JSON Object New Restaurant
    @PostMapping(value = "/customer", consumes = {"application/json"})
    public ResponseEntity<?> addNewCustomer(@Validated @RequestBody Customer newCustomer)
    {
        newCustomer.setCustcode(0);
        newCustomer = customerService.save(newCustomer);

        HttpHeaders responseHeaders = new HttpHeaders();
        URI newCustomerURI = ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{custcode}").buildAndExpand(newCustomer.getCustcode()).toUri();
        responseHeaders.setLocation(newCustomerURI);

        return new ResponseEntity<>(null, responseHeaders, HttpStatus.CREATED);

    }

    //PUT /customers/customer/{custcode}
    //Request body - JSON Object complete Customer - > agent -> orders
    @PutMapping(value = "customer/{custcode}", consumes ="application/json", produces = "application/json")
    public ResponseEntity<?> updateCustomer(@Validated @RequestBody Customer updateCustomer,
                                            @PathVariable long custcode)
    {
        updateCustomer.setCustcode(custcode);
        customerService.save(updateCustomer);

        return new ResponseEntity<>(updateCustomer, HttpStatus.OK);

    }

    //PATCH /customers/customer/{custcode}
    @PatchMapping(value = "/customer/{custcode}")










}
