package com.jriver.annotion;

import java.lang.annotation.*;

/**
 * 自定义扫描注解
 * Created by wujianjiang on 2017-12-7.
 */
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CustScanBean {
}
