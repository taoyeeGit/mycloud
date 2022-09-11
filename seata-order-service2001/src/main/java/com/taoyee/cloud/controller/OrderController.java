package com.taoyee.cloud.controller;

import com.taoyee.cloud.entities.CommonResult;
import com.taoyee.cloud.entities.Order;
import com.taoyee.cloud.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
public class OrderController {
    @Resource
    private OrderService orderService;
    @RequestMapping(value="/order/create")
    public CommonResult<Order> create(Order order){
        this.orderService.create(order);
        return new CommonResult<>(400,"下单成功！");
    }
}
