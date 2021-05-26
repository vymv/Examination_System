package com.system.controller;

import com.system.vo.ResetPasswordVo;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONArray;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ThreadContext;
import org.apache.shiro.web.subject.WebSubject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.annotation.Resource;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/*.xml")
@WebAppConfiguration
@EnableWebMvc
public class RestPasswordControllerTest {

    @Autowired
    private WebApplicationContext context;

    private MockMvc mockMvc;

    @Resource
    org.apache.shiro.mgt.SecurityManager securityManager;
    @Resource
    private WebApplicationContext webApplicationContext;

    private Subject subject;

    private MockHttpServletRequest mockHttpServletRequest;
    private MockHttpServletResponse mockHttpServletResponse;


    //用户登录
    private void login(String username, String password) {
        subject = new WebSubject.Builder(mockHttpServletRequest, mockHttpServletResponse)
                .buildWebSubject();
        //用户密码
        UsernamePasswordToken token = new UsernamePasswordToken(username, password, true);
        subject.login(token);
        ThreadContext.bind(subject);
    }

    @Before
    public void init() {
        //mockMvc = MockMvcBuilders.webAppContextSetup(context).build();

        mockHttpServletRequest = new MockHttpServletRequest(webApplicationContext.getServletContext());
        mockHttpServletResponse = new MockHttpServletResponse();
        MockHttpSession mockHttpSession = new MockHttpSession(webApplicationContext.getServletContext());
        mockHttpServletRequest.setSession(mockHttpSession);
        SecurityUtils.setSecurityManager( securityManager);
        mockMvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .build();


    }

    @Test
    public void passwordRest14() throws Exception {

        login("10001", "123");
        String OldPassword = "123";
        String NewPassword = "1234";

        mockMvc.perform(MockMvcRequestBuilders.post("/passwordRest")
                .param("oldPassword", OldPassword)
                .param("password1", NewPassword))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(redirectedUrl("/logout"))
                .andReturn();
    }

    @Test
    public void passwordRest15() throws Exception {

        login("10002", "123");
        String OldPassword = "123";
        String NewPassword = "";
        mockMvc.perform(MockMvcRequestBuilders.post("/passwordRest")
                .param("oldPassword", OldPassword)
                .param("password1", NewPassword)
                .content("Json"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andReturn();

    }


    @Test
    public void passwordRest16() throws Exception {
        login("10003", "123");
        String OldPassword = "123";
        String NewPassword = "123";
        mockMvc.perform(MockMvcRequestBuilders.post("/passwordRest")
                .param("oldPassword", OldPassword)
                .param("password1", NewPassword)
                .content("Json"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andReturn();
    }


    @Test
    public void passwordRest24() throws Exception {
        login("10004", "123");
        String OldPassword = "";
        String NewPassword = "1234";
        mockMvc.perform(MockMvcRequestBuilders.post("/passwordRest")
                .param("oldPassword", OldPassword)
                .param("password1", NewPassword)
                .content("Json"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andReturn();
    }


    @Test
    public void passwordRest34() throws Exception {
        login("10005", "123");
        String OldPassword = "124";
        String NewPassword = "1234";
        mockMvc.perform(MockMvcRequestBuilders.post("/passwordRest")
                .param("oldPassword", OldPassword)
                .param("password1", NewPassword)
                .content("Json"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andReturn();
    }
}