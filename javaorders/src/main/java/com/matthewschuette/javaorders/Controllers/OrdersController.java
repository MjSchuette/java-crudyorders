package com.matthewschuette.javaorders.Controllers;

import com.matthewschuette.javaorders.Models.Customers;
import com.matthewschuette.javaorders.Models.Orders;
import com.matthewschuette.javaorders.Services.OrderServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping(value = "/orders")
public class OrdersController {
    @Autowired
    OrderServices orderService;


    // http://localhost:2019/orders/order/{id}
    @GetMapping(value = "/order/{id}", produces = {"application/json"})
    public ResponseEntity<?> getOrderByNumber(@PathVariable long id) {
        Orders o = orderService.findOrdersById(id);
        return new ResponseEntity<>(o, HttpStatus.OK);
    }

    // DELETE http://localhost:2019/orders/order/58
    @DeleteMapping(value = "/order/{id}")
    public ResponseEntity<?> deleteOrderById(@PathVariable long id) {
        orderService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // POST http://localhost:2019/orders/order
    @PostMapping(value = "/order", consumes = {"application/json"})
    public ResponseEntity<?> addNewOrder(@Valid @RequestBody Orders newOrder) {
        newOrder.setOrdnum(0);
        newOrder = orderService.save(newOrder);

        HttpHeaders responseHeaders = new HttpHeaders();
        URI newCustomerURI = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{ordnum}")
                .buildAndExpand(newOrder.getOrdnum())
                .toUri();
        responseHeaders.setLocation(newCustomerURI);

        return new ResponseEntity<>(null, responseHeaders, HttpStatus.CREATED);
    }

    // PUT http://localhost:2019/orders/order/63
    @PutMapping(value = "/order/{id}", produces = {"application/json"}, consumes = {"application/json"})
    public ResponseEntity<?> updateOrder(@Valid @RequestBody Orders updateOrder, @PathVariable long id) {
        updateOrder.setOrdnum(id);
        updateOrder = orderService.save(updateOrder);

        return new ResponseEntity<>(updateOrder, HttpStatus.OK);
    }
}
