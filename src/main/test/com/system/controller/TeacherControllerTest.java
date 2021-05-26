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
public class TeacherControllerTest {
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
        login("1001", "123");

    }
    @Test
    public void stuCourseShow() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/teacher/showCourse"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    public void gradeCourse() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/teacher/gradeCourse"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    public void markUI() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/teacher/mark"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    public void mark1() throws Exception {

        String courseid = "1";
        String studentid = "10001";
        String mark = "-1";

        mockMvc.perform(MockMvcRequestBuilders.post("/teacher/mark")
                .param("courseid", courseid)
                .param("studentid", studentid)
                .param("mark", mark))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(model().attribute("message","成绩边界超出范围"))
                .andReturn();
    }

    @Test
    public void mark2() throws Exception {

        String courseid = "1";
        String studentid = "10002";
        String mark = "0";

        mockMvc.perform(MockMvcRequestBuilders.post("/teacher/mark")
                .param("courseid", courseid)
                .param("studentid", studentid)
                .param("mark", mark))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(redirectedUrl("/teacher/gradeCourse?id="+courseid))
                .andReturn();
    }
    @Test
    public void mark3() throws Exception {

        String courseid = "1";
        String studentid = "10003";
        String mark = "1";

        mockMvc.perform(MockMvcRequestBuilders.post("/teacher/mark")
                .param("courseid", courseid)
                .param("studentid", studentid)
                .param("mark", mark))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(redirectedUrl("/teacher/gradeCourse?id="+courseid))
                .andReturn();
    }
    @Test
    public void mark4() throws Exception {

        String courseid = "1";
        String studentid = "10004";
        String mark = "88";

        mockMvc.perform(MockMvcRequestBuilders.post("/teacher/mark")
                .param("courseid", courseid)
                .param("studentid", studentid)
                .param("mark", mark))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(redirectedUrl("/teacher/gradeCourse?id="+courseid))
                .andReturn();
    }
    @Test
    public void mark5() throws Exception {

        String courseid = "1";
        String studentid = "10005";
        String mark = "99";

        mockMvc.perform(MockMvcRequestBuilders.post("/teacher/mark")
                .param("courseid", courseid)
                .param("studentid", studentid)
                .param("mark", mark))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(redirectedUrl("/teacher/gradeCourse?id="+courseid))
                .andReturn();
    }
    @Test
    public void mark6() throws Exception {

        String courseid = "1";
        String studentid = "10006";
        String mark = "100";

        mockMvc.perform(MockMvcRequestBuilders.post("/teacher/mark")
                .param("courseid", courseid)
                .param("studentid", studentid)
                .param("mark", mark))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(redirectedUrl("/teacher/gradeCourse?id="+courseid))
                .andReturn();
    }
    @Test
    public void mark7() throws Exception {

        String courseid = "1";
        String studentid = "10007";
        String mark = "101";

        mockMvc.perform(MockMvcRequestBuilders.post("/teacher/mark")
                .param("courseid", courseid)
                .param("studentid", studentid)
                .param("mark", mark))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(model().attribute("message","成绩边界超出范围"))
                .andReturn();
    }


    @Test
    public void passwordRest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/admin/userPasswordRest"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andReturn();
    }
}