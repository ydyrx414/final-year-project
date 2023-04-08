package com.example.springboot.vo;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;

import java.sql.Timestamp;

@Data
@Builder
public class ApplyVO {
    private int jobId;
    private int corporationId;
    private String jobName;
    private String corporationName;
    private Timestamp createdTime;
    private int studentId;
    private String studentName;
    private int type;
}
