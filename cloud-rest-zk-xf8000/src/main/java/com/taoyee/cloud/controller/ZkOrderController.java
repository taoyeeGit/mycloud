package com.taoyee.cloud.controller;

import com.taoyee.cloud.entities.CommonResult;
import com.taoyee.cloud.entities.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@Slf4j
public class ZkOrderController {
    @Autowired
    private RestTemplate restTemplate;
    //public static  final  String SCZ_URL="http://localhost:8001";//单机版
    public static  final  String SCZ_URL="http://cloud-zk-payment-service/";//集群版
    @GetMapping(value="xfz/zk/payment/create")
    public CommonResult<Payment> create(Payment payment){
        log.info("消费者添加订单");
        return    restTemplate.postForObject(SCZ_URL+"zk/payment/create",payment, CommonResult.class);
    }
    @GetMapping(value="xfz/zk/payment/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id){
        log.info("消费者查询订单");
        return restTemplate.getForObject(SCZ_URL+"zk/payment/get/"+id,CommonResult.class);
    }

}
