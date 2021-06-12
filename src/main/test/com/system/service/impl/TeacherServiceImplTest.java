package com.system.service.impl;

import com.sun.xml.internal.ws.policy.AssertionSet;
import com.system.po.Teacher;
import com.system.po.TeacherCustom;
import com.system.service.TeacherService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;


public class TeacherServiceImplTest {
    private ApplicationContext applicationContext;
    TeacherService teacherService;

    @Before
    public void setUp() throws Exception {
        applicationContext = new ClassPathXmlApplicationContext(new String[]{"spring/applicationContext-dao.xml",
                "spring/applicationContext-service.xml"});
        teacherService = (TeacherService) applicationContext.getBean("teacherServiceImpl");
    }

    @Test
    public void updateById() throws Exception {
        TeacherCustom teacherCustom = new TeacherCustom();
        teacherCustom.setUserid(1003);
        teacherCustom.setUsername("软老师");

        //指定时间格式
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-M-d");
        // 指定一个日期
        Date date = dateFormat.parse("1996-09-02");
        teacherCustom.setBirthyear(date);

        teacherCustom.setCollegeid(1);
        teacherCustom.setSex("男");
        teacherCustom.setGrade(new Date());
        teacherCustom.setTitle("助教");
        teacherCustom.setDegree("硕士");

        teacherService.updateById(1003, teacherCustom);
        Assert.assertEquals(teacherService.findById(1003).getUsername(),"软老师");
        Assert.assertEquals(teacherService.findById(1003).getTitle(),"助教");
        Assert.assertEquals(teacherService.findById(1003).getDegree(),"硕士");
    }

    @Test
    public void removeById() throws Exception {
        teacherService.removeById(1003);
        Assert.assertNull(teacherService.findById(1003));
    }

    @Test
    public void findByPaging() throws Exception {
        List<TeacherCustom> list = teacherService.findByPaging(1);
        Assert.assertEquals(list.get(0).getUsername(),"刘老师");
    }

    @Test
    public void save() throws Exception {
        TeacherCustom teacherCustom = new TeacherCustom();
        teacherCustom.setUserid(1003);
        teacherCustom.setUsername("软老师");

        //指定时间格式
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-M-d");
        // 指定一个日期
        Date date = dateFormat.parse("1996-09-02");
        teacherCustom.setBirthyear(date);

        teacherCustom.setCollegeid(1);
        teacherCustom.setSex("男");
        teacherCustom.setGrade(new Date());
        teacherCustom.setTitle("助教");

        teacherService.save(teacherCustom);
        Assert.assertEquals(teacherService.findById(1003).getUsername(),"软老师");
        Assert.assertEquals(teacherService.findById(1003).getTitle(),"助教");
    }

    @Test
    public void getCountTeacher() throws Exception {
        int i = teacherService.getCountTeacher();
        Assert.assertEquals(i,3);
    }

    @Test
    public void findById() throws Exception {

        TeacherCustom teacherCustom = teacherService.findById(1001);
        Assert.assertEquals(teacherCustom.getUsername(),"刘老师");
    }

    @Test
    public void findByName() throws Exception {
        List<TeacherCustom> list = teacherService.findByName("老师");
        Assert.assertEquals(list.get(0).getUsername(),"刘老师");
        Assert.assertEquals(list.size(),3);
    }

    @Test
    public void findAll() throws Exception {
        List<TeacherCustom> list = teacherService.findAll();
        Assert.assertEquals(list.get(0).getUsername(),"刘老师");
        Assert.assertEquals(list.size(),3);
    }

}