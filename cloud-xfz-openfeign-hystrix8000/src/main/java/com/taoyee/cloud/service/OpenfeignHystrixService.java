package com.taoyee.cloud.service;

import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component

@FeignClient(value="CLOUD-SCZ-HYSTRIX-SERVICE-8001" ,fallback = OpenfeignHystrixImpService.class)
public interface OpenfeignHystrixService {

    @GetMapping(value="/zf/hystrix/info_Ok/{id}")
    public String zfHystrixInfo_Ok(@PathVariable("id")  Integer id);

    @GetMapping(value="/zf/hystrix/timeout/{id}")
    public String zfHystrixInfo_timeout(@PathVariable ("id") Integer id);
}
