package com.system.service.impl;

import com.system.po.CourseCustom;
import com.system.po.SelectedCourseCustom;
import com.system.service.SelectedCourseService;
import org.junit.Assert;
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
        Assert.assertEquals(list.get(0).getCourseid().intValue(),1);

    }

    @Test
    public void countByCourseID() throws Exception {
        int count = service.countByCourseID(2);
        Assert.assertEquals(count,2);
    }

    @Test
    public void findOne1() throws Exception {
        SelectedCourseCustom selectedCourseCustom =new SelectedCourseCustom();
        selectedCourseCustom.setCourseid(2);
        selectedCourseCustom.setStudentid(10001);
        SelectedCourseCustom courseCustom = service.findOne(selectedCourseCustom);
        Assert.assertEquals(courseCustom.getMark().intValue(),12);

    }

    @Test
    public void findOne2() throws Exception {
        SelectedCourseCustom selectedCourseCustom =new SelectedCourseCustom();
        selectedCourseCustom.setCourseid(2);
        selectedCourseCustom.setStudentid(10003);
        SelectedCourseCustom courseCustom = service.findOne(selectedCourseCustom);
        Assert.assertEquals(courseCustom.getMark().intValue(),19);
    }


    @Test
    public void updataOne() throws Exception {
        SelectedCourseCustom selectedCourseCustom =new SelectedCourseCustom();
        selectedCourseCustom.setCourseid(6);
        selectedCourseCustom.setStudentid(10001);
        selectedCourseCustom.setMark(78);
        service.updataOne(selectedCourseCustom);

        Assert.assertEquals(service.findOne(selectedCourseCustom).getCourseid().intValue(),6);
        Assert.assertEquals(service.findOne(selectedCourseCustom).getStudentid().intValue(),10001);
        Assert.assertEquals(service.findOne(selectedCourseCustom).getMark().intValue(),78);
    }

    @Test
    public void save() throws Exception {
        SelectedCourseCustom selectedCourseCustom =new SelectedCourseCustom();
        selectedCourseCustom.setCourseid(6);
        selectedCourseCustom.setStudentid(10010);
        selectedCourseCustom.setMark(78);
        service.save(selectedCourseCustom);
        Assert.assertEquals(service.findOne(selectedCourseCustom).getCourseid().intValue(),6);
        Assert.assertEquals(service.findOne(selectedCourseCustom).getStudentid().intValue(),10010);
        Assert.assertEquals(service.findOne(selectedCourseCustom).getMark().intValue(),78);

    }


    @Test
    public void remove() throws Exception {
        SelectedCourseCustom selectedCourseCustom =new SelectedCourseCustom();
        selectedCourseCustom.setCourseid(6);
        selectedCourseCustom.setStudentid(10010);
        service.remove(selectedCourseCustom);
        Assert.assertNull(service.findOne(selectedCourseCustom));
    }
}