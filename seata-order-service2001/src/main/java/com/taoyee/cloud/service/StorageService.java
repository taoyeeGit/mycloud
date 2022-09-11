package com.taoyee.cloud.service;


import com.taoyee.cloud.entities.CommonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value="seata-storage-service")
public interface StorageService {
    @PostMapping(value="storage/decrease")
    CommonResult decrease(@RequestParam("productId")  Long productId , @RequestParam("count")   Integer count);
}
