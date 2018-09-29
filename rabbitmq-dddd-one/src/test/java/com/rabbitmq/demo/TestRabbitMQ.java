package com.rabbitmq.demo;

import com.rabbitmq.demo.controller.SendController;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author guochunyuan
 * @create on  2018-09-29 9:01
 */
@SpringBootTest(classes = RabbitApplication.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class TestRabbitMQ {

    @Autowired
    private SendController helloSender;

    @Test
    @Ignore
    public void testRabbit() {
        helloSender.send();
    }

    @Test
    @Ignore
    public void testRabbits() {
        helloSender.sendd();
    }

    @Test
    @Ignore
    public void testRabbitTopic() {
        helloSender.sendTopic();
    }

    @Test
    public void testRabbitFanout() {
        helloSender.sendFanout();
    }

}
