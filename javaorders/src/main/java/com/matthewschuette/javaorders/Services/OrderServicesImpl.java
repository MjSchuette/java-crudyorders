package com.matthewschuette.javaorders.Services;

import com.matthewschuette.javaorders.Models.Orders;
import com.matthewschuette.javaorders.Repositories.OrdersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

@Transactional
@Service(value = "orderService")
public class OrderServicesImpl implements OrderServices{
    @Autowired
    private OrdersRepo ordersRepos;

    @Override
    public Orders save(Orders orders) {
        return ordersRepos.save(orders);
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
