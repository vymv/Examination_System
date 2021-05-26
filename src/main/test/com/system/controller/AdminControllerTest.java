package com.system.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ThreadContext;
import org.apache.shiro.web.subject.WebSubject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.annotation.Resource;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/*.xml")
@WebAppConfiguration
public class AdminControllerTest {

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
        login("admin", "123");

    }

    @Test
    public void showStudent() throws Exception {
        String responseString = mockMvc.perform(MockMvcRequestBuilders.get("/admin/showStudent"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();
        System.out.println(responseString);
    }

    @Test
    public void addStudent() throws Exception {
        String responseString = mockMvc.perform(MockMvcRequestBuilders.get("/admin/addStudent"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();
        System.out.println(responseString);
    }


    @Test
    public void editStudent() throws Exception {
        String responseString = mockMvc.perform(MockMvcRequestBuilders.get("/admin/editStudent"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();
        System.out.println(responseString);
    }

    @Test
    public void showTeacher() throws Exception {
        String responseString = mockMvc.perform(MockMvcRequestBuilders.get("/admin/showTeacher"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();
        System.out.println(responseString);
    }

    @Test
    public void addTeacher() throws Exception {
        String responseString = mockMvc.perform(MockMvcRequestBuilders.get("/admin/addTeacher"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();
        System.out.println(responseString);
    }

    @Test
    public void editTeacher() throws Exception {
        String responseString = mockMvc.perform(MockMvcRequestBuilders.get("/admin/editStudent"))
                .andExpect(redirectedUrl("/admin/showStudent"))
                .andReturn()
                .getResponse()
                .getContentAsString();
        System.out.println(responseString);
    }


    @Test
    public void removeTeacher() throws Exception {
        String responseString = mockMvc.perform(MockMvcRequestBuilders.get("/admin/removeTeacher"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();
        System.out.println(responseString);
    }


    @Test
    public void selectTeacher() throws Exception {
        String responseString = mockMvc.perform(MockMvcRequestBuilders.get("/admin/selectTeacher?findByName="))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();
        System.out.println(responseString);
    }

    @Test
    public void showCourse() throws Exception {
        String responseString = mockMvc.perform(MockMvcRequestBuilders.get("/admin/showCourse"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();
        System.out.println(responseString);
    }


    @Test
    public void addCourse() throws Exception {
        String responseString = mockMvc.perform(MockMvcRequestBuilders.get("/admin/addCourse"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

    }

    @Test
    public void editCourse() throws Exception {
        String responseString = mockMvc.perform(MockMvcRequestBuilders.get("/admin/editCourse"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

    }


    @Test
    public void removeCourse() throws Exception {
        String responseString = mockMvc.perform(MockMvcRequestBuilders.get("/admin/removeCourse"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

    }

    @Test
    public void selectCourse() throws Exception {
        String responseString = mockMvc.perform(MockMvcRequestBuilders.get("/admin/selectCourse"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

    }

    @Test
    public void userPasswordRest() throws Exception {
        String responseString = mockMvc.perform(MockMvcRequestBuilders.get("/admin/userPasswordRest"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

    }

    @Test
    public void passwordRestUI() throws Exception {
        String responseString = mockMvc.perform(MockMvcRequestBuilders.get("/admin/passwordRest"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

    }

}