package com.matthewschuette.javaorders.Controllers;

import com.matthewschuette.javaorders.Models.Orders;
import com.matthewschuette.javaorders.Services.OrderServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/orders")
public class OrdersController {
    @Autowired
    OrderServices orderService;


    // http://localhost:2019/orders/order/{id}
    @GetMapping(value = "/order/{id}", produces = {"application/json"})
    public ResponseEntity<?> getOrderByNumber(@PathVariable long id)
    {
        Orders o = orderService.findOrdersById(id);
        return new ResponseEntity<>(o, HttpStatus.OK);
    }
}
