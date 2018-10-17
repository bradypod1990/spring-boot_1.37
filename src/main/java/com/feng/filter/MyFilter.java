package com.feng.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

import lombok.extern.slf4j.Slf4j;
//这种方法不能够设置filter的初始化顺序，只能按照字典编码类初始化加载顺序（eg:a**比b**开头的优先加载），手动设置顺序参考InitBean中的myFilter
//@WebFilter(filterName="myFilter", urlPatterns={"/*"})
@Slf4j
public class MyFilter implements Filter {

	@Override
	public void destroy() {
		log.info("MyFilter is destroy");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		log.info("myFilter do filter ");
		chain.doFilter(request, response);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		log.info("myFilter is init");
	}

}
