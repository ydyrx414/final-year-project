package com.example.springboot.dto;

import lombok.Data;

@Data
public class ApplicationQueryDTO {
    private Integer studentId;
    private Integer corporationId;
    private Integer jobId;
    private int type;
    private int pageSize;
    private int pageNum;
    public static final int TYPE_SENT_BY_STUDENT = 0;
    public static final int TYPE_SENT_BY_CORP = 1;
}
