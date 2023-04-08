package com.example.springboot.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName("job")
@Data
public class Job {
    private int id;
    private String name;
    private int corporation;
    private String exptime;
}
