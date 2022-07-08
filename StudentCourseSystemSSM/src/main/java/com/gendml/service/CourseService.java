package com.gendml.service;

import com.gendml.entity.Course;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Зөндөө
 * @create 2022-05-28 22:45
 */
public interface CourseService {

    public int insertCourse(Course course);

    public int deleteCourseByCid(String cid);

    public int updateCourse(Course course);

    public List<Course> queryAllCourse(int currentPage,int size);

    public List<Course> queryCourseByCid(String cid);

    public Course queryOneCourseByCid(String cid);

    public int getCourseNum();
}
