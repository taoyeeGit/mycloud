package com.taoyee.cloud.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RefreshScope
public class ConfigClientController {
    @Value("${config.info}")
    private String configInfo;
    @GetMapping(value="/config/client3366/getInfo")
    public String getConfigInfo(){
        log.info("通过服务配置中心3344获取到github的配置信息为：" +configInfo);
        return configInfo;
    }
}
