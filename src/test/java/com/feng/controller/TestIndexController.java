package com.feng.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import com.feng.service.IndexService;

//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(classes=Application.class) //加载应用程序上下文
//@SpringApplicationConfiguration(classes=Application.class) //不仅加载应用程序上下文，还会开启日志、加载外部属性（application.properties或application.yml），以及其他Spring Boot特性。
//@WebAppConfiguration //开启web上下文
public class TestIndexController {

    @Autowired
    private WebApplicationContext webContext;
    
    private MockMvc mockMvc;
    
    @Autowired
    private IndexService indexService;
    
    @Before
    public void before() {
        mockMvc = webAppContextSetup(webContext).build();
    }
    
    @Test
    public void testIndexSerivce() {
        String result = indexService.hello();
        Assert.assertEquals(result, "hello world service");
    }
    
    @Test
    public void testHomePage() {
        try {
            mockMvc.perform(get("/"))
                    .andExpect(status().isOk())
                    .andExpect(view().name("index"))
                    .andExpect(model().attributeExists("message"))
                    ;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
