package com.taoyee.cloud.service;

import com.taoyee.cloud.dao.OrderDao;
import com.taoyee.cloud.entities.Order;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {
    @Resource
    private OrderDao orderDao;
    @Resource
    private StorageService storageService;
    @Resource
    private AccountService accountService;

    @Override
    @GlobalTransactional(name = "seata-create-order", rollbackFor = Exception.class)
    public void create(Order order) {
        log.info("------->开始创订单<--------");
        orderDao.create(order);
        log.info("---------开始扣库存---------");
        storageService.decrease(order.getProductId(), order.getCount());
        log.info("---------扣库存结束---------");

        log.info("---------开始扣账户余额---------");
        accountService.decrease(order.getUserId(), order.getMoney());
        log.info("---------扣账户余额结束---------");

        log.info("---------开始更新订单状态---------");
        orderDao.update(order.getUserId(), 0);//订单状态：0：创建中; 1：已完结
        log.info("---------更新订单状态结束---------");

        log.info("---------下单成功---------");
    }
}
