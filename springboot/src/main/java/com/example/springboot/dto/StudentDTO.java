package com.example.springboot.dto;

import lombok.Data;
/*
 * 接收前段登录请求的参数
 * */

@Data
public class StudentDTO {

    private String username;
    private String password;
    private String nickname;
    private String token;

}



