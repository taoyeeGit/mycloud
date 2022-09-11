package com.taoyee.cloud.dao;

import com.taoyee.cloud.entities.Payment;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.web.bind.annotation.PathVariable;

@Mapper
public interface PaymentDao {
    public  int create(Payment payment);
    public  Payment getPaymentById(@PathVariable("id")  Long id);
}
