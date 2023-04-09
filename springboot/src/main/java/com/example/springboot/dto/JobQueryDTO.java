package com.example.springboot.dto;

import lombok.Data;

@Data
public class JobQueryDTO {
    private int pageNum;
    private int pageSize;
    private String name;
    private String expTime;
    private String address;
    private int view;
    public static final int VIEW_ANY = 0;
    public static final int VIEW_APPLICABLE = 1;
    public static final int VIEW_MY_POSTS = 2;

}
