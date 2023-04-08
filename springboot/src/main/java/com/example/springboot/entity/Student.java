package com.example.springboot.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName(value = "student")
public class Student {
    @TableId(value = "id",type = IdType.AUTO)
    private int id;
    private String subject;
    private String nature;
    private String intention;
}
