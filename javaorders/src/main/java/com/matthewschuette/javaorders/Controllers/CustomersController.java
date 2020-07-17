package com.matthewschuette.javaorders.Controllers;

import com.matthewschuette.javaorders.Models.Customers;
import com.matthewschuette.javaorders.Services.CustomerServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
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

    // DELETE http://localhost:2019/customers/customer/{id}
    @DeleteMapping(value = "/customer/{id}")
    public ResponseEntity<?> deleteCustomerById(@PathVariable long id){
        customerService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // POST http://localhost:2019/customers/customer
    @PostMapping(value = "/customer", consumes = {"application/json"})
    public ResponseEntity<?> addNewCustomer(@Valid @RequestBody Customers newCustomer){
        newCustomer.setCustcode(0);
        newCustomer = customerService.save(newCustomer);

        HttpHeaders responseHeaders = new HttpHeaders();
        URI newCustomerURI = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{custcode}")
                .buildAndExpand(newCustomer.getCustcode())
                .toUri();
        responseHeaders.setLocation(newCustomerURI);

        return new ResponseEntity<>(null, responseHeaders, HttpStatus.CREATED);
    }

    // PUT http://localhost:2019/customers/customer/19
    @PutMapping(value = "/customer/{id}", produces = {"application/json"}, consumes = {"application/json"})
    public ResponseEntity<?> updateCustomer(@Valid @RequestBody Customers updateCustomers, @PathVariable long id) {
        updateCustomers.setCustcode(id);
        updateCustomers = customerService.save(updateCustomers);

        return new ResponseEntity<>(updateCustomers, HttpStatus.OK);
    }

    // PATCH http://localhost:2019/customers/customer/19
    @PatchMapping(value = "/customer/{id}", consumes = {"application/json"})
    public ResponseEntity<?> updatePartCustomer(@RequestBody Customers updateCustomer, @PathVariable long id) {
        customerService.update(updateCustomer, id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
