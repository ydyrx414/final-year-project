package com.example.springboot.dto;

import lombok.Data;
import org.springframework.web.bind.annotation.RequestParam;

@Data
public class UserQueryDTO {
    private int pageNum;
    private int pageSize;
    private String username;
    private String nickname;
    private String email;
    private String phone;
}
