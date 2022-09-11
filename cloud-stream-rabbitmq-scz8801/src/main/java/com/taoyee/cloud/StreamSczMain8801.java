package com.taoyee.cloud;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
@Slf4j
public class StreamSczMain8801 {
    public static void main(String[] args) {
        SpringApplication.run(StreamSczMain8801.class,args);
        log.info("Stream统一消息之生产者 StreamSczMain8801 is started ===>>>");
    }
}
