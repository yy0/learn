package com.jriver.condition;

import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Component;

/**
 * Created by wujianjiang on 2017-12-7.
 */
@Component
@Conditional(InitCondition.class)
public class ConditionTest {
}
