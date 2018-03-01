package com.jriver;

import com.jriver.disruptor.CommonEventFactory;
import com.jriver.disruptor.DisruptorEvent;
import com.jriver.disruptor.PrintEventHandler;
import com.lmax.disruptor.*;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ImportResource;

import java.util.concurrent.ThreadFactory;

@ImportResource
@SpringBootApplication // same as @Configuration @EnableAutoConfiguration @ComponentScan
public class Application {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(Application.class, args);
        /*new SpringApplicationBuilder().bannerMode(Banner.Mode.CONSOLE).listeners(new ApplicationListener<ApplicationEvent>() {
            @Override
            public void onApplicationEvent(ApplicationEvent applicationEvent) {
                System.out.println("========");
            }
        }).build().run(args);*/
        /*ConditionTest condition = context.getBean("conditionTest", ConditionTest.class);
        System.out.println(condition == null);

        ScanTest scanTest = context.getBean("scanTest", ScanTest.class);
        scanTest.print();*/
        /*Disruptor disruptor = context.getBean("disruptor", Disruptor.class);
        RingBuffer<DisruptorEvent> ringBuffer = disruptor.getRingBuffer();
        long sequence = ringBuffer.next();//请求下一个事件序号；

        for (int i=0; i<10; i++) {
            try {
                DisruptorEvent event = ringBuffer.get(sequence);//获取该序号对应的事件对象；
                long data = i;//获取要通过事件传递的业务数据；
                event.set(data);
            } finally{
                ringBuffer.publish(sequence);//发布事件；
            }
        }*/
        test();
    }

    public static void test() {
        ThreadFactory threadFactory = r -> new Thread("Disruptor_" + System.currentTimeMillis());
        WaitStrategy waitStrategy = new YieldingWaitStrategy();
        EventFactory eventFactory = new CommonEventFactory();
        EventHandler eventHandler = new PrintEventHandler();
        Disruptor disruptor = new Disruptor(eventFactory, 1024, threadFactory, ProducerType.SINGLE, waitStrategy);
        disruptor.handleEventsWith(eventHandler);
        disruptor.start();
        RingBuffer<DisruptorEvent> ringBuffer = disruptor.getRingBuffer();
        long sequence = ringBuffer.next();//请求下一个事件序号；

        for (int i=0; i<10; i++) {
            try {
                DisruptorEvent event = ringBuffer.get(sequence);//获取该序号对应的事件对象；
                long data = i;//获取要通过事件传递的业务数据；
                event.set(data);
            } finally{
                ringBuffer.publish(sequence);//发布事件；
            }
        }
    }

    public static Translator TRANSLATOR = new Translator();

    public static void publishEvent2(Disruptor<DisruptorEvent> disruptor) {
        // 发布事件；
        RingBuffer<DisruptorEvent> ringBuffer = disruptor.getRingBuffer();
        long data = 1L;//获取要通过事件传递的业务数据；
        ringBuffer.publishEvent(TRANSLATOR, data);
    }

    static class Translator implements EventTranslatorOneArg<DisruptorEvent, Long> {
        @Override
        public void translateTo(DisruptorEvent event, long sequence, Long data) {
            event.set(data);
        }
    }

}