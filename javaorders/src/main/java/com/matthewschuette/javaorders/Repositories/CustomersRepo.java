package com.matthewschuette.javaorders.Repositories;

import com.matthewschuette.javaorders.Models.Customers;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CustomersRepo extends CrudRepository<Customers, Long> {

    List<Customers> findByCustnameContainingIgnoringCase(String name);
}
