package com.taoyee.cloud;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class OpenfeignMain8000 {
    public static void main(String[] args) {
        SpringApplication.run(OpenfeignMain8000.class,args);
        System.out.println("Eureka Client 消费者 8000 ==单机版  is started ===>>>");
    }
}