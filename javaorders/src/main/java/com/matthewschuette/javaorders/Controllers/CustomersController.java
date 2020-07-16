package com.matthewschuette.javaorders.Controllers;

import com.matthewschuette.javaorders.Models.Customers;
import com.matthewschuette.javaorders.Services.CustomerServices;
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
public class CustomersController {
    @Autowired
    private CustomerServices customerService;


    // http://localhost:2019/customers/orders
    @GetMapping(value = "/orders", produces = {"application/json"})
    public ResponseEntity<?> listAllOrders() {
        List<Customers> customerOrders = customerService.findAllCustomerOrders();
        return new ResponseEntity<>(customerOrders, HttpStatus.OK);
    }


    // http://localhost:2019/customers/customer/{id}
    @GetMapping(value = "/customer/{custid}",
            produces = {"application/json"})
    public ResponseEntity<?> getCustomerById(@PathVariable long custid) {
        Customers c = customerService.findCustomersById(custid);
        return new ResponseEntity<>(c,
                HttpStatus.OK);
    }


    //
    @GetMapping(value = "/namelike/{custname}",
            produces = {"application/json"})
    public ResponseEntity<?> findCustomerByName(@PathVariable String custname)
    {
        List<Customers> myCustomerList = customerService.findByCustomerName(custname);
        return new ResponseEntity<>(myCustomerList,
                HttpStatus.OK);
    }
}
