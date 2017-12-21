package com.jriver.rabbitmq;

import com.jriver.condition.InitCondition;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "spring-boot-mq")
@Conditional(InitCondition.class)
public class HelloReceiver {

    @RabbitHandler
    public void process(String hello) {
        System.out.println("Receiver  : " + hello);
    }

}