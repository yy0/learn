package com.jriver.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

/**
 * Created by wujianjiang on 2017-12-1.
 */
@Component
public class BaseService {

    protected Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    protected RedisTemplate redisTemplate;
}
