package com.system.service.impl;

import com.system.po.Role;
import com.system.service.RoleService;
import com.system.service.StudentService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;



import static org.junit.Assert.*;

public class RoleServiceImplTest {

    private ApplicationContext applicationContext;
    RoleService roleService;

    @Before
    public void setUp() throws Exception {
        applicationContext = new ClassPathXmlApplicationContext(new String[]{"spring/applicationContext-dao.xml",
                "spring/applicationContext-service.xml"});
        roleService = (RoleService) applicationContext.getBean("roleServiceImpl");
    }
    @Test
    public void findByid() throws Exception {
        Integer id =Integer.getInteger("1");
        Role r = roleService.findByid(id);
        Assert.assertEquals(r.getRolename(),"teacher");
    }
}