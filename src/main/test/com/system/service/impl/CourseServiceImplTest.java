package com.system.service.impl;

import com.system.po.CourseCustom;
import com.system.service.CourseService;
import com.system.service.StudentService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

import static org.junit.Assert.*;


public class CourseServiceImplTest {

    private CourseService courseService;
    private ApplicationContext applicationContext;

    @Before
    public void setUp() throws Exception {
        applicationContext = new ClassPathXmlApplicationContext(new String[]{"spring/applicationContext-dao.xml",
                "spring/applicationContext-service.xml"});
        courseService = (CourseService) applicationContext.getBean("courseServiceImpl");
    }

    @Test
    public void updateById() throws Exception {
        CourseCustom courseCustom = new CourseCustom();
        courseCustom.setClassroom("海韵教学楼");
        courseService.updateById(5,courseCustom);
        Assert.assertEquals(courseService.findById(5).getClassroom(),"海韵教学楼");

    }

    @Test
    public void removeById1() throws Exception {
        courseService.removeById(6);
        Assert.assertNull(courseService.findById(6));
    }

    @Test
    public void removeById2() throws Exception {
        Assert.assertFalse(courseService.removeById(Integer.getInteger("100")));
    }

    @Test
    public void findByPaging() throws Exception {
        List<CourseCustom> courseCustomList = courseService.findByPaging(Integer.getInteger("1"));
        Assert.assertEquals(courseCustomList.get(0).getCoursename(),"C语言程序设计");

    }

    @Test
    public void save() throws Exception {
        CourseCustom courseCustom = new CourseCustom();
        courseCustom.setCourseid(1024);
        courseService.save(courseCustom);
        Assert.assertNotNull(courseService.findById(1024));
    }

    @Test
    public void getCountCourse() throws Exception {
        int count  = courseService.getCountCourse();
        Assert.assertEquals(count,6);
    }

    @Test
    public void findById() throws Exception {

        CourseCustom courseCustom = courseService.findById(1);
        Assert.assertEquals(courseCustom.getCoursename(),"C语言程序设计");
    }


    @Test
    public void findByName() throws Exception {
        List<CourseCustom> courseCustomList = courseService.findByName("英语");
        Assert.assertEquals(courseCustomList.get(0).getCourseid().intValue(),5);

    }

    @Test
    public void findByTeacherID() throws Exception {

        List<CourseCustom> courseCustomList = courseService.findByTeacherID(1001);
        Assert.assertEquals(courseCustomList.get(0).getTeacherid().intValue(),1001);
    }

}