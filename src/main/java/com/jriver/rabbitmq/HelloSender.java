package com.jriver.rabbitmq;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class HelloSender {

	@Autowired
    private AmqpTemplate rabbitTemplate;

	@Autowired
	private RabbitConfig rabbitConfig;

	public void send() {
		String context = "hello " + new Date();
		System.out.println("Sender : " + context);
		this.rabbitTemplate.convertAndSend(rabbitConfig.getDirectQueue(), context);
	}

}