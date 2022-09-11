package com.taoyee.cloud.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope
@Slf4j
public class NacosZfController {
    @Value("${server.port}")
    private String serverPort;

    @GetMapping(value = "zf/nacos/{id}")
    public String getConfigInfo(@PathVariable(value = "id") String id) {
        return "服务端口:" + serverPort + "\t   id=" + id;
    }
}

