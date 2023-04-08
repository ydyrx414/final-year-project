package com.example.springboot.vo;

import com.example.springboot.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RegisterOrLoginVO {
    private User user;
    private String token;
}
