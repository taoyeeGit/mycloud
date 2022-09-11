package com.taoyee.cloud.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
@Slf4j
public class NacosXfController {
    @Value("${service-url.nacos-user-service}")
    private String serviceURL;
    @Resource
    private RestTemplate restTemplate;

    @GetMapping("xf/nacos/{id}")
    public String getinfo(@PathVariable("id") Integer id) {
        return restTemplate.getForObject(serviceURL + "/zf/nacos/" + id, String.class);
    }
}
