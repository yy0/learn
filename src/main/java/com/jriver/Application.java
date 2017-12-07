package com.jriver;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;
import org.springframework.stereotype.Controller;

@ImportResource
@MapperScan(basePackages = "com.jriver.dao", annotationClass = org.springframework.stereotype.Repository.class)
@SpringBootApplication // same as @Configuration @EnableAutoConfiguration @ComponentScan
@Controller
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
       /* new SpringApplicationBuilder().bannerMode(Banner.Mode.CONSOLE).listeners(new ApplicationListener<ApplicationEvent>() {
            @Override
            public void onApplicationEvent(ApplicationEvent applicationEvent) {
                System.out.println("========");
            }
        }).build().run(args);*/
    }

}