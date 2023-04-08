package com.example.springboot.dto;

import lombok.Data;
import org.springframework.web.bind.annotation.RequestParam;

@Data
public class StudentQueryDTO {
    private int pageNum;
    private int pageSize;
    private String username;
    private String nickname;
    private String subject;
    private String nature;
    private String intention;
    private String email;
    private String phone;
}
