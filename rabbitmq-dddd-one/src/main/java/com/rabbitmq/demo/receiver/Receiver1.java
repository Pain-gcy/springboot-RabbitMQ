package com.rabbitmq.demo.receiver;

import com.rabbitmq.client.Channel;
import com.rabbitmq.demo.beans.User;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author guochunyuan
 * @create on  2018-09-28 16:07
 */
@Component
public class Receiver1 {

    //    ------对列接收---------
    @RabbitHandler
    @RabbitListener(queues = "gcytest1")
    public void receiver(Message message, Channel channel) throws IOException {
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), true);
        System.out.println("接收到的信息：" + new String(message.getBody()));
    }

    @RabbitHandler
    @RabbitListener(queues = "queue")
    public void receiver1(Message message, Channel channel) throws IOException {
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), true);
        System.out.println("queue接收到的信息：" + new String(message.getBody()));
    }

    //    -------topic 接收 ----------
    @RabbitHandler
    @RabbitListener(queues = "topic.message")   //监听器监听指定的Queue
    public void process1(Message message, Channel channel) throws IOException {
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), true);
        System.out.println("topic.message接收到的信息：" + new String(message.getBody()));
    }

    @RabbitHandler
    @RabbitListener(queues = "topic.messages")    //监听器监听指定的Queue
    public void process2(Message message, Channel channel) throws IOException {
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), true);
        System.out.println("topic.messages接收到的信息：" + new String(message.getBody()));
    }


    //    --------fanout 接收--------
    @RabbitHandler
    @RabbitListener(queues = "fanout.A")
    public void processA(Message message, Channel channel) throws IOException {
        System.out.println("A接收到的信息：" + new String(message.getBody()));
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), true);
    }

    @RabbitHandler
    @RabbitListener(queues = "fanout.B")
    public void processB(Message message, Channel channel) throws IOException {
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), true);
        System.out.println("B接收到的信息：" + new String(message.getBody()));
    }

    @RabbitHandler
    @RabbitListener(queues = "fanout.C")
    public void processC(Message message, Channel channel) throws IOException {
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), true);
        System.out.println("C接收到的信息：" + new String(message.getBody()));
    }

    @RabbitHandler
    @RabbitListener(queues = "fanout.D")
    public void processD(String msg, Message message, Channel channel) throws IOException {
        try {
            System.out.println("fanout.D收到消息：" + msg);
            //TODO 具体业务
//            throw new Exception("test");
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        } catch (Exception e) {
            if (message.getMessageProperties().getRedelivered()) {
                System.out.println("消息已重复处理失败,拒绝再次接收...");
                channel.basicReject(message.getMessageProperties().getDeliveryTag(), false); // 拒绝消息
            } else {
                System.out.println("消息即将再次返回队列处理...");
                channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, true);
            }
        }
    }


}
