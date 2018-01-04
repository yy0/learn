package com.jriver.disruptor;

import com.lmax.disruptor.EventHandler;

public class PrintEventHandler implements EventHandler<DisruptorEvent> {
    public void onEvent(DisruptorEvent event, long sequence, boolean endOfBatch) {
        System.err.println("======================Event: " + event.toString());
    }
}