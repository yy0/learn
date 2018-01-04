package com.jriver.disruptor;

import com.lmax.disruptor.EventFactory;
import com.lmax.disruptor.EventHandler;
import com.lmax.disruptor.WaitStrategy;
import com.lmax.disruptor.YieldingWaitStrategy;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import sun.misc.Contended;

import java.util.concurrent.ThreadFactory;

/**
 * Created by wujianjiang on 2017-11-27.
 */
//@Contended
@Configuration
@ConfigurationProperties(prefix="disruptor")
public class DisruptorConfiguration {

    private int ringBufferSize;

    private String producerType;

    @Bean("disruptor")
    public Disruptor getDisruptor() {
        if (getRingBufferSize() <= 0) {
            // RingBuffer 大小，必须是 2 的 N 次方；
            setRingBufferSize(1024 * 1024);
        }
        if (StringUtils.isBlank(getProducerType())) {
            setProducerType(ProducerType.SINGLE.name());
        }
        ProducerType producerType = ProducerType.valueOf(getProducerType());
        Disruptor disruptor = new Disruptor(getEventFactory(), getRingBufferSize(), getThreadFactory(), producerType, getWaitStrategy());
        disruptor.handleEventsWith(getEventHandler());
        disruptor.start();
        return disruptor;
    }

    @Bean
    @ConditionalOnMissingBean
    public ThreadFactory getThreadFactory() {
        ThreadFactory threadFactory = r -> new Thread("Disruptor_" + System.currentTimeMillis());
        return threadFactory;
    }

    @Bean
    @ConditionalOnMissingBean
    public WaitStrategy getWaitStrategy() {
        WaitStrategy waitStrategy = new YieldingWaitStrategy();
        return waitStrategy;
    }

    @Bean
    @ConditionalOnMissingBean
    public EventFactory getEventFactory() {
        EventFactory eventFactory = new CommonEventFactory();
        return eventFactory;
    }

    @Bean
    @ConditionalOnMissingBean
    public EventHandler getEventHandler() {
        EventHandler eventHandler = new PrintEventHandler();
        return eventHandler;
    }

    public int getRingBufferSize() {
        return ringBufferSize;
    }

    public void setRingBufferSize(int ringBufferSize) {
        this.ringBufferSize = ringBufferSize;
    }

    public String getProducerType() {
        return producerType;
    }

    public void setProducerType(String producerType) {
        this.producerType = producerType;
    }
}
