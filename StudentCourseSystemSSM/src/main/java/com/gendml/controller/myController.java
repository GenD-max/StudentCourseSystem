package com.gendml.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gendml.entity.Admin;
import com.gendml.entity.Faculity;
import com.gendml.entity.ResponseResult;
import com.gendml.mapper.FaculityMapper;
import com.gendml.service.AdminService;
import com.gendml.service.CourseService;
import com.gendml.service.StudentService;
import com.gendml.service.TeacherService;
import com.mysql.cj.util.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Зөндөө
 * @create 2022-06-07 0:03
 */
@Slf4j
@RestController
public class myController {

    @Autowired
    private CourseService courseService;

    @Autowired
    private StudentService studentService;

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private AdminService adminService;

    @Autowired
    private FaculityMapper faculityMapper;

    @GetMapping("getNumInfo")
    public ResponseResult<Map<String,Integer>> getNumInfo(){
        int courseNum = courseService.getCourseNum();
        int studentNum = studentService.getStudentNum();
        int teacherNum = teacherService.getTeacherNum();
        int adminNum = adminService.getAdminNum();
        Map<String, Integer> res = new HashMap<>();
        res.put("courseNum",courseNum);
        res.put("studentNum",studentNum);
        res.put("teacherNum",teacherNum);
        res.put("adminNum",adminNum);
        return ResponseResult.success(res);
    }

    @PostMapping("insertFaculity")
    public ResponseResult<Void> insertFaculity(@RequestParam("faculityName") String faculityName){
        int res = faculityMapper.insert(new Faculity().setFname(faculityName));
        if(res != 0){
            return ResponseResult.success();
        }
        return ResponseResult.error();
    }

    @GetMapping("queryFaculity")
    public ResponseResult<List<Faculity>> queryFaculity(){
        List<Faculity> res = faculityMapper.selectList(new QueryWrapper<>());
        if(null != res){
            return ResponseResult.success(res);
        }
        return ResponseResult.error();
    }
}
