package com.taoyee.cloud.controller;

import com.taoyee.cloud.dao.StorageDao;
import com.taoyee.cloud.entities.CommonResult;
import com.taoyee.cloud.service.StorageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
public class StorageController {
    @Resource
    private StorageService storageService;

    /**
     *
     * @param productId
     * @param count
     * @return
     */
    @PostMapping (value="storage/decrease")
    public CommonResult decrease(Long productId ,Integer count ){
        log.info("------> StorageController 中开始扣减库存");
        storageService.decrease(productId,count);
        log.info("------> StorageController 中开始扣减库存");
        return new CommonResult(400,"扣库存成功！");
    }
}
