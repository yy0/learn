package com.jriver.disruptor;

import com.lmax.disruptor.*;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;

import java.util.concurrent.ThreadFactory;

/**
 * BlockingWaitStrategy 是最低效的策略，但其对CPU的消耗最小并且在各种不同部署环境中能提供更加一致的性能表现；
 * SleepingWaitStrategy 的性能表现跟 BlockingWaitStrategy 差不多，对 CPU 的消耗也类似，但其对生产者线程的影响最小，适合用于异步日志类似的场景；
 * YieldingWaitStrategy 的性能是最好的，适合用于低延迟的系统。在要求极高性能且事件处理线数小于 CPU 逻辑核心数的场景中，推荐使用此策略；例如，CPU开启超线程的特性。
 * Created by wujianjiang on 2017-11-27.
 */
public class Bootstrap {

    public static void main(String[] args) {
        EventFactory<DisruptorEvent> eventFactory = new CommonEventFactory();
        int ringBufferSize = 1024 * 1024; // RingBuffer 大小，必须是 2 的 N 次方；
        ThreadFactory threadFactory = r -> new Thread();
        Disruptor<DisruptorEvent> disruptor = new Disruptor<>(eventFactory, ringBufferSize, threadFactory, ProducerType.SINGLE, new YieldingWaitStrategy());
        EventHandler<DisruptorEvent> eventHandler = new PrintEventHandler();
        disruptor.handleEventsWith(eventHandler);
        disruptor.start();


        /*RingBuffer<DisruptorEvent> ringBuffer = disruptor.getRingBuffer();
        long sequence = ringBuffer.next();//请求下一个事件序号；

        try {
            DisruptorEvent DisruptorEvent = ringBuffer.get(sequence);//获取该序号对应的事件对象；
            long data = 1L;//获取要通过事件传递的业务数据；
            DisruptorEvent.set(data);
        } finally{
            ringBuffer.publish(sequence);//发布事件；
        }*/
        publishEvent2(disruptor);
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
        public void translateTo(DisruptorEvent DisruptorEvent, long sequence, Long data) {
            DisruptorEvent.set(data);
        }
    }
}