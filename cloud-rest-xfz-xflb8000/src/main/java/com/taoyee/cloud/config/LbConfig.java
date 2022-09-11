package com.taoyee.cloud.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.availability.ReadinessState;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class LbConfig {
    @Bean
    //@LoadBalanced,注掉用自己的负载均衡算法，否则就用系统自带的算法实现负载均衡
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }
}
