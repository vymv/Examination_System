package com.system.service.impl;

import com.system.po.PagingVO;
import com.system.po.Student;
import com.system.po.StudentCustom;
import com.system.service.StudentService;
import com.system.service.TeacherService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Jacey on 2017/6/29.
 */
public class StudentServiceImplTest {


    private ApplicationContext applicationContext;
    StudentService studentService;

    @Before
    public void setUp() throws Exception {
        applicationContext = new ClassPathXmlApplicationContext(new String[]{"spring/applicationContext-dao.xml",
                "spring/applicationContext-service.xml"});
        studentService = (StudentService) applicationContext.getBean("studentServiceImpl");
    }

    @Test
    public void updataById() throws Exception {


        StudentCustom studentCustom = new StudentCustom();
        studentCustom.setUserid(10004);
        studentCustom.setUsername("小拉");
//        studentCustom.setBirthyear(new Date(1996, 9, 2));

        //指定时间格式
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-M-d");
        // 指定一个日期
        Date date = dateFormat.parse("1990-09-06");
        studentCustom.setBirthyear(date);

        studentCustom.setCollegeid(1);
        studentCustom.setSex("男");
        studentCustom.setGrade(new Date());
        studentService.updataById(10004, studentCustom);
        Assert.assertEquals(studentService.findById(10004).getSex(),"男");
    }

    @Test
    public void removeById() throws Exception {

        studentService.removeById(10004);
        Assert.assertNotNull(studentService.findById(10004));
    }

    @Test
    public void findByPaging() throws Exception {

        List<StudentCustom> list =  studentService.findByPaging(1);
        Assert.assertEquals(list.get(0).getUsername(),"小黄");

    }

    @Test
    public void save() throws Exception {
        StudentCustom studentCustom = new StudentCustom();
        studentCustom.setUserid(10040);
        studentCustom.setUsername("小花");
//        studentCustom.setBirthyear(new Date(1996, 9, 2));

        //指定时间格式
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-M-d");
        // 指定一个日期
        Date date = dateFormat.parse("1996-09-02");
        studentCustom.setBirthyear(date);

        studentCustom.setCollegeid(1);
        studentCustom.setSex("男");
        studentCustom.setGrade(new Date());


        studentService.save(studentCustom);
        Assert.assertEquals(studentService.findById(10040).getUsername(),"小花");
    }

    @Test
    public void getCountStudent() throws Exception {

        int i = studentService.getCountStudent();
        Assert.assertEquals(i,7);
    }

    @Test
    public void findById() throws Exception {

        Student student = studentService.findById(10001);
        Assert.assertEquals(student.getUsername(),"小黄");
        Assert.assertEquals(student.getSex(),"男");
    }

    @Test
    public void findByName() throws Exception {

        List<StudentCustom> list = studentService.findByName("小");
        Assert.assertEquals(list.get(0).getUsername(),"小黄");

    }



}