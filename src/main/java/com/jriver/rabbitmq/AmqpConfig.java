package com.jriver.rabbitmq;

import com.jriver.condition.InitCondition;
import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.core.Binding;  
import org.springframework.amqp.core.BindingBuilder;  
import org.springframework.amqp.core.DirectExchange;  
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;  
import org.springframework.amqp.rabbit.connection.ConnectionFactory;  
import org.springframework.amqp.rabbit.core.ChannelAwareMessageListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;  

/**  
 * 手动装配
 *   
 * http://docs.spring.io/spring-amqp/docs/1.4.5.RELEASE/reference/html/  
 *   
 * @author lkl  
 * @version $Id: AmqpConfig.java, v 0.1 2015年11月01日 下午2:05:37 lkl Exp $  
 */  
  
@Configuration
@Conditional(InitCondition.class)
public class AmqpConfig {  
  
    public static final String EXCHANGE   = "spring-boot-exchange";  
    public static final String ROUTINGKEY = "spring-boot-routingKey";  
  
    @Bean  
    public ConnectionFactory connectionFactory() {  
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();  
        connectionFactory.setAddresses("127.0.0.1:5672");  
        connectionFactory.setUsername("guest");  
        connectionFactory.setPassword("guest");  
        connectionFactory.setVirtualHost("/");  
        connectionFactory.setPublisherConfirms(true); //必须要设置
        return connectionFactory;
    }  
  
    @Bean  
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)  
    //必须是prototype类型  
    public RabbitTemplate rabbitTemplate() {  
        RabbitTemplate template = new RabbitTemplate(connectionFactory());
        template.setMessageConverter(new Jackson2JsonMessageConverter());
        return template;  
    }  
  
    /**  
     * 针对消费者配置  
     * 1. 设置交换机类型  
     * 2. 将队列绑定到交换机  
     * FanoutExchange: 将消息分发到所有的绑定队列，无routingkey的概念
     * HeadersExchange ：通过添加属性key-value匹配
     * DirectExchange:按照routingkey分发到指定队列
     * TopicExchange:多关键字匹配
     */
    @Bean  
    public DirectExchange defaultExchange() {  
        return new DirectExchange(EXCHANGE);  
    }  
  
    @Bean  
    public Queue queue() {  
        return new Queue("spring-boot-queue", true); //队列持久  
  
    }  
  
    @Bean  
    public Binding binding() {  
        return BindingBuilder.bind(queue()).to(defaultExchange()).with(AmqpConfig.ROUTINGKEY);  
    }  
  
    @Bean  
    public SimpleMessageListenerContainer messageContainer() {  
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer(connectionFactory());  
        container.setQueues(queue());  
        container.setExposeListenerChannel(true);  
        container.setMaxConcurrentConsumers(1);  
        container.setConcurrentConsumers(1);  
        container.setAcknowledgeMode(AcknowledgeMode.MANUAL); //设置确认模式手工确认
        container.setMessageListener((ChannelAwareMessageListener) (message, channel) -> {
            byte[] body = message.getBody();
            System.out.println("receive msg : " + new String(body));
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false); //确认消息成功消费
        });
        return container;  
    }
  
}  