package com.gendml.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * @author Зөндөө
 * @create 2022-05-27 21:45
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
//Lombok的注解方法：开启后可以使用链式的set方法 比如Answer ans = new Answer().setTitle("").setId(1);
@Accessors(chain = true)
//通过此注解，确定该实体类对应的表
@TableName("admin")
//表示被注解的类映射到数据库表
public class Admin {
    private String Aid;
    private String Aname;
    private String Apassword;
}
