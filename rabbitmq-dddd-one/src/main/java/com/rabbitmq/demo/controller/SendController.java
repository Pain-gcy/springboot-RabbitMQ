package com.rabbitmq.demo.controller;

import com.rabbitmq.demo.beans.User;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;
import java.util.UUID;

/**
 * @author guochunyuan
 * @create on  2018-09-28 16:04
 */
@Component
public class SendController {

    @Resource
    private RabbitTemplate amqpTemplate;


    public String send(){
        String content="Date:"+ new Date();
        amqpTemplate.convertAndSend("gcytest1",content);
        return content;
    }

    public  void sendd(){
        User user=new User();    //实现Serializable接口
        user.setUserName("hlhdidi");
        user.setPwd("123");
        amqpTemplate.convertAndSend("queue",user);
    }


    public void sendTopic(){
        amqpTemplate.convertAndSend("exchanges","topic.message","hello,rabbit!!!!");
    }


    public  void sendFanout(){
        amqpTemplate.convertAndSend("fanoutExchange","","xixixxixixixiixix,aaaaaaa");
    }

}
