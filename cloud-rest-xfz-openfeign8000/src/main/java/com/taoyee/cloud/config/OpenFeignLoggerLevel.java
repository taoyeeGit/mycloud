package com.taoyee.cloud.config;

import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenFeignLoggerLevel {
    @Bean
    Logger.Level feignLogLevel(){
        return  Logger.Level.FULL;
    }
}
