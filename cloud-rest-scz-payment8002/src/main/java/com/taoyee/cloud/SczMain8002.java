package com.taoyee.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class SczMain8002 {
    public static void main(String[] args) {
        SpringApplication.run(SczMain8002.class,args);
        System.out.println("Eureka Client 生产者 8002 ==集群版  is started ===>>>");
    }
}
