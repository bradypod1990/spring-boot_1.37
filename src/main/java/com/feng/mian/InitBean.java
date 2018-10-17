package com.feng.mian;

import javax.servlet.Filter;

import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.feng.filter.MyFilter;

@Configuration
public class InitBean {

	/**
	 * 实例化filter，这种方法可以设置filter的初始化顺序
	 * @return
	 */
	@Bean
	public FilterRegistrationBean myFilterBean() {
		FilterRegistrationBean myFilterBean = new FilterRegistrationBean();
		myFilterBean.setFilter(myFilter());
		myFilterBean.addUrlPatterns("/*");
		myFilterBean.setName("myFilter");
		myFilterBean.setOrder(1);
		return myFilterBean;
	}
	
	@Bean
	public Filter myFilter() {
		return new MyFilter();
	}
}
