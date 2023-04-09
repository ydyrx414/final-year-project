package com.example.springboot.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.sql.Timestamp;

@TableName("v_application")
@Data
public class VApplication {
    private int id;
    @TableField("studentid")
    private int studentId;
    @TableField("studentname")
    private String studentName;
    @TableField("jobid")
    private int jobId;
    @TableField("jobname")
    private String jobName;
    private Timestamp time;
    @TableField("corpid")
    private int corpId;
    @TableField("corpname")
    private String corpName;
    private int type;
}
