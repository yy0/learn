package com.jriver.rabbitmq;

import org.springframework.amqp.core.*;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 自动装配
 */
@Configuration
@ConfigurationProperties(prefix="mq")
public class RabbitConfig {

    private String directQueue;

    @Bean
    public Queue getQueue() {
        return new Queue(directQueue);
    }

    public String getDirectQueue() {
        return directQueue;
    }

    public void setDirectQueue(String directQueue) {
        this.directQueue = directQueue;
    }

    /************************************** topic exchange *************************************/
    final static String topicQueue1 = "topic.message";
    final static String topicQueue2 = "topic.message2";

    final static String routingKey1 = "topic.message";
    final static String routingKey2 = "topic.#";

    final static String topicExchange = "topicExchange";

    @Bean
    public Queue topicQueue1() {
        return new Queue(topicQueue1);
    }

    @Bean
    public Queue topicQueue2() {
        return new Queue(topicQueue2);
    }

    @Bean
    TopicExchange topicExchange() {
        return new TopicExchange(topicExchange);
    }

    @Bean
    Binding bindingExchangeMessage(Queue topicQueue1, TopicExchange exchange) {
        return BindingBuilder.bind(topicQueue1).to(exchange).with(routingKey1);
    }

    @Bean
    Binding bindingExchangeMessages(Queue topicQueue2, TopicExchange exchange) {
        return BindingBuilder.bind(topicQueue2).to(exchange).with(routingKey2);
    }

    /************************************** fanout exchange *************************************/
    final static String fanoutExchange = "fanoutExchange";

    @Bean
    public Queue fanoutQueue1() {
        return new Queue("fanout.A");
    }

    @Bean
    public Queue fanoutQueue2() {
        return new Queue("fanout.B");
    }

    @Bean
    public Queue fanoutQueue3() {
        return new Queue("fanout.C");
    }

    @Bean
    FanoutExchange fanoutExchange() {
        return new FanoutExchange(fanoutExchange);
    }

    @Bean
    Binding bindingFanoutExchange1(Queue fanoutQueue1, FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(fanoutQueue1).to(fanoutExchange);
    }

    @Bean
    Binding bindingFanoutExchange2(Queue fanoutQueue2, FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(fanoutQueue2).to(fanoutExchange);
    }
}