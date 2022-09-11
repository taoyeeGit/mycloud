package com.taoyee.cloud.service;

import com.taoyee.cloud.entities.CommonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient(value="CLOUD-REST-SCZ-PAYMENT-SERVICE")
public interface ZfOpenfeignService {
    @GetMapping(value = "payment/get/{id}")
    public CommonResult getPaymentById(@PathVariable("id") Long id);

    @GetMapping(value="/payment/openfeign/timeout")
    public String feignTimeout();
}
