package com.example.springboot.dto;

import lombok.Data;

@Data
public class JobQueryDTO {
    private int pageNum;
    private int pageSize;
    private String name;
    private String expTime;
    private String address;
}
