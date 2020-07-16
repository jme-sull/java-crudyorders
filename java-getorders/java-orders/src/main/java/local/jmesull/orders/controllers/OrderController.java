package local.jmesull.orders.controllers;

import local.jmesull.orders.models.Order;
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
@RequestMapping(value = "/orders")
public class OrderController
{
    @Autowired
    private OrderServices orderServices;

    //http://localhost:2019/orders/order/{id}
    @GetMapping(value = "/order/{id}", produces = {"application/json"})
    public ResponseEntity<?> listOrderById(@PathVariable long id)
    {
        Order o = orderServices.findById(id);
        return new ResponseEntity<>(o, HttpStatus.OK);
    }

    //http://localhost:2019/orders/advanceamount
    @GetMapping(value = "/advanceamount", produces = {"application/json"})
    public ResponseEntity<?> listOrdersWithAdvanceAmount()
    {
        List <Order> orders = orderServices.findByAdvanceAmount();
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }
}
