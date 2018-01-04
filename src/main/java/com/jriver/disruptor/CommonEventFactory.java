package com.jriver.disruptor;

import com.lmax.disruptor.EventFactory;

/**
 * Created by wujianjiang on 2017-11-27.
 */
public class CommonEventFactory implements EventFactory<DisruptorEvent> {

    @Override
    public DisruptorEvent newInstance() {
        return new DisruptorEvent();
    }
}
