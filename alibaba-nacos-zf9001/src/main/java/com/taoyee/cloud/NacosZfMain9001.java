package com.taoyee.cloud;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
@Slf4j
public class NacosZfMain9001 {
    public static void main(String[] args) {
        SpringApplication.run(NacosZfMain9001.class,args);
        log.info("alibaba Nacos  client 生产者zfService 9001 is started ===>>>");
    }
}
