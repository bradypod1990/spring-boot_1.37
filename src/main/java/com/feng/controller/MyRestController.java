	package com.feng.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.feng.advice.Log;
import com.feng.model.ObjectProperty;
import com.feng.service.ObjectService;

@RestController
@RequestMapping("rest")
public class MyRestController {


	@Autowired
	private ObjectService objectService;
	
	/**
	 * 获取关系型属性信息
	 * @param pageNum
	 * @param pageSize
	 * @param systemCode 系统编码
	 * @param objectName 表名称
	 * @return
	 */
	@RequestMapping("/queryObjectProperty")
	@Cacheable(value="zoufeng", key="#pageNum+'-'+#pageSize+'-'+#systemCode+'-'+#objectName")
	public Page<ObjectProperty> queryObjectProperty(@RequestParam(required=true) int pageNum, @RequestParam(required=true) int pageSize, @RequestParam(required=false,defaultValue="")String systemCode, @RequestParam(required=false,defaultValue="")String objectName) {
 		IPage<ObjectProperty> page =  objectService.queryObjectProperty(pageNum, pageSize, systemCode, objectName);
 		Page<ObjectProperty> returnPage = new Page<ObjectProperty> (page.getCurrent(),page.getSize(), page.getTotal());
 		returnPage.setRecords(page.getRecords());
		return returnPage;
	}
	
	@RequestMapping("/aspectTest1")
	public String aspectTest1() {
		return "我是没加注解的";
	}
	
	@RequestMapping("/aspectTest2")
	@Log(ignore=true, desc="hello")
	public String aspectTest2() {
		return "我是加了注解的";
	}
	
	
}
