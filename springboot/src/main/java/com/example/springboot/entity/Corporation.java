package com.example.springboot.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName(value = "corporation")
public class Corporation {
    @TableId(value = "id",type = IdType.AUTO)
    private int id;
    private String address;
}
