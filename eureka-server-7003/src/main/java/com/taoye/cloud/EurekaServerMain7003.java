package com.taoye.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class EurekaServerMain7003 {
    public static void main(String[] args) {
        SpringApplication.run(EurekaServerMain7003.class,args);
        System.out.println("Eureka Server 7003==集群版  is started ==>>>welcome  to  xi'an !!!");
    }
}
