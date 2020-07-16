package com.matthewschuette.javaorders.Repositories;

import com.matthewschuette.javaorders.Models.Orders;
import org.springframework.data.repository.CrudRepository;

public interface OrdersRepo extends CrudRepository<Orders, Long> {
}
