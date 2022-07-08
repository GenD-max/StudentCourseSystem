package com.gendml.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gendml.entity.Course;
import com.gendml.entity.CoursePlan;
import com.gendml.entity.Student;
import com.gendml.mapper.CoursePlanMapper;
import com.gendml.mapper.StudentMapper;
import com.gendml.service.StudentService;
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
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentMapper StudentMapper;

    @Autowired
    private CoursePlanMapper coursePlanMapper;


    @Override
    public int insertStudent(Student Student) {
        int res = StudentMapper.insert(Student);
        if (res != 0) {
            log.info("存入课程信息成功：{}", Student);
        } else {
            log.info("存入课程信息失败！");
        }
        return res;
    }

    @Override
    public int deleteStudentBySid(String sid) {
        int res = StudentMapper.delete(new QueryWrapper<Student>().eq("Sid", sid));
        if (res == 0) {
            log.info("删除失败！");
        }
        log.info("删除了{}条数据", res);
        return res;
    }

    @Override
    public int updateStudent(Student Student) {
        UpdateWrapper<Student> wrapper = new UpdateWrapper<>();
        wrapper.eq("Sid", Student.getSid());
        int res = StudentMapper.update(Student, wrapper);
        if (res == 0) {
            log.info("更新失败！");
        }
        log.info("更新了{}条数据", res);
        return res;
    }

    @Override
    public List<Student> queryAllStudent(int currentPage, int size) {
        //参数1：开始页 参数2：每页的大小
        IPage<Student> page = new Page<>(currentPage, size);
        StudentMapper.selectPage(page, null);
//        System.out.println(page.getCurrent()); // 获取当前页
//        System.out.println(page.getTotal()); // 获取总记录数
//        System.out.println(page.getSize()); // 获取每页的条数 默认10
//        System.out.println(page.getRecords()); // 获取每页数据的集合
//        System.out.println(page.getPages()); // 获取总页数
        return page.getRecords();
    }

    @Override
    public List<Student> queryStudentBySid(String sid) {
        return StudentMapper.selectList(new QueryWrapper<Student>().like("Sid", sid));
    }

    @Override
    public Student queryOneStudentBySid(String sid) {
        return StudentMapper.selectOne(new QueryWrapper<Student>().like("Sid", sid));
    }

    @Override
    public int getStudentNum() {
        return StudentMapper.selectCount(new QueryWrapper<Student>());
    }

    @Override
    public List<CoursePlan> queryMySchedules(String sid) {
        QueryWrapper<CoursePlan> qw = new QueryWrapper<>();
        qw.eq("Sid", sid);
        return coursePlanMapper.selectList(qw);
    }

}
