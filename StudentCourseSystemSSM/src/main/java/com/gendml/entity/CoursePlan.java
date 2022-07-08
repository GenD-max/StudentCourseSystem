package com.gendml.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.persistence.Transient;

/**
 * @author Зөндөө
 * @create 2022-05-28 21:10
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
@Accessors(chain = true)
@TableName("courseplan")
public class CoursePlan {
    @TableId
    private String Cname;
    private String Cid;
    private String Tid;
    private String Sid;
    private String Monday;
    private String Tuesday;
    private String Wednesday;
    private String Thursday;
    private String Friday;

    @TableField(exist = false)
    @Transient
    private Course course;

    @TableField(exist = false)
    @Transient
    private Teacher teacher;

    @TableField(exist = false)
    @Transient
    private Student student;
}
