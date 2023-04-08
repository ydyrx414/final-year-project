package com.example.springboot.dto;
import lombok.Data;
import java.util.List;

/*
* 接收前段登录请求的参数
* */
@Data
public class UserDTO {
    private String username;
    private String password;
    private String nickname;
    private String email;
    private String phone;
    private int type;
}
