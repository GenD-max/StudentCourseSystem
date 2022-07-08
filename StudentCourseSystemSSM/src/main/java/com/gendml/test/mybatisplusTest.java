package com.gendml.test;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gendml.entity.*;
import com.gendml.mapper.*;
import com.gendml.service.AdminService;
import com.gendml.service.CourseService;
import com.gendml.service.StudentService;
import com.gendml.service.TeacherService;
import lombok.extern.slf4j.Slf4j;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * @author Зөндөө
 * @create 2022-03-27 20:16
 */
@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:config/*.xml")
public class mybatisplusTest {

    @Autowired
    private TeacherMapper courseMapper;

    @Autowired
    private CourseService courseService;

    @Autowired
    private StudentService studentService;

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private AdminService adminService;
    /**
     * 测试增
     */
    @Test
    public void testInsert() {
        QueryWrapper<Teacher> wrapper = new QueryWrapper<>();
        List<Teacher> res = courseMapper.selectList(wrapper);
        System.out.println(res);
        log.info("查询结果为：{}",res.toString());
    }

    @Test
    public void testService(){
        System.out.println(adminService.updateAdminByAid(new Admin().setAid("@Admin123").setAname("董梦龙2").setApassword("123456")));
    }

    @Test
    public void testStudentService(){
        System.out.println(teacherService.queryTeacherByTid("0002"));
    }

}
