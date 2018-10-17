package com.feng.mian;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

/**
 * 加载mybatis配置文件,也可写在主类上
 * @author Administrator
 *
 */
@Configuration
@ImportResource(locations={"classpath:spring-mybatis.xml"})
public class MybatisConfigure {

}
