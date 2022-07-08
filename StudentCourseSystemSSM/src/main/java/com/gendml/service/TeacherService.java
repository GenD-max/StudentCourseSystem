package com.gendml.service;

import com.gendml.entity.Course;
import com.gendml.entity.CoursePlan;
import com.gendml.entity.Teacher;

import java.util.List;

/**
 * @author Зөндөө
 * @create 2022-05-28 22:45
 */
public interface TeacherService {

    public int insertTeacher(Teacher tea);

    public int deleteTeacherByTid(String tid);

    public int updateTeacher(Teacher tea);

    public List<Teacher> queryAllTeacher(int currentPage,int size);

    public List<Teacher> queryTeacherByTid(String tid);

    public Teacher queryOneTeacherByTid(String tid);

    public int getTeacherNum();

    public List<Course> queryMySchedules(String tid);
}
