package com.example.springboot.dto;

import lombok.Data;

@Data
public class CorporationRegisterDTO {
    private String username;
    private String password;
    private String nickname;
    private String token;
}
