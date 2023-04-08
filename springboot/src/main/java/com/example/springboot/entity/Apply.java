package com.example.springboot.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.sql.Timestamp;

@TableName("apply")
@Data
public class Apply {
    private int id;
    @TableField("studentid")
    private int studentId;
    @TableField("jobid")
    private int jobId;
    private Timestamp time;
    private int type = TYPE_APPLY;
    public static final int TYPE_APPLY = 0;
    public static final int TYPE_INVITATION = 1;
}
