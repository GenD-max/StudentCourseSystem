package com.gendml.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * @author Зөндөө
 * @create 2022-06-07 0:31
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
//Lombok的注解方法：开启后可以使用链式的set方法 比如Answer ans = new Answer().setTitle("").setId(1);
@Accessors(chain = true)
//通过此注解，确定该实体类对应的表
@TableName("faculity")
//表示被注解的类映射到数据库表
public class Faculity {
    private String Fname;
}
