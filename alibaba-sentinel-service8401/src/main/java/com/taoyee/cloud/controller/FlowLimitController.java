package com.taoyee.cloud.controller;

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
        return  "TestA------->端口： "+serverPort;
    }
    @GetMapping(value = "/testB")
    public String testB(){
        return  "TestB------->端口： "+serverPort;
    }
}
