package com.system.service.impl;

import com.system.po.CourseCustom;
import com.system.service.CourseService;
import com.system.service.StudentService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

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
        courseService.updateById(Integer.getInteger("5"),courseCustom);
    }

    @Test
    public void removeById1() throws Exception {
        courseService.removeById(Integer.getInteger("6"));
    }

    @Test
    public void removeById2() throws Exception {
        courseService.removeById(Integer.getInteger("100"));
    }

    @Test
    public void findByPaging() throws Exception {
        courseService.findByPaging(Integer.getInteger("1"));
    }

    @Test
    public void save() throws Exception {
        CourseCustom courseCustom = new CourseCustom();
        courseCustom.setCourseid(Integer.getInteger("1024"));
        courseService.save(courseCustom);
    }

    @Test
    public void getCountCourse() throws Exception {
        courseService.getCountCourse();
    }

    @Test
    public void findById() throws Exception {

        CourseCustom courseCustom = courseService.findById(1);

        System.out.println();
    }


    @Test
    public void findByName() throws Exception {
        courseService.findByName("英语");
    }

    @Test
    public void findByTeacherID() throws Exception {

        courseService.findByTeacherID(1001);

    }

}