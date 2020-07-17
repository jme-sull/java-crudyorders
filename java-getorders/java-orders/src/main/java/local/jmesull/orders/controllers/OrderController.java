package local.jmesull.orders.controllers;

import local.jmesull.orders.models.Customer;
import local.jmesull.orders.models.Order;
import local.jmesull.orders.services.OrderServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
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


    //POST http://localhost:2019/orders/order
    @PostMapping(value = "/order", consumes = {"application/json"})
    public ResponseEntity<?> addNewOrder(@Validated @RequestBody Order newOrder)
    {
        newOrder.setOrdnum(0);
        newOrder = orderServices.save(newOrder);

        HttpHeaders responseHeaders = new HttpHeaders();
        URI newOrderURI = ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{ordernum}").buildAndExpand(newOrder.getOrdnum()).toUri();
        responseHeaders.setLocation(newOrderURI);

        return new ResponseEntity<>(newOrder, responseHeaders, HttpStatus.CREATED);
    }


    //PUT http://localhost:2019/order/{ordernum}
    @PutMapping(value = "order/{ordernum}", consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> updateOrder(@Validated @RequestBody Order updateOrder, @PathVariable long ordernum)
    {
        updateOrder.setOrdnum(ordernum);
        orderServices.save(updateOrder);

        return new ResponseEntity<>(updateOrder, HttpStatus.OK);

    }


    //DELETE http://localhost:2019/orders/order/ordernum
    @DeleteMapping(value = "/order/{ordernum}")
    public ResponseEntity<?> deleteOrderById(@PathVariable long ordernum)
    {
        orderServices.deleteOrder(ordernum);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
