package com.taoyee.cloud;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@Slf4j
@EnableDiscoveryClient
public class NacosZfMain9002 {
    public static void main(String[] args) {
        SpringApplication.run(NacosZfMain9002.class,args);
        log.info("alibaba Nacos  client 生产者zfService 9002 is started ===>>>");
    }
}
