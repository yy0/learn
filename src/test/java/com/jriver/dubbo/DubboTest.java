package com.jriver.dubbo;

import com.jriver.dubbo.DubboConsumerService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DubboTest {

	@Autowired
	private DubboConsumerService dubboConsumerService;

	@Test
	public void printGeneric() throws Exception {
		dubboConsumerService.printGeneric();
	}

	@Test
	public void print() throws Exception {
		dubboConsumerService.print();
	}

	@Test
	public void getDataGeneric() throws Exception {
		dubboConsumerService.getDataGeneric();
	}

	@Test
	public void getData() throws Exception {
		dubboConsumerService.getData();
	}

}