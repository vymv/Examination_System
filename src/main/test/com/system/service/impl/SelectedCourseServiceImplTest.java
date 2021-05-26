package com.system.service.impl;

import com.system.po.CourseCustom;
import com.system.po.SelectedCourseCustom;
import com.system.service.SelectedCourseService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

/**
 * Created by Jacey on 2017/7/6.
 */
public class SelectedCourseServiceImplTest {


    private ApplicationContext applicationContext;
    private SelectedCourseService service;
    @Before
    public void setUp() throws Exception {
        applicationContext = new ClassPathXmlApplicationContext(new String[]{"spring/applicationContext-dao.xml",
                "spring/applicationContext-service.xml"});
        service = (SelectedCourseService) applicationContext.getBean("selectedCourseServiceImpl");
    }

    @Test
    public void findByCourseID() throws Exception {
        List<SelectedCourseCustom> list = service.findByCourseID(1);
        System.out.println();

    }

    @Test
    public void countByCourseID() throws Exception {
        service.countByCourseID(2);
    }

    @Test
    public void findOne1() throws Exception {
        SelectedCourseCustom selectedCourseCustom =new SelectedCourseCustom();
        selectedCourseCustom.setCourseid(6);
        selectedCourseCustom.setStudentid(10001);
        service.findOne(selectedCourseCustom);
    }

    @Test
    public void findOne2() throws Exception {
        SelectedCourseCustom selectedCourseCustom =new SelectedCourseCustom();
        selectedCourseCustom.setCourseid(1);
        selectedCourseCustom.setStudentid(10001);
        service.findOne(selectedCourseCustom);
    }


    @Test
    public void updataOne() throws Exception {
        SelectedCourseCustom selectedCourseCustom =new SelectedCourseCustom();
        selectedCourseCustom.setCourseid(6);
        selectedCourseCustom.setStudentid(10001);
        selectedCourseCustom.setMark(78);
        service.updataOne(selectedCourseCustom);
    }

    @Test
    public void save() throws Exception {
        SelectedCourseCustom selectedCourseCustom =new SelectedCourseCustom();
        selectedCourseCustom.setCourseid(6);
        selectedCourseCustom.setStudentid(10010);
        selectedCourseCustom.setMark(78);
        service.save(selectedCourseCustom);
    }


    @Test
    public void remove() throws Exception {
        SelectedCourseCustom selectedCourseCustom =new SelectedCourseCustom();
        selectedCourseCustom.setCourseid(6);
        selectedCourseCustom.setStudentid(10010);
        service.remove(selectedCourseCustom);
    }
}