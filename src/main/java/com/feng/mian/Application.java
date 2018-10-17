package com.feng.mian;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.EnableAsync;


@SpringBootApplication(scanBasePackages={"com.feng"})
//引入自定义的配置文件  
//YAML的缺点：不能通过@PropertySource注解加载。如果需要使用@PropertySource注解的方式加载值，那就要使用properties文件。
//@PropertySource(value="classpath:application_zf.properties")
@ServletComponentScan(basePackages={"com.feng.filter","com.feng.listener"})
//@ImportResource(locations={"classpath:spring-mybatis.xml"})
//启动异步调用
@EnableAsync
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
