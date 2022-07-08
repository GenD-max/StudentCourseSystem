package com.gendml.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gendml.entity.Course;
import com.gendml.entity.CoursePlan;
import com.gendml.entity.Teacher;
import com.gendml.mapper.CourseMapper;
import com.gendml.mapper.CoursePlanMapper;
import com.gendml.mapper.TeacherMapper;
import com.gendml.service.TeacherService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Зөндөө
 * @create 2022-05-28 22:55
 */
@Service
@Slf4j
public class TeacherServiceImpl implements TeacherService {
    @Autowired
    private TeacherMapper TeacherMapper;

    @Autowired
    private CourseMapper courseMapper;

    @Override
    public int insertTeacher(Teacher Teacher) {
        int res = TeacherMapper.insert(Teacher);
        if(res != 0){
            log.info("存入课程信息成功：{}",Teacher);
        }else{
            log.info("存入课程信息失败！");
        }
        return res;
    }

    @Override
    public int deleteTeacherByTid(String Tid) {
        int res = TeacherMapper.delete(new QueryWrapper<Teacher>().eq("Tid",Tid));
        if(res == 0){
            log.info("删除失败！");
        }
        log.info("删除了{}条数据",res);
        return res;
    }

    @Override
    public int updateTeacher(Teacher Teacher) {
        UpdateWrapper<Teacher> wrapper = new UpdateWrapper<>();
        wrapper.eq("Tid",Teacher.getTid());
        int res = TeacherMapper.update(Teacher,wrapper);
        if(res == 0){
            log.info("更新失败！");
        }
        log.info("更新了{}条数据",res);
        return res;
    }

    @Override
    public List<Teacher> queryAllTeacher(int currentPage,int size) {
        //参数1：开始页 参数2：每页的大小
        IPage<Teacher> page = new Page<>(currentPage,size);
        TeacherMapper.selectPage(page,null);
//        System.out.println(page.getCurrent()); // 获取当前页
//        System.out.println(page.getTotal()); // 获取总记录数
//        System.out.println(page.getSize()); // 获取每页的条数 默认10
//        System.out.println(page.getRecords()); // 获取每页数据的集合
//        System.out.println(page.getPages()); // 获取总页数
        return page.getRecords();
    }

    @Override
    public List<Teacher> queryTeacherByTid(String Tid) {
        return TeacherMapper.selectList(new QueryWrapper<Teacher>().like("Tid",Tid));
    }

    @Override
    public Teacher queryOneTeacherByTid(String tid) {
        return TeacherMapper.selectOne(new QueryWrapper<Teacher>().like("Tid",tid));
    }

    @Override
    public int getTeacherNum() {
        return TeacherMapper.selectCount(new QueryWrapper<Teacher>());
    }

    @Override
    public List<Course> queryMySchedules(String tid) {
        QueryWrapper<Course> qw = new QueryWrapper<>();
        qw.eq("Tid", tid);
        return courseMapper.selectList(qw);
    }

}
