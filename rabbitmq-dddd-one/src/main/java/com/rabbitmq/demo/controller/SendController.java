package com.rabbitmq.demo.controller;

import com.rabbitmq.demo.beans.User;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @author guochunyuan
 * @create on  2018-09-28 16:04
 */
@Component
public class SendController {

    @Autowired
    private AmqpTemplate amqpTemplate;


    public String send(){
        String content="Date:"+new Date();
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
        amqpTemplate.convertAndSend("exchange","topic.message","hello,rabbit!!!!");
    }


    public  void sendFanout(){
        amqpTemplate.convertAndSend("fanoutExchange","","xixixxixixixiixix,aaaaaaa");
    }

}
