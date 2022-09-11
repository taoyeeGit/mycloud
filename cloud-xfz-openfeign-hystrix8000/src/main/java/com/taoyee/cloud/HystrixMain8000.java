package com.taoyee.cloud;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;

@Slf4j
@SpringBootApplication
@EnableFeignClients
@EnableHystrix
public class HystrixMain8000 {
    public static void main(String[] args) {
        SpringApplication.run(HystrixMain8000.class,args);
        log.info("eureka client 消费者 HystrixMain8000 is started ===>>>");
    }
}
