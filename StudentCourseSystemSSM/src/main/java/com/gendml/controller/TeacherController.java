package com.gendml.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gendml.entity.*;
import com.gendml.mapper.CourseMapper;
import com.gendml.mapper.CoursePlanMapper;
import com.gendml.mapper.SCMapper;
import com.gendml.service.CourseService;
import com.gendml.service.StudentService;
import com.gendml.service.TeacherService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * @author Зөндөө
 * @create 2022-05-29 0:10
 */
@RestController
@RequestMapping("teacher")
@Slf4j
public class TeacherController {
    @Autowired
    private CoursePlanMapper coursePlanMapper;

    @Autowired
    private CourseService courseService;

    @Autowired
    private CourseMapper courseMapper;

    @Autowired
    private StudentService studentService;

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private SCMapper scMapper;
    

    @PostMapping("/login")
    public ResponseResult<Void> login(@RequestParam("account") String account,
                                      @RequestParam("password") String password) {
        Teacher teacher = teacherService.queryOneTeacherByTid(account);
        if (teacher != null && password.equals(teacher.getTpassword())) {
            return ResponseResult.success(UUID.randomUUID().toString());
        }
        return ResponseResult.error("");
    }

    @GetMapping("/userInfo")
    public ResponseResult<Map<String, String>> login(@RequestParam("id") String id) {
        Teacher teacher = teacherService.queryOneTeacherByTid(id);
        Map<String, String> res = new HashMap<>();
        if (teacher != null) {
            res.put("name", teacher.getTname());
            return ResponseResult.success(res);
        }
        res.put("name", "");
        return ResponseResult.error(res);
    }

    /*
     *
     * ******************************************************************************************************************
     * 教师管理课程
     *
     * */
    @PostMapping("insertCourse")
    public ResponseResult<Void> insertCourse(Course Course){
        //
        int res = courseService.insertCourse(Course);
        if(res != 0){
            return ResponseResult.success();
        }
        return ResponseResult.error();
    }

    @PostMapping("deleteCourse")
    public ResponseResult<Void> deleteCourse(@RequestParam("Cid") String Cid){
        int res = courseService.deleteCourseByCid(Cid);
        if(res != 0){
            return ResponseResult.success();
        }
        return ResponseResult.error();
    }

    @PostMapping("updateCourse")
    public ResponseResult<Void> updateCourse(Course Course){
        int res = courseService.updateCourse(Course);
        if(res != 0){
            return ResponseResult.success();
        }
        return ResponseResult.error();
    }

    //根据id模糊查询
    @GetMapping("getCourse/{Cid}")
    public ResponseResult<List<Course>> getCourse(@PathVariable("Cid") String Cid){
        List<Course> res = courseService.queryCourseByCid(Cid);
        if(res != null){
            for(Course c : res){
                Teacher resT = teacherService.queryOneTeacherByTid(c.getTid());
                c.setTeacher(resT);
            }
            return ResponseResult.success(res);
        }
        return ResponseResult.error();
    }

    //根据id查询
    @GetMapping("getOneCourse/{Cid}")
    public ResponseResult<Course> getOneCourse(@PathVariable("Cid") String Cid){
        Course res = courseService.queryOneCourseByCid(Cid);
        if(res != null){
            res.setTeacher(teacherService.queryOneTeacherByTid(res.getTid()));
            return ResponseResult.success(res);
        }
        return ResponseResult.error();
    }

    //根据id分页查询全部
    @GetMapping("getPageCourse/{currentPage}/{size}")
    public ResponseResult<List<Course>> getPageCourse(@PathVariable("currentPage") int currentPage,
                                                      @PathVariable("size") int size){
        List<Course> res = courseService.queryAllCourse(currentPage, size);
        if(res != null){
            for(Course c : res){
                Teacher resT = teacherService.queryOneTeacherByTid(c.getTid());
                c.setTeacher(resT);
            }
            return ResponseResult.success(res);
        }
        return ResponseResult.error();
    }



/*
*
* ******************************************************************************************************************
* 教师管理个人信息
* */
    @PostMapping("insertTeacher")
    public ResponseResult<Void> insertTeacher(Teacher Teacher){
        int res = teacherService.insertTeacher(Teacher);
        if(res != 0){
            return ResponseResult.success();
        }
        return ResponseResult.error();
    }

    //删除教师->删除教师信息、教师开课信息、教师课程对应的学生成绩、学生已选的课表
    @PostMapping("deleteTeacher")
    public ResponseResult<Void> deleteTeacher(@RequestParam("Tid") String Tid){
        //删除教师信息
        int res = teacherService.deleteTeacherByTid(Tid);
        //删除教师开课信息
        QueryWrapper<Course> qw1 = new QueryWrapper<>();
        qw1.eq("Tid",Tid);
        courseMapper.delete(qw1);
        //删除教师课程对应的学生成绩
        QueryWrapper<SC> qw2 = new QueryWrapper<>();
        qw1.eq("Tid",Tid);
        scMapper.delete(qw2);
        //删除学生已选的课表
        QueryWrapper<CoursePlan> qw3 = new QueryWrapper<>();
        qw1.eq("Tid",Tid);
        coursePlanMapper.delete(qw3);
        if(res != 0){
            return ResponseResult.success();
        }
        return ResponseResult.error();
    }

    @PostMapping("updateTeacher")
    public ResponseResult<Void> updateTeacher(Teacher Teacher){
        int res = teacherService.updateTeacher(Teacher);
        if(res != 0){
            return ResponseResult.success();
        }
        return ResponseResult.error();
    }

    //根据id模糊查询
    @GetMapping("getTeacher/{Tid}")
    public ResponseResult<List<Teacher>> getTeacher(@PathVariable("Tid") String Tid){
        List<Teacher> res = teacherService.queryTeacherByTid(Tid);
        if(res != null){
            return ResponseResult.success(res);
        }
        return ResponseResult.error();
    }

    //根据id查询
    @GetMapping("getOneTeacher/{Tid}")
    public ResponseResult<Teacher> getOneTeacher(@PathVariable("Tid") String Tid){
        Teacher res = teacherService.queryOneTeacherByTid(Tid);
        if(res != null){
            return ResponseResult.success(res);
        }
        return ResponseResult.error();
    }

    //根据id分页查询全部
    @GetMapping("getPageTeacher/{currentPage}/{size}")
    public ResponseResult<List<Teacher>> getPageTeacher(@PathVariable("currentPage") int currentPage,
                                                        @PathVariable("size") int size){
        List<Teacher> res = teacherService.queryAllTeacher(currentPage, size);
        if(res != null){
            return ResponseResult.success(res);
        }
        return ResponseResult.error();
    }

    //查询教师课表
    @GetMapping("getSchedule/{Tid}")
    public ResponseResult<List<Course>> getSchedule(@PathVariable("Tid") String tid) {

        List<Course> res = teacherService.queryMySchedules(tid);
        if (res != null) {
            return ResponseResult.success(res);
        }
        return ResponseResult.error();
    }

    //给学生打成绩
    @PostMapping("setGrade")
    public ResponseResult<Void> setGrade(SC sc){
        QueryWrapper<SC> qw = new QueryWrapper<>();
        qw.eq("Sid",sc.getSid());
        qw.eq("Cid",sc.getCid());
        qw.eq("Tid",sc.getTid());
        int res = scMapper.update(sc,qw);
        if(res != 0){
            return ResponseResult.success();
        }
        return ResponseResult.error();
    }

    //查成绩 根据教师id和课程id
    @GetMapping("queryGrade/{Tid}/{Cid}")
    public ResponseResult<List<SC>> queryGrade(@PathVariable("Tid") String tid,
                                               @PathVariable("Cid") String cid) {
        QueryWrapper<SC> qw = new QueryWrapper<>();
        qw.eq("Tid",tid);
        qw.eq("Cid",cid);
        List<SC> res = scMapper.selectList(qw);
        if(res != null){
            for(SC sc : res){
                sc.setCourse(courseService.queryOneCourseByCid(sc.getCid()));
                sc.setStudent(studentService.queryOneStudentBySid(sc.getSid()));
            }
            return ResponseResult.success(res);
        }
        return ResponseResult.error();
    }
}
