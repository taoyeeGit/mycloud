package com.taoyee.cloud.controller;

import cn.hutool.core.util.IdUtil;
import com.taoyee.cloud.service.ZfHystrixService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
public class ZfHystrixController {
    @Value("${server.port}")
    private String serverPort;
    @Resource
    private ZfHystrixService zfHystrixService;

    ////////////////////////  服务降级  //////////////////////////
    @GetMapping(value="/zf/hystrix/info_Ok/{id}")
    public String zfHystrixInfo_Ok(@PathVariable("id")  Integer id){
        String result=zfHystrixService.zfHystrixInfo_Ok(id);
        log.info("***服务降级**result:  "+result);
        return result;
    }

    @GetMapping(value="/zf/hystrix/timeout/{id}")
    public String zfHystrixInfo_timeout(@PathVariable ("id") Integer id){
        String result=zfHystrixService.zfHystrixInfo_timeout(id);
        log.info("***服务降级**result:  "+result);
        return result;
    }


    ////////////////////////  服务熔断  //////////////////////////
    @GetMapping(value="/zf/circuitBreaker/{id}")
    public String zfCircuitBreaker(@PathVariable ("id") Integer id){
        String result=zfHystrixService.zfCircuitBreaker(id);
        log.info("**服务熔断***result:  "+result);
        return result;
    }
}
