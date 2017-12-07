package com.jriver;

import com.jriver.condition.ConditionTest;
import com.jriver.scanAnnontation.ScanTest;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ImportResource;

@ImportResource
@SpringBootApplication // same as @Configuration @EnableAutoConfiguration @ComponentScan
public class Application {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(Application.class, args);
        /*new SpringApplicationBuilder().bannerMode(Banner.Mode.CONSOLE).listeners(new ApplicationListener<ApplicationEvent>() {
            @Override
            public void onApplicationEvent(ApplicationEvent applicationEvent) {
                System.out.println("========");
            }
        }).build().run(args);*/
        ConditionTest condition = context.getBean("conditionTest", ConditionTest.class);
        System.out.println(condition == null);

        ScanTest scanTest = context.getBean("scanTest", ScanTest.class);
        scanTest.print();
    }

}