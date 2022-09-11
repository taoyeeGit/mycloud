package com.taoyee.cloud.controller;

import com.taoyee.cloud.entities.CommonResult;
import com.taoyee.cloud.service.ZfOpenfeignService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
public  class XfOpenfeignController {
    @Resource
    private ZfOpenfeignService zfOpenfeignService;

    @GetMapping(value = "zf/openfeign/payment/get/{id}")
    public CommonResult getPaymentById(@PathVariable("id") Long id){
        return  zfOpenfeignService.getPaymentById(id);
    }

    @GetMapping(value="/xfz/openfeign/timeout")
    public String feignTimeout(){
        return zfOpenfeignService.feignTimeout();
    }
}

