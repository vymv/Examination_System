package com.system.service.impl;

import com.system.exception.CustomException;
import com.system.mapper.*;
import com.system.po.*;
import com.system.service.StudentService;
import com.system.service.TeacherService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

/**
 * Student
 */
@Service
public class StudentServiceImpl implements StudentService {

    //使用spring 自动注入
    @Autowired
    private StudentMapperCustom studentMapperCustom;

    @Autowired
    private StudentMapper studentMapper;

    @Autowired
    private CollegeMapper collegeMapper;

    public void updataById(Integer id, StudentCustom studentCustom) throws Exception {
        studentMapper.updateByPrimaryKey(studentCustom);
    }

    public void removeById(Integer id) throws Exception {
        studentMapper.deleteByPrimaryKey(id);
    }
    public List<StudentCustom> findByPaging(Integer toPageNo) throws Exception {
        PagingVO pagingVO = new PagingVO();
        pagingVO.setToPageNo(toPageNo);
        List<StudentCustom> list = studentMapperCustom.findByPaging(pagingVO);
        return list;
    }
    public Boolean save(StudentCustom studentCustoms) throws Exception {
        Student stu = studentMapper.selectByPrimaryKey(studentCustoms.getUserid());
        if (stu == null) {
            studentMapper.insert(studentCustoms);
            return true;
        }

        return false;
    }


    public int getCountStudent() throws Exception {
        //自定义查询对象
        StudentExample studentExample = new StudentExample();
        StudentExample.Criteria criteria = studentExample.createCriteria();
        criteria.andUseridIsNotNull();
        return studentMapper.countByExample(studentExample);
    }
    public StudentCustom findById(Integer id) throws Exception {

        Student student  = studentMapper.selectByPrimaryKey(id);
        StudentCustom studentCustom = null;
        if (student != null) {
            studentCustom = new StudentCustom();
            BeanUtils.copyProperties(student, studentCustom);
        }
        return studentCustom;
    }
    public List<StudentCustom> findByName(String name) throws Exception {

        StudentExample studentExample = new StudentExample();
        StudentExample.Criteria criteria = studentExample.createCriteria();
        criteria.andUsernameLike("%" + name + "%");
        List<Student> list = studentMapper.selectByExample(studentExample);
        List<StudentCustom> studentCustomList = null;
        if (list != null) {
            studentCustomList = new ArrayList<StudentCustom>();
            for (Student s : list) {
                StudentCustom studentCustom = new StudentCustom();
                BeanUtils.copyProperties(s, studentCustom);
                College college = collegeMapper.selectByPrimaryKey(s.getCollegeid());
                studentCustom.setcollegeName(college.getCollegename());
                studentCustomList.add(studentCustom);
            }
        }
        return studentCustomList;
    }

    @Override
    public StudentCustom findStudentAndSelectCourseListByName(String name) throws Exception {
        StudentCustom studentCustom = studentMapperCustom.findStudentAndSelectCourseListById(Integer.parseInt(name));
        List<SelectedCourseCustom> list = studentCustom.getSelectedCourseList();
        // 判断该课程是否修完
        for (SelectedCourseCustom s : list) {
            if (s.getMark() != null) {
                s.setOver(true);
            }
        }
        return studentCustom;
    }
}
