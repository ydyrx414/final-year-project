package com.example.springboot.dto;

import lombok.Data;

@Data
public class ApplicationQueryDTO {
    private Integer studentId;
    private Integer corporationId;
    private Integer jobId;
    private int pageSize;
    private int pageNum;
}
