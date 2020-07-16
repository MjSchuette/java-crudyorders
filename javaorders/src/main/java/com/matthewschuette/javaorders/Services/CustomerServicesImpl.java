package com.matthewschuette.javaorders.Services;

import com.matthewschuette.javaorders.Models.Customers;
import com.matthewschuette.javaorders.Repositories.CustomersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Transactional
@Service(value = "customerService")
public class CustomerServicesImpl implements CustomerServices{
    @Autowired
    private CustomersRepo customersRepos;

    @Override
    public Customers save(Customers customers) {
        return customersRepos.save(customers);
    }

    @Override
    public List<Customers> findAllCustomerOrders() {
        List<Customers> list = new ArrayList<>();

        customersRepos.findAll().iterator().forEachRemaining(list::add);

        return list;
    }

    @Override
    public Customers findCustomersById(long id) throws EntityNotFoundException {
        return customersRepos.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Customer " + id + " Not Found"));
    }

    @Override
    public List<Customers> findByCustomerName(String custname) {
        return customersRepos.findByCustnameContainingIgnoringCase(custname);
    }
}
