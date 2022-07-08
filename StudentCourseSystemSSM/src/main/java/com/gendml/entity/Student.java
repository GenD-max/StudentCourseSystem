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
 * @create 2022-05-28 22:13
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
@Accessors(chain = true)
@TableName("student")
public class Student {
    private String Sid;
    private String Sname;
    private String Sidcard;
    private String Ssex;
    private String Spassword;
    private String Sage;
    private String Sgrade;
    private String Sclass;
    private String Sprofession;
    private String Sinstitution;
    private String Sphotourl;
    private Date Smodtime;
}
