package com.example.springboot.dto;

import lombok.Data;

@Data
public class RegisterDTO {
    private String username;
    private String password;
    private String nickName;
    private int type;
}
