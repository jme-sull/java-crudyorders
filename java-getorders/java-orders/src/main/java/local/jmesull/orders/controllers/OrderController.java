package local.jmesull.orders.controllers;

import local.jmesull.orders.models.Order;
import local.jmesull.orders.services.OrderServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    //DELETE http://localhost:2019/orders/order/ordernum
    @DeleteMapping(value = "/order/{ordernum}")
    public ResponseEntity<?> deleteOrderById(@PathVariable long ordernum)
    {
        orderServices.deleteOrder(ordernum);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
