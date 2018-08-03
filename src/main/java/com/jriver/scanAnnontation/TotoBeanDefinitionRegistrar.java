package com.jriver.scanAnnontation;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotationMetadata;

/**
 * 在注解上添加 @Import(TotoBeanDefinitionRegistrar.class) 扫描实例化注解BEAN
 * Created by wujianjiang on 2018-7-24.
 */
public class TotoBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar, EnvironmentAware, BeanFactoryAware {

    private BeanFactory beanFactory;

    private Environment environment;

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    @Override
    public void registerBeanDefinitions(AnnotationMetadata annotationMetadata, BeanDefinitionRegistry beanDefinitionRegistry) {
       /* AnnotationAttributes annoAttrs = AnnotationAttributes.fromMap(annotationMetadata.getAnnotationAttributes(EnableFlowSystemTodo.class.getName()));
        String addressKey = annoAttrs.getString("addressKey");
        String serverKey = annoAttrs.getString("serverKey");
        String timeoutKey = annoAttrs.getString("timeoutKey");
        String encodingKey = annoAttrs.getString("encodingKey");
        String address = environment.getProperty(addressKey, "127.0.0.1");
        String serverName = environment.getProperty(serverKey, "localhost");
        String encoding = environment.getProperty(encodingKey, "utf-8");
        Integer timeout = environment.getProperty(timeoutKey, Integer.class, 1000);*/

        // 创建自定义BEAN
        Object o = new Object();
        ((DefaultListableBeanFactory) this.beanFactory).registerSingleton("todoZbusInvoker", o);
    }
}
