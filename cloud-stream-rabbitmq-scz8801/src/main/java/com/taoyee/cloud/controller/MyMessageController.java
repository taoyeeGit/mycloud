package com.taoyee.cloud.controller;


import com.taoyee.cloud.service.MessageProdImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
public class MyMessageController {
    @Resource
    private MessageProdImpl messageProdImpl;

    @GetMapping(value ="sendmsg")
    public String sendMessage(){
        return messageProdImpl.sendMessage();
    }

}
