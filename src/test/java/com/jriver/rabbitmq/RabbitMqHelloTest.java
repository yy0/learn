package com.jriver.rabbitmq;

import com.alibaba.boot.dubbo.annotation.DubboConsumer;
import com.alibaba.boot.dubbo.annotation.EnableDubboConfiguration;
import com.jriver.dubbo.DubboConsumerService;
import com.jriver.dubbo.IDubboProviderService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RabbitMqHelloTest {

	@Autowired
	private DubboConsumerService dubboConsumerService;

	@Test
	public void hello() throws Exception {
		dubboConsumerService.print();
	}

}