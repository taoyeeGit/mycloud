package com.taoyee.cloud.service;


import com.taoyee.cloud.entities.Payment;
import org.springframework.web.bind.annotation.PathVariable;
public interface PaymentService {
    public int create (Payment payment);
    public Payment getPaymentById(@PathVariable("id") Long id);
}
