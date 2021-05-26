package com.system.service.impl;

import com.system.po.Userlogin;
import com.system.service.TeacherService;
import com.system.service.UserloginService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.junit.Assert.*;

/**
 * Created by Jacey on 2017/6/30.
 */
public class UserloginServiceImplTest {

    private ApplicationContext applicationContext;
    UserloginService userloginService;

    @Before
    public void setUp() throws Exception {
        applicationContext = new ClassPathXmlApplicationContext(new String[]{"spring/applicationContext-dao.xml",
                "spring/applicationContext-service.xml"});
        userloginService = (UserloginService) applicationContext.getBean("userloginServiceImpl");
    }

    @Test
    public void findByName() throws Exception {
        Userlogin u = userloginService.findByName("123");
        System.out.println();
    }

    @Test
    public void save() throws Exception {
        Userlogin userlogin = new Userlogin();
        userlogin.setUserid(Integer.getInteger("1111"));
        userlogin.setUsername("a");
        userlogin.setPassword("abc");

        userloginService.save(userlogin);
    }

    @Test
    public void reomveByName() throws Exception {
        userloginService.removeByName("10005");
    }

    @Test
    public void updateByName(){
        Userlogin userlogin = new Userlogin();
        userlogin.setPassword("000");
        userloginService.updateByName("小拉",userlogin);
    }
}