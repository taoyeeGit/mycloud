package com.taoyee.cloud.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.taoyee.cloud.entities.CommonResult;
import com.taoyee.cloud.entities.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class FlowLimitController {

    @Value("${server.port}")
    private  String serverPort;

    @GetMapping(value = "/testA")
    public String testA(){
        log.info( Thread.currentThread().getName()+"--------->testA");
        return  "TestA------->端口： "+serverPort;
    }
    @GetMapping(value = "/testB")
    public String testB(){
        return  "TestB------->端口： "+serverPort;
    }

    @GetMapping("/byResource")
    @SentinelResource(value = "byResource",blockHandler ="handlerException",fallback ="fallbackException" )
    public CommonResult byResource(){
        log.info("1111111111111");
        return new CommonResult(200,"成功!",new Payment(2020l,"serial001"));
    }
    public CommonResult handlerException(BlockException blockException){
        return new CommonResult(200,blockException.getClass().getCanonicalName()+"\t  服务不可用!");
    }

}
