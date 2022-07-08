package com.gendml.service;

import com.gendml.entity.CoursePlan;
import com.gendml.entity.ResponseResult;
import com.gendml.entity.Student;

import java.util.List;

/**
 * @author Зөндөө
 * @create 2022-05-28 22:45
 */
public interface StudentService {

    public int insertStudent(Student stu);

    public int deleteStudentBySid(String sid);

    public int updateStudent(Student stu);

    public List<Student> queryAllStudent(int currentPage,int size);

    public List<Student> queryStudentBySid(String sid);

    public Student queryOneStudentBySid(String sid);

    public int getStudentNum();

    public List<CoursePlan> queryMySchedules(String sid);
}
