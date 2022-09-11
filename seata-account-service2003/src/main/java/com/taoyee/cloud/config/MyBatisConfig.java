package com.taoyee.cloud.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("com.taoyee.cloud.dao")
public class MyBatisConfig {
}
