package com.taoyee.cloud.controller;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.messaging.Message;

import org.springframework.stereotype.Component;

@Component
@Slf4j
@EnableBinding(Sink.class)
public class ReceiveMessageController {
    @Value("${server.port}")
    private String serverPort;

    //@GetMapping(value="receivemsg")
    @StreamListener(Sink.INPUT)
    public  void receiveMessage(Message<String> message ){
       log.info("消费者8802 接受到的消息是："+ message.getPayload()+"端口为："+serverPort);
    }
}
