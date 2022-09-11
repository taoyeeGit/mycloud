package com.taoyee.cloud.controller;

import com.taoyee.cloud.entities.CommonResult;
import com.taoyee.cloud.entities.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@Slf4j
public class ConsulOrderController {
    @Autowired
    private RestTemplate restTemplate;
    public static  final  String XFZ_URL="http://cloud-consul-payment-service/";
    @GetMapping(value="xfz/consul/payment/create")
    public CommonResult<Payment> create(Payment payment){
        log.info("消费者添加订单");
        return    restTemplate.postForObject(XFZ_URL+"consul/payment/create",payment,CommonResult.class);
    }
    @GetMapping(value="xfz/consul/payment/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id){
        log.info("消费者查询订单");
        return restTemplate.getForObject(XFZ_URL+"consul/payment/get/"+id,CommonResult.class);
    }

}
