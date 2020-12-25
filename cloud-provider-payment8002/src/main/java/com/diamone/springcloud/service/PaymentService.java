package com.diamone.springcloud.service;

import com.diamone.springcloud.entities.Payment;

public interface PaymentService {
    int create(Payment payment);
    Payment getPaymentById(Long id);
}
