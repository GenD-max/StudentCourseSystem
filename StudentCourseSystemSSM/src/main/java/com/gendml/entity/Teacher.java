package com.gendml.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * @author Зөндөө
 * @create 2022-05-28 22:18
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
@Accessors(chain = true)
@TableName("teacher")
public class Teacher {
    private String Tid;
    private String Tname;
    private String Tpassword;
    private String Tsex;
    private String Tposition;
    private String Tphone;
    private String Temail;
    private String Tphotourl;
    private String Tintroduction;
    private Date Tmodtime;
}
