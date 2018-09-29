package com.rabbitmq.demo.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author guochunyuan
 * @create on  2018-09-29 9:15
 */
@Configuration
public class SenderConf {

    /**
     * 创建对列
     * @return
     */


    @Bean(name="message")
    public Queue queueMessage() {
        return new Queue("topic.message");
    }


    /**
     * 创建对列
     * @return
     */

    @Bean(name="messages")
    public Queue queueMessages() {
        return new Queue("topic.messages");
    }

    /**
     * 注入交换机
     * @return
     */
    @Bean
    public TopicExchange exchange() {
        return new TopicExchange("exchange");
    }

    /**
     * 绑定交换机
     * @param queueMessage
     * @param exchange
     * @return
     */
    @Bean
    Binding bindingExchangeMessage(@Qualifier("message") Queue queueMessage, TopicExchange exchange) {
        return BindingBuilder.bind(queueMessage).to(exchange).with("topic.message");
    }

    /**
     * 绑定交换机
     * @param queueMessages
     * @param exchange
     * @return
     */
    @Bean
    Binding bindingExchangeMessages(@Qualifier("messages") Queue queueMessages, TopicExchange exchange) {
        return BindingBuilder.bind(queueMessages).to(exchange).with("topic.#");//*表示一个词,#表示零个或多个词
    }
}
