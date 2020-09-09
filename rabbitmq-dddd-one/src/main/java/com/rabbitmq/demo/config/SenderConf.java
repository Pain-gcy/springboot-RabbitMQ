package com.rabbitmq.demo.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author guochunyuan
 * @create on  2018-09-29 9:15
 */
//@Configuration
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
    public TopicExchange exchanges() {
        return new TopicExchange("exchanges");
    }
    /**
     * 注入交换机
     * @return
     */
    @Bean
    public DirectExchange exchange() {
        return new DirectExchange("exchange");
    }

    /**
     * 绑定交换机
     * @param queueMessage
     * @param exchange
     * @return
     */
    @Bean
    Binding bindingExchangeMessage(@Qualifier("message") Queue queueMessage, @Qualifier("exchange") DirectExchange exchange) {
        return BindingBuilder.bind(queueMessage).to(exchange).with("topic.message");
    }

    /**
     * 绑定交换机
     * @param queueMessages
     * @param exchange
     * @return
     */
    @Bean
    Binding bindingExchangeMessages(@Qualifier("message") Queue queueMessages, @Qualifier("exchanges") TopicExchange exchange) {
        return BindingBuilder.bind(queueMessages).to(exchange).with("topic.#");//*表示一个词,#表示零个或多个词
    }
}
