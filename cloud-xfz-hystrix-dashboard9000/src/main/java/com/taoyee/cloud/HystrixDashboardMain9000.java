package com.taoyee.cloud;


import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;


@SpringBootApplication
@Slf4j
@EnableHystrixDashboard
public class HystrixDashboardMain9000 {
    public static void main(String[] args) {
        SpringApplication.run(HystrixDashboardMain9000.class,args);
        log.info("消费者 HystrixDashboard  9000 is started ===>>>");
    }
}
