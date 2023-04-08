package com.example.springboot.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
@TableName(value = "sys_user")
public class User {
    @TableId(value = "id", type = IdType.AUTO)
    private int id;

    private String username;
    @JsonIgnore
    private String password;
    private String nickname;
    private String email;
    private String phone;
    private int type = TYPE_ADMIN;


    public static final int TYPE_ADMIN = 0;
    public static final int TYPE_STUDENT = 1;
    public static final int TYPE_CORPORATION = 2;
}
