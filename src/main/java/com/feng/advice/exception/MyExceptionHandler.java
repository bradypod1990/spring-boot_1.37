package com.feng.advice.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.extern.slf4j.Slf4j;

@ControllerAdvice
@Slf4j
public class MyExceptionHandler {

	
	//多个错误可以定义多个方法
	@ExceptionHandler(Exception.class)
	@ResponseBody
	public Map<String, Object> commonException(Exception e) {
		//一般情况下都是封装成对象
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("code", -1);
		resultMap.put("mes", e.getMessage());
		log.error(e.getMessage(), e);
		return resultMap;
	}
}
