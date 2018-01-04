package com.jriver.disruptor;

/**
 * Created by wujianjiang on 2017-11-27.
 */
public class DisruptorEvent<T> {

    private T data;

    public void set(T data) {
        this.data = data;
    }
}