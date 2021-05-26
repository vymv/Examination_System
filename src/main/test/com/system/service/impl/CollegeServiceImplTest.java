package com.system.service.impl;

import com.system.service.CollegeService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class CollegeServiceImplTest {

    private ApplicationContext applicationContext;
    CollegeService collegeService;

    @Before
    public void setUp() throws Exception {
        applicationContext = new ClassPathXmlApplicationContext(new String[]{"spring/applicationContext-dao.xml",
                "spring/applicationContext-service.xml"});
        collegeService = (CollegeService) applicationContext.getBean("collegeServiceImpl");
    }

    @Test
    public void findAll() throws Exception {
        collegeService.findAll();
    }
}