package com.taoyee.cloud.service;

import org.springframework.stereotype.Component;

@Component
public class OpenfeignHystrixImpService implements OpenfeignHystrixService {
    @Override
    public String zfHystrixInfo_Ok(Integer id) {
        return "------OK--------";
    }

    @Override
    public String zfHystrixInfo_timeout(Integer id) {
        return "------timeout------";
    }
}
