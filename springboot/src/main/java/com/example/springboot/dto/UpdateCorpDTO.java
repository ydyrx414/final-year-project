package com.example.springboot.dto;

import lombok.Data;

@Data
public class UpdateCorpDTO {
    private String email;
    private int id;
    private String nickName;
    private String username;
    private String phone;
    private String address;
}
