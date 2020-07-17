package com.matthewschuette.javaorders.Services;

import com.matthewschuette.javaorders.Models.Customers;
import com.matthewschuette.javaorders.Models.Orders;
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

    @Transactional
    @Override
    public Customers save(Customers customers) {
        Customers newCustomer = new Customers();

        if (customers.getCustcode() != 0){
            customersRepos.findById(customers.getCustcode())
                    .orElseThrow(() -> new EntityNotFoundException("Customer " + customers.getCustcode() + " Not Found"));
            newCustomer.setCustcode(customers.getCustcode());
        }

        newCustomer.setCustname(customers.getCustname());
        newCustomer.setCustcity(customers.getCustcity());
        newCustomer.setWorkingarea(customers.getWorkingarea());
        newCustomer.setCustcountry(customers.getCustcountry());
        newCustomer.setGrade(customers.getGrade());
        newCustomer.setOpeningamt(customers.getOpeningamt());
        newCustomer.setReceiveamt(customers.getReceiveamt());
        newCustomer.setPaymentamt(customers.getPaymentamt());
        newCustomer.setOutstandingamt(customers.getOutstandingamt());
        newCustomer.setPhone(customers.getPhone());

        newCustomer.getOrders().clear();
        for (Orders o : customers.getOrders()) {
            Orders newOrders = new Orders(o.getOrdamount(), o.getAdvanceamount(), newCustomer, o.getOrderdescription());
            newCustomer.getOrders().add(newOrders);
        }

        newCustomer.setAgent(customers.getAgent());

        return customersRepos.save(newCustomer);
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

    @Transactional
    @Override
    public void delete(long id) {
        customersRepos.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Customer " + id + " Not Found"));
        customersRepos.deleteById(id);
    }

    @Transactional
    @Override
    public Customers update(Customers customers, long id) {

        Customers currentCustomer = customersRepos.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("Customer " + id + " Not Found"));
//            currentCustomer.setCustcode(customers.getCustcode());

            if (customers.getCustname() != null) {
                currentCustomer.setCustname(customers.getCustname());
            }
            if (customers.getCustcity() != null) {
                currentCustomer.setCustcity(customers.getCustcity());
            }
            if (customers.getWorkingarea() != null) {
                currentCustomer.setWorkingarea(customers.getWorkingarea());
            }
            if (customers.getCustcountry() != null) {
                currentCustomer.setCustcountry(customers.getCustcountry());
            }
            if (customers.getGrade() != null) {
                currentCustomer.setGrade(customers.getGrade());
            }
            if (customers.hasValueForOpeningamt) {
                currentCustomer.setOpeningamt(customers.getOpeningamt());
            }
            if (customers.hasValueForReceiveamt) {
                currentCustomer.setReceiveamt(customers.getReceiveamt());
            }
            if (customers.hasValueForPaymentamt) {
                currentCustomer.setPaymentamt(customers.getPaymentamt());
            }
            if (customers.hasValueForOutstandingamt) {
                currentCustomer.setOutstandingamt(customers.getOutstandingamt());
            }
            if (customers.getPhone() != null) {
                currentCustomer.setPhone(customers.getPhone());
            }
        if (customers.getOrders().size() > 0) {
            currentCustomer.getOrders().clear();
            for (Orders o : customers.getOrders()) {
                Orders newOrders = new Orders(o.getOrdamount(), o.getAdvanceamount(), currentCustomer, o.getOrderdescription());
                currentCustomer.getOrders().add(newOrders);
            }
        }
        if (customers.getAgent() != null) {
            currentCustomer.setAgent(customers.getAgent());
        }


        return customersRepos.save(currentCustomer);
    }
}
