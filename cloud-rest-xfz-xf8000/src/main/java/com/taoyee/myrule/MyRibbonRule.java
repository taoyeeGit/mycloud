package com.taoyee.myrule;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import com.netflix.loadbalancer.RoundRobinRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
//自定义负载均衡算法
public class MyRibbonRule {
    @Bean
    public IRule myrule(){
        return new RandomRule();//随机，默认为轮询RoundRobinRule
    }
}
