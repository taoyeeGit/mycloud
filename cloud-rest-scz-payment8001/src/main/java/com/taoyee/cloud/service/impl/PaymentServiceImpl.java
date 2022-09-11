package com.taoyee.cloud.service.impl;

import com.taoyee.cloud.dao.PaymentDao;
import com.taoyee.cloud.entities.Payment;
import com.taoyee.cloud.service.PaymentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class PaymentServiceImpl implements PaymentService {
    @Resource
    private PaymentDao paymentDao;

    @Override
    public int create(Payment payment) {
        return this.paymentDao.create(payment);
    }
    @Override
    public Payment getPaymentById(Long id) {
        return  this.paymentDao.getPaymentById(id);
    }
}
