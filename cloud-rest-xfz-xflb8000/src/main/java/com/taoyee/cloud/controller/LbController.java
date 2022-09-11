package com.taoyee.cloud.controller;

import com.taoyee.cloud.entities.CommonResult;
import com.taoyee.cloud.entities.Payment;
import com.taoyee.cloud.mylb.IMyLoadBalanced;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.net.URI;
import java.util.List;

@RestController
@Slf4j
public class LbController {//XfzOrderController
    @Autowired
    private RestTemplate restTemplate;
    //public static  final  String SCZ_URL="http://localhost:8001";//单机版
    public static  final  String SCZ_URL="http://CLOUD-REST-SCZ-PAYMENT-SERVICE/";//集群版
    @GetMapping(value="xfz/payment/create")
    public CommonResult<Payment> create(Payment payment){
        log.info("消费者添加订单");
        return    restTemplate.postForObject(SCZ_URL+"payment/create",payment,CommonResult.class);
    }
    @GetMapping(value="xfz/payment/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id){
        log.info("消费者查询订单");
        return restTemplate.getForObject(SCZ_URL+"payment/get/"+id,CommonResult.class);
    }

    @Resource
    private IMyLoadBalanced myLoadBalanced;

    @Resource
    private DiscoveryClient discoveryClient;

    @GetMapping("xfz/lb/{id}")
    public String getPaymentLB(@PathVariable("id") Long id) {
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-REST-SCZ-PAYMENT-SERVICE");
        if (instances == null || instances.size() == 0) {
            return null;
        }
        ServiceInstance serviceInstance = myLoadBalanced.instance(instances);
        URI uri = serviceInstance.getUri();
        return restTemplate.getForObject(uri + "payment/get/"+id, String.class);
    }
}
