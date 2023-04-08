package com.example.springboot.vo;

import com.example.springboot.entity.Job;
import lombok.Data;

@Data
public class JobVO {
    private int id;
    private String name;
    private String expTime;
    private int corporationId;
    private String address;
    private String corporationName;
    public JobVO(Job dbJob,String corporationName,String address){
        this.id = dbJob.getId();
        this.name = dbJob.getName();
        this.address = address;
        this.corporationId = dbJob.getCorporation();
        this.corporationName = corporationName;
        this.expTime = dbJob.getExptime();
    }
}
