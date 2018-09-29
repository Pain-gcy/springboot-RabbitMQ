package com.rabbitmq.demo.config;


import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author guochunyuan
 * @Description:队列配置，队列的名称，发送者和接受者的名称必须一致，否则接收不到消息
 * @create on  2018-09-28 16:00
 */
@Configuration
public class RabbitMqConfig {

    @Bean
    public Queue queue1(){
        return new Queue("gcytest1");
    }
    @Bean
    public Queue queue(){
        return new Queue("queue");
    }
}
