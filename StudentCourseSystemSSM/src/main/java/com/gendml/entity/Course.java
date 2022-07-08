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
import java.util.Date;

/**
 * @author Зөндөө
 * @create 2022-05-28 20:47
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
@Accessors(chain = true)
@TableName("course")
public class Course {
    @TableId
    private String Cid;
    private String Tid;
    private String Cname;
    private Float Ccredit;
    private String Cintroduction;
    private String Ctype;
    private String Cbelongcoll;
    private String Cbelongpro;
    private String Monday;
    private String Tuesday;
    private String Wednesday;
    private String Thursday;
    private String Friday;
    private String Csemester;
    private String Courseweek;
    private String Cclassroom;
    private String Cteachbuilding;
    private Date Cmodtime;

    @TableField(exist = false)
    @Transient
    private Teacher teacher;
}
