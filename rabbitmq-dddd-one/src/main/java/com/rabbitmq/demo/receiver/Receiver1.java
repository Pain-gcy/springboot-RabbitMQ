package com.rabbitmq.demo.receiver;

import com.rabbitmq.demo.beans.User;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author guochunyuan
 * @create on  2018-09-28 16:07
 */
@Component
public class Receiver1 {

//    ------对列接收---------
    @RabbitHandler
    @RabbitListener(queues = "gcytest1")
    public void receiver(String msg){
        System.out.println("接收到的信息："+msg);
    }

    @RabbitHandler
    @RabbitListener(queues = "queue")
    public void receiver1(User user){
        System.out.println("接收到queue的信息："+user);
    }

//    -------topic 接收 ----------

    @RabbitListener(queues="topic.message")    //监听器监听指定的Queue
    public void process1(String str) {
        System.out.println("message:"+str);
    }
    @RabbitListener(queues="topic.messages")    //监听器监听指定的Queue
    public void process2(String str) {
        System.out.println("messages:"+str);
    }


//    --------fanout 接收--------

    @RabbitListener(queues="fanout.A")
    public void processA(String str1) {
        System.out.println("ReceiveA:"+str1);
    }
    @RabbitListener(queues="fanout.B")
    public void processB(String str) {
        System.out.println("ReceiveB:"+str);
    }
    @RabbitListener(queues="fanout.C")
    public void processC(String str) {
        System.out.println("ReceiveC:"+str);
    }
}
