package com.example.springboot.mapper;

import com.example.springboot.entity.VApplication;
import com.github.yulichang.base.MPJBaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface VAppMapper extends MPJBaseMapper<VApplication> {
}
