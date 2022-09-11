package com.taoyee.cloud.controller;

import com.taoyee.cloud.entities.CommonResult;
import com.taoyee.cloud.entities.Payment;
import com.taoyee.cloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@RestController
@Slf4j
public class SczPaymentController {
    @Resource
    private PaymentService paymentService;
    @Value("${server.port}")
    private String serverPort;
    //@PostMapping(value = "payment/create")
    @RequestMapping(value = "payment/create" ,method=RequestMethod.POST)
    public CommonResult create(@RequestBody Payment payment){
        System.out.println("添加开始===>>>");
        int result=this.paymentService.create(payment);
        if (result>0){
            System.out.println("添加结束===>>>");
            return new CommonResult(200,"插入成功！  "+serverPort,result);
        }else{
            return new CommonResult(400,"插入失败！  "+serverPort, null);
        }

    }
    @GetMapping(value = "payment/get/{id}")
    public CommonResult getPaymentById(@PathVariable("id") Long id){
        System.out.println("查询开始===>>>");
        Payment payment=this.paymentService.getPaymentById(id);
        if(!"".equals(payment) && payment!=null){
            System.out.println("查询结束===>>>");
            return new CommonResult(200,"查询成功！  "+serverPort+"  数据内容为：",payment);
        }else{
            return new CommonResult(400,"没查到符合条件的数据！  "+serverPort+"  查询id为："+id,payment);
        }
    }
    @GetMapping(value="/payment/openfeign/timeout")
    public String feignTimeout(){
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return serverPort;
    }

    @GetMapping(value="/zf/zipkin")
    public String zipkin(){
        return "我是zipkin,我的端口号为： "+serverPort;
    }
}
