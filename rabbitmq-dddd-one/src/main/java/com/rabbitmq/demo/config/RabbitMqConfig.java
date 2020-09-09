package com.rabbitmq.demo.config;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author guochunyuan
 * @Description:队列配置，队列的名称，发送者和接受者的名称必须一致，否则接收不到消息
 * @create on  2018-09-28 16:00
 */
@Configuration
public class RabbitMqConfig {

    public static final Logger log = LoggerFactory.getLogger(RabbitTemplate.class);

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {

        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        // 消息发送失败返回到队列中, yml需要配置 publisher-returns: true
        rabbitTemplate.setMandatory(true);
        // 消息返回, yml需要配置 publisher-returns: true
        rabbitTemplate.setReturnCallback(new RabbitTemplate.ReturnCallback() {
            @Override
            public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
                String correlationId = message.getMessageProperties().getCorrelationId();
                log.error("消息：{} 发送失败, 应答码：{} 原因：{} 交换机: {}  路由键: {}", correlationId, replyCode, replyText, exchange, routingKey);
            }
        });

        // 消息确认, yml需要配置 publisher-confirms: true
        rabbitTemplate.setConfirmCallback(new RabbitTemplate.ConfirmCallback() {
            @Override
            public void confirm(CorrelationData correlationData, boolean ack, String cause) {
                if (!ack) {
                    log.error("消息发送到exchange失败,原因: {}", cause);
                }
            }
        });
        return rabbitTemplate;
    }
}
