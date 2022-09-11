package com.taoyee.cloud;

import com.taoyee.myrule.MyRibbonRule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;

@SpringBootApplication
@EnableEurekaClient
//改变负载均衡算法为随机RandomRule，ribbon默认为轮询算法RoundRobinRule,不加此行代码即为默认算法
//@RibbonClient(name = "欲调用的服务的名称",configuration = 欲改变算法的类.class)
@RibbonClient(name = "CLOUD-REST-SCZ-PAYMENT-SERVICE",configuration = MyRibbonRule.class)

public class XfzMain8000 {
    public static void main(String[] args) {
        SpringApplication.run(XfzMain8000.class,args);
        System.out.println("Eureka Client 消费者 8000 ==单机版  is started ===>>>");
    }
}