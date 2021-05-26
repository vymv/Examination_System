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
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.annotation.Resource;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
        mockMvc.perform(MockMvcRequestBuilders.get("/admin/showStudent"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
    }

    @Test
    public void addStudentUI() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/admin/addStudent"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    public void addStudent() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/admin/addStudent")
                .param("userid","2333")
                .param("username","小蓝")
                .param("sex","男")
                .param("collegeid","1"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(redirectedUrl("/admin/showStudent"))
                .andReturn();
    }

    @Test
    public void editStudentUI1() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/admin/editStudent"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(redirectedUrl("/admin/showStudent"))
                .andReturn();
    }
    @Test
    public void editStudentUI2() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/admin/editStudent")
                .param("id","10007"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andReturn();
    }
        
    @Test
    public void editStudent() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/admin/editStudent")
                .param("userid","2333")
                .param("username","小蓝")
                .param("sex","男")
                .param("collegeid","1"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(redirectedUrl("/admin/showStudent"))
                .andReturn();
    }

    @Test
    public void removeStudent() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/admin/removeStudent")
                .param("id","2333"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(redirectedUrl("/admin/showStudent"))
                .andReturn();
    }

    @Test
    public void selectStudent() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/admin/selectStudent")
                .param("findByName","小米"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andReturn();
    }
    @Test
    public void showTeacher() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/admin/showTeacher"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    public void addTeacherUI() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/admin/addTeacher"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    public void addTeacher() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/admin/addTeacher")
                .param("userid","2333")
                .param("username","老师")
                .param("sex","男")
                .param("degree","本科")
                .param("title","讲师")
                .param("collegeid","1"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(redirectedUrl("/admin/showTeacher"))
                .andReturn();
    }

    @Test
    public void editTeacherUI1() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/admin/editTeacher"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(redirectedUrl("/admin/showTeacher"))
                .andReturn();
    }

    @Test
    public void editTeacherUI2() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/admin/editTeacher")
                .param("id","1003"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andReturn();
    }
    @Test
    public void editTeacher() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/admin/editTeacher")
                .param("userid","2333")
                .param("username","老师")
                .param("sex","男")
                .param("degree","本科")
                .param("title","副教授")
                .param("collegeid","1"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(redirectedUrl("/admin/showTeacher"))
                .andReturn();
    }


    @Test
    public void removeTeacher() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/admin/removeTeacher")
                .param("id","2333"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(redirectedUrl("/admin/showTeacher"))
                .andReturn();
    }


    @Test
    public void selectTeacher() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/admin/selectTeacher")
                .param("findByName","刘老师"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    public void showCourse() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/admin/showCourse"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    public void addCourseUI() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/admin/addCourse"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andReturn();
    }
    @Test
    public void addCourse() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/admin/addCourse")
                .param("courseid","1024")
                .param("coursename","计算机图形学")
                .param("teacherid","1001")
                .param("collegeid","2")
                .param("score","2"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(redirectedUrl("/admin/showCourse"))
                .andReturn();

    }
    @Test
    public void editCourseUI1() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/admin/editCourse"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(redirectedUrl("/admin/showCourse"))
                .andReturn();
    }

    @Test
    public void editCourseUI2() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/admin/editCourse")
                .param("id","6"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andReturn();
    }
    @Test
    public void editCourse() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/admin/editCourse")
                .param("courseid","1024")
                .param("coursename","计算机图形学")
                .param("teacherid","1002")
                .param("collegeid","1")
                .param("score","2"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(redirectedUrl("/admin/showCourse"))
                .andReturn();

    }


    @Test
    public void removeCourse() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/admin/removeCourse")
                .param("id","1024"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(redirectedUrl("/admin/showCourse"))
                .andReturn();

    }

    @Test
    public void selectCourse() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/admin/selectCourse")
                .param("findByName","英语"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    public void userPasswordRestUI() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/admin/userPasswordRest"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andReturn();
    }
    @Test
    public void userPasswordRest1() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/admin/userPasswordRest")
                .param("username","10001")
                .param("password","123"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andReturn();

    }

    @Test
    public void userPasswordRest2() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/admin/userPasswordRest")
                .param("username","admin")
                .param("password","123"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andReturn();

    }

    @Test
    public void passwordRestUI() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/admin/passwordRest"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andReturn();

    }

}