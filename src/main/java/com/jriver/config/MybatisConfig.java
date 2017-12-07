package com.jriver.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * Created by wujianjiang on 2017-12-7.
 */
@Configuration
@MapperScan(basePackages = "com.jriver.dao", annotationClass = org.springframework.stereotype.Repository.class)
public class MybatisConfig {
}
