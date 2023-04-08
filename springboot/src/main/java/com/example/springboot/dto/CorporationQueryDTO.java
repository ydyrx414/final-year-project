package com.example.springboot.dto;

import lombok.Data;
import org.springframework.web.bind.annotation.RequestParam;

@Data
public class CorporationQueryDTO {
    private int pageSize;
    private int pageNum;
    private String username;
    private String nickname;
    private String address;
    private String email;
    private String phone;
}
