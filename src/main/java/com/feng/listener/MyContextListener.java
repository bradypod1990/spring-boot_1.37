package com.feng.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import lombok.extern.slf4j.Slf4j;
@WebListener
@Slf4j
public class MyContextListener implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		log.info("上下文销毁");
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		log.info("上下文初始化");
	}

}
