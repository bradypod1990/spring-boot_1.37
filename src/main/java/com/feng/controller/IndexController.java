package com.feng.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.feng.service.IndexService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;


@Controller
@Slf4j
@Api(tags="首页API")
public class IndexController {

	@Autowired
	private IndexService indexService;
	
	@Autowired
	private StringRedisTemplate redisTemplate;
	
	
    @RequestMapping(value="/", method= {RequestMethod.GET})
    @ApiOperation(value="跳转到首页",notes="返回首页地址")
    public String index(ModelMap model, HttpServletRequest request)  {
    	String key = redisTemplate.opsForValue().get("zou");
        model.put("message", "redis中key为zou的值为:" + key);
       // indexService.createDataSource();
        log.info("Hello man");
        return "index";
    }
    
    
}
