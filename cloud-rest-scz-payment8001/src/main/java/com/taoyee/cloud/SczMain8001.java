package com.taoyee.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class SczMain8001 {
    public static void main(String[] args) {
        SpringApplication.run(SczMain8001.class,args);
        System.out.println("Eureka Client 生产者 8001 ==集群版  is started ===>>>");
    }
}
