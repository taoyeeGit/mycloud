package com.taoyee.cloud.service;

import cn.hutool.core.lang.UUID;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@EnableBinding(Source.class)
@Slf4j
@Service
public class MessageProdImpl  implements  IMessageProd{
    @Resource
    private MessageChannel output; //消息发送管道

    @Override
    public String sendMessage() {
        String  uuid= UUID.randomUUID().toString();
        log.info("=============> uuid=  "+uuid);
        output.send(MessageBuilder.withPayload(uuid).build());
        return null;
    }
}
