package com.taoyee.cloud;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@Slf4j
@EnableDiscoveryClient
public class NacosXfMain9000 {
    public static void main(String[] args) {
        SpringApplication.run(NacosXfMain9000.class,args);
        log.info("alibaba Nacos  client 消费者xfService 9000 is started ===>>>");
    }
}
