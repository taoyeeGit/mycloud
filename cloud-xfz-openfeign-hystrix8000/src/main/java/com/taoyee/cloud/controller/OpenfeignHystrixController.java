package com.taoyee.cloud.controller;

import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.taoyee.cloud.service.OpenfeignHystrixService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Slf4j
@RestController
@DefaultProperties( defaultFallback = "global_fallback" )//全局Hystrix
public class OpenfeignHystrixController {
    @Resource
    private OpenfeignHystrixService openfeignHystrixService;

    @GetMapping(value="/xf/hystrix/info_Ok/{id}")
    public String zfHystrixInfo_Ok(@PathVariable("id")  Integer id){
        return openfeignHystrixService.zfHystrixInfo_Ok(id);
    }

    @GetMapping(value="/xf/hystrix/timeout/{id}")
    /*@HystrixCommand(fallbackMethod = "xf_fallback_timeout",
            commandProperties = {
                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value= "4500")
            }
    )
    //前提：8001超时为3秒，程序运行需要2秒.   而本系统的改业务的超时为1.5秒，故降级用xf_fallback_timeout处理*/
    @HystrixCommand  // 后面不加任何属性参数，若出现异常则调用defaultFallback指定的全局方法。 带了参数，若出现异常，就用自己的fallbackMethod指定的方法
    public String xfHystrixInfo_timeout(@PathVariable ("id") Integer id){

        return openfeignHystrixService.zfHystrixInfo_timeout(id);
    }

    public String xf_fallback_timeout(@PathVariable ("id") Integer id){
        String result="我是消费者8000"+Thread.currentThread().getName()+"  对方8001系统繁忙，请稍后再试...或是本系统维护中...";
        return result;
    }
    public String global_fallback(){

        return "我是消费者全局Hystrix..."+Thread.currentThread().getName()+" ...";
    }
}
