package com.feng.mian;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * 通过http://127.0.0.1:8090/swagger-ui.html访问API页面
 * @author Administrator
 *
 */
@EnableSwagger2
@Configuration
@ConfigurationProperties(locations={"classpath:config.properties"})
public class SwaggerConfig {
	
	@Value("${swagger.enabled}")
	Boolean swaggerEnable;

	@Bean
	public Docket createRestApi() {
		return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo())
				.enable(swaggerEnable)
				// 是否开启
				.select()
				// 扫描的路径包
				.apis(RequestHandlerSelectors.basePackage("com.feng"))
				 // 指定路径处理PathSelectors.any()代表所有的路径
				.paths(PathSelectors.any()).build().pathMapping("/");
	}
	
	
	private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("SpringBoot-Swagger2集成和使用-demo示例")
                .description("Bradypod 邹枫")
                // 作者信息
                .contact(new Contact("Bradypod", "www.baidu.com", "734844277@qq.com"))
                .version("1.0.0")
                .build();
    }
}
