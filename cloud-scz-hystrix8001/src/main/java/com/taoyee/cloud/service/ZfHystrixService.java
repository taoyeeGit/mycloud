package com.taoyee.cloud.service;

import cn.hutool.core.util.IdUtil;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class ZfHystrixService {

    ////////////////////////  服务降级   //////////////////////////
    /**
     * 正常的方法
     * @param id
     * @return
     */
    public String zfHystrixInfo_Ok(Integer id){
        return "线程池："+Thread.currentThread().getName()+"  ZfHystrixInfo_Ok，id:"+id+"   哈哈hahha~~~";
    }
    /**
     * 超时的方法
     * @param id
     * @return
     */
    @HystrixCommand(fallbackMethod = "zf_fallback_timeout",
            commandProperties = {
                @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value= "3000")
            }
                   )
    public String zfHystrixInfo_timeout(Integer id){
        //1、程序有异常，直接调用fallbackMethod的方法进行处理
        //int i=10/0;

        //2、程序处理的时间超过设定时间，直接调用fallbackMethod的方法进行处理
       int timeout=2000;
        try {
            TimeUnit.MILLISECONDS.sleep(timeout);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "线程池："+Thread.currentThread().getName()+"  ZfHystrixInfo_timeout，id:"+id+"    哈哈hahha~~~";
    }
    public String zf_fallback_timeout(Integer id){
           String result="线程池："+Thread.currentThread().getName()+"  系统8001忙，请稍后再试...........";
        return result;
    }
    ////////////////////////  服务熔断   //////////////////////////
    @HystrixCommand(fallbackMethod = "fallbackCircuitBreaker"
            ,commandProperties = {
                @HystrixProperty(name = "circuitBreaker.enabled",value= "true")//是否开启断路器
               ,@HystrixProperty(name = "circuitBreaker.requestVolumeThreshold",value= "10")//请求次数
               ,@HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds",value= "10000")//时间窗口期
               ,@HystrixProperty(name = "circuitBreaker.errorThresholdPercentage",value= "60")//失败率达到多少以后熔断
            }
    )
    public String zfCircuitBreaker(Integer id){
        if(id<0){
            throw new RuntimeException("=====ID不能为负数！");
        }
        String  serialNum = IdUtil.simpleUUID();
    return Thread.currentThread().getName()+"\t"+"  调用成功，流水号===="+serialNum;
    }
    public String fallbackCircuitBreaker(Integer id){
        return "ID 不能为负数！请稍后再试......id为： " + id;
    }
}

