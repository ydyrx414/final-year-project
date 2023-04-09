package com.example.springboot.dto;

import lombok.Data;

@Data
public class UpdateStudentDTO {
    private String email;
    private int id;
    private String intention;
    private String nature;
    private String nickName;
    private String username;
    private String phone;
    private String subject;
}
