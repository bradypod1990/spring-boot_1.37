package com.feng.mian;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;

/**
 * 
 * @author zf
 *   SpringBootServletInitializer是一个支持
 *   Spring Boot的Spring WebApplicationInitializer实现。除了配置Spring的DispatcherServlet， SpringBootServletInitializer还会在Spring应用程序上下文里查找Filter、
 *   Servlet或ServletContextInitializer类型的Bean，把它们绑定到Servlet容器里。
 */
public class ReadingListServletInitializer extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(
            SpringApplicationBuilder builder) {
        return builder.sources(Application.class);
    }

}
