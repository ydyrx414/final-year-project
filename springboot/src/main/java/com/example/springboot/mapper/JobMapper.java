package com.example.springboot.mapper;

import com.example.springboot.entity.Job;
import com.github.yulichang.base.MPJBaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface JobMapper extends MPJBaseMapper<Job> {
}
