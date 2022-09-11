package com.taoyee.cloud.service;

import com.taoyee.cloud.dao.StorageDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
@Slf4j
public class StorageServiceImpl implements StorageService{
    @Resource
    private StorageDao storageDao;
    @Override
    public void decrease(Long productId, Integer count) {
        log.info("------> storage-service中开始扣减库存");
        storageDao.decrease(productId,count);
        log.info("------> storage-service中开始扣减库存");
    }
}
