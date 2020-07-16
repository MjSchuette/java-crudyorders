package com.matthewschuette.javaorders.Services;

import com.matthewschuette.javaorders.Models.Payments;
import com.matthewschuette.javaorders.Repositories.OrdersRepo;
import com.matthewschuette.javaorders.Repositories.PaymentsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Transactional
@Service(value = "paymentService")
public class PaymentServicesImpl implements PaymentServices {
    @Autowired
    private PaymentsRepo paymentRepos;

    @Override
    public Payments save(Payments payments) {
        return paymentRepos.save(payments);
    }
}
