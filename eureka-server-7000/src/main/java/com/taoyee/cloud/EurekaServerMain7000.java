package com.taoyee.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class EurekaServerMain7000 {
    public static void main(String[] args) {
        SpringApplication.run(EurekaServerMain7000.class,args);
        System.out.println("Eureka Server 7000==单机版  is started ==>>>welcome  to  xi'an !!!");
    }
}
