package com.feng.listener;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;

import lombok.extern.slf4j.Slf4j;

@WebListener
@Slf4j
public class MyRquestListener implements ServletRequestListener {

	@Override
	public void requestDestroyed(ServletRequestEvent arg0) {
		log.info("myReqest 请求注销了");
	}

	@Override
	public void requestInitialized(ServletRequestEvent arg0) {
		log.info("myRequest 请求初始化");
	}

}
