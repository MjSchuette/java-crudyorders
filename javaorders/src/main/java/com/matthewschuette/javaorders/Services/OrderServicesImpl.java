package com.matthewschuette.javaorders.Services;

import com.matthewschuette.javaorders.Models.Customers;
import com.matthewschuette.javaorders.Models.Orders;
import com.matthewschuette.javaorders.Models.Payments;
import com.matthewschuette.javaorders.Repositories.OrdersRepo;
import com.matthewschuette.javaorders.Repositories.PaymentsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

@Transactional
@Service(value = "orderService")
public class OrderServicesImpl implements OrderServices{
    @Autowired
    private OrdersRepo ordersRepos;
    @Autowired
    private PaymentsRepo paymentsRepos;

    @Transactional
    @Override
    public Orders save(Orders orders) {
        Orders newOrders = new Orders();

        if (orders.getOrdnum() != 0){
            ordersRepos.findById(orders.getOrdnum())
                    .orElseThrow(() -> new EntityNotFoundException("Customer " + orders.getOrdnum() + " Not Found"));
            newOrders.setOrdnum(orders.getOrdnum());
        }

        newOrders.setOrdamount(orders.getOrdamount());
        newOrders.setAdvanceamount(orders.getAdvanceamount());
        newOrders.setOrderdescription(orders.getOrderdescription());

        // many to one

        newOrders.setCustomer(orders.getCustomer());

        //many to many
        newOrders.getPayments().clear();
        for (Payments p : orders.getPayments()) {
            Payments newPayment = paymentsRepos.findById(p.getPaymentid())
                    .orElseThrow(() -> new EntityNotFoundException("Payment " + p.getPaymentid() + " Not Found"));
            newOrders.getPayments().add(newPayment);
        }

        return ordersRepos.save(newOrders);
    }

    @Override
    public Orders findOrdersById(long id) throws EntityNotFoundException {
        return ordersRepos.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Order " + id + "Not Found"));
    }

    @Transactional
    @Override
    public void delete(long id) {
        ordersRepos.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Order " + id + " Not Found"));
        ordersRepos.deleteById(id);
    }
}
