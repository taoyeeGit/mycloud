package com.taoyee.cloud;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@Slf4j
@EnableEurekaClient
public class StreamXfzMain8802 {
    public static void main(String[] args) {
        SpringApplication.run(StreamXfzMain8802.class,args);
        log.info("Stream消息 消费者 StreamXfzMain8802  is started ===>>>");
    }
}
