package com.gendml.entity;

import com.baomidou.mybatisplus.annotation.TableField;
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
 * @create 2022-05-28 22:11
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
@Accessors(chain = true)
@TableName("sc")
public class SC {
    private String Sid;
    private String Cid;
    private String Tid;
    private Integer Ggrade;
    private Date Gmodtime;

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
