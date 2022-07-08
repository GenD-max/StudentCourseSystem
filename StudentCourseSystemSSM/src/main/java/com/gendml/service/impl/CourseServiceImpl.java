package com.gendml.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gendml.entity.Course;
import com.gendml.mapper.CourseMapper;
import com.gendml.service.CourseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
 * @author Зөндөө
 * @create 2022-05-28 22:55
 */
@Service
@Slf4j
public class CourseServiceImpl implements CourseService {
    @Autowired
    private CourseMapper courseMapper;

    @Override
    public int insertCourse(Course course) {
        int res = courseMapper.insert(course);
        if(res != 0){
            log.info("存入课程信息成功：{}",course);
        }else{
            log.info("存入课程信息失败！");
        }
        return res;
    }

    @Override
    public int deleteCourseByCid(String cid) {
        int res = courseMapper.delete(new QueryWrapper<Course>().eq("Cid",cid));
        if(res == 0){
            log.info("删除失败！");
        }
        log.info("删除了{}条数据",res);
        return res;
    }

    @Override
    public int updateCourse(Course course) {
        UpdateWrapper<Course> wrapper = new UpdateWrapper<>();
        wrapper.eq("Cid",course.getCid());
        int res = courseMapper.update(course,wrapper);
        if(res == 0){
            log.info("更新失败！");
        }
        log.info("更新了{}条数据",res);
        return res;
    }

    @Override
    public List<Course> queryAllCourse(int currentPage,int size) {
        //参数1：开始页 参数2：每页的大小
        IPage<Course> page = new Page<>(currentPage,size);
        courseMapper.selectPage(page,null);
//        System.out.println(page.getCurrent()); // 获取当前页
//        System.out.println(page.getTotal()); // 获取总记录数
//        System.out.println(page.getSize()); // 获取每页的条数 默认10
//        System.out.println(page.getRecords()); // 获取每页数据的集合
//        System.out.println(page.getPages()); // 获取总页数
        return page.getRecords();
    }

    @Override
    public List<Course> queryCourseByCid(String cid) {
        return courseMapper.selectList(new QueryWrapper<Course>().like("Cid",cid));
    }

    @Override
    public Course queryOneCourseByCid(String cid) {
        return courseMapper.selectOne(new QueryWrapper<Course>().like("Cid",cid));
    }

    @Override
    public int getCourseNum() {
        return courseMapper.selectCount(new QueryWrapper<Course>());
    }

}
