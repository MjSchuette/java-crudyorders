package com.matthewschuette.javaorders.Repositories;

import com.matthewschuette.javaorders.Models.Payments;
import org.springframework.data.repository.CrudRepository;

public interface PaymentsRepo extends CrudRepository<Payments, Long> {
}
