package com.gendml.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gendml.entity.*;
import com.gendml.mapper.CoursePlanMapper;
import com.gendml.mapper.SCMapper;
import com.gendml.service.CourseService;
import com.gendml.service.StudentService;
import com.gendml.service.TeacherService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.net.ResponseCache;
import java.util.*;

/**
 * @author Зөндөө
 * @create 2022-05-29 0:10
 */
@RestController
@RequestMapping("student")
@Slf4j
public class StudentController {

    @Autowired
    private CourseService courseService;

    @Autowired
    private StudentService studentService;

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private CoursePlanMapper coursePlanMapper;

    @Autowired
    private SCMapper scMapper;


    @PostMapping("/login")
    public ResponseResult<Void> login(@RequestParam("account") String account,
                                      @RequestParam("password") String password) {
        Student student = studentService.queryOneStudentBySid(account);
        if (student != null && password.equals(student.getSpassword())) {
            return ResponseResult.success(UUID.randomUUID().toString());
        }
        return ResponseResult.error("");
    }

    @GetMapping("/userInfo")
    public ResponseResult<Map<String, String>> login(@RequestParam("id") String id) {
        Student student = studentService.queryOneStudentBySid(id);
        Map<String, String> res = new HashMap<>();
        if (student != null) {
            res.put("name", student.getSname());
            return ResponseResult.success(res);
        }
        res.put("name", "");
        return ResponseResult.error(res);
    }

    @PostMapping("register")
    public ResponseResult<Void> register(@RequestParam("Sid") String Sid,
                                         @RequestParam("Spassword") String Spassword,
                                         @RequestParam("Sname") String Sname,
                                         @RequestParam("Ssex") String Ssex,
                                         @RequestParam("Sidcard") String Sidcard,
                                         @RequestParam("Sgrade") String Sgrade,
                                         @RequestParam("Sinstitution") String Sinstitution) {
        if (studentService.queryOneStudentBySid(Sid) != null) {
            return ResponseResult.error();
        }
        int res = studentService.insertStudent(new Student().setSid(Sid)
                .setSpassword(Spassword)
                .setSname(Sname)
                .setSsex(Ssex)
                .setSidcard(Sidcard)
                .setSgrade(Sgrade)
                .setSinstitution(Sinstitution)
                .setSmodtime(new Date()));
        if (res == 1) {
            return ResponseResult.success();
        }
        return ResponseResult.error();
    }

    //管理员删除学生->清除学生信息、学生成绩信息、课程表信息
    @PostMapping("deleteStudent")
    public ResponseResult<Void> deleteStudent(@RequestParam("Sid") String Sid) {
        //删除学生表对应学生信息
        int res = studentService.deleteStudentBySid(Sid);
        //删除成绩表对应信息
        QueryWrapper<SC> qw1 = new QueryWrapper<>();
        qw1.eq("Sid",Sid);
        scMapper.delete(qw1);
        //删除课程表对应信息
        QueryWrapper<CoursePlan> qw2 = new QueryWrapper<>();
        qw1.eq("Sid",Sid);
        coursePlanMapper.delete(qw2);
        if (res != 0) {
            return ResponseResult.success();
        }
        return ResponseResult.error();
    }

    @PostMapping("updateStudent")
    public ResponseResult<Void> updateStudent(Student student) {
        int res = studentService.updateStudent(student);
        if (res != 0) {
            return ResponseResult.success();
        }
        return ResponseResult.error();
    }

    //根据id分页查询全部
    @GetMapping("getPageStudent/{currentPage}/{size}")
    public ResponseResult<List<Student>> getPageStudent(@PathVariable("currentPage") int currentPage,
                                                        @PathVariable("size") int size){
        List<Student> res = studentService.queryAllStudent(currentPage, size);
        if(res != null){
            return ResponseResult.success(res);
        }
        return ResponseResult.error();
    }

    //根据id查询
    @GetMapping("getOneStudent/{Sid}")
    public ResponseResult<Student> getOneStudent(@PathVariable("Sid") String Sid) {
        Student res = studentService.queryOneStudentBySid(Sid);
        if (res != null) {
            return ResponseResult.success(res);
        }
        return ResponseResult.error();
    }

    //选课(自动同步成绩表)
    @PostMapping("insertCoursePlan")
    public ResponseResult<Void> getOneStudent(CoursePlan coursePlan) {
        int res = coursePlanMapper.insert(coursePlan);
        if (res != 0) {
            scMapper.insert(new SC().setSid(coursePlan.getSid())
                    .setCid(coursePlan.getCid())
                    .setTid(coursePlan.getTid())
            );
            return ResponseResult.success();
        }
        return ResponseResult.error();
    }


    //查询学生课表
    @GetMapping("getSchedule/{Sid}")
    public ResponseResult<List<CoursePlan>> getSchedule(@PathVariable("Sid") String Sid) {
        List<CoursePlan> res = studentService.queryMySchedules(Sid);
        if (res != null) {
            for (CoursePlan coursePlan : res) {
                coursePlan.setCourse(courseService.queryOneCourseByCid(coursePlan.getCid()));
//                coursePlan.setStudent(studentService.queryOneStudentBySid(coursePlan.getSid()));
                coursePlan.setTeacher(teacherService.queryOneTeacherByTid(coursePlan.getTid()));
            }
            return ResponseResult.success(res);
        }
        return ResponseResult.error();
    }

    //查成绩
    @GetMapping("getGrade/{Sid}")
    public ResponseResult<List<SC>> getGrade(@PathVariable("Sid") String Sid){
        QueryWrapper<SC> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("Sid",Sid);
        List<SC> res = scMapper.selectList(queryWrapper);
        if(res != null){
            for (SC sc : res) {
                Course course = courseService.queryOneCourseByCid(sc.getCid());
                sc.setCourse(course);
//                coursePlan.setStudent(studentService.queryOneStudentBySid(coursePlan.getSid()));
                sc.setTeacher(teacherService.queryOneTeacherByTid(course.getTid()));
            }
            return ResponseResult.success(res);
        }
        return ResponseResult.error();
    }


}
