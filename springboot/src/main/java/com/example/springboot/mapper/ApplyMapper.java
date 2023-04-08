package com.example.springboot.mapper;

import com.example.springboot.entity.Apply;
import com.example.springboot.entity.Corporation;
import com.github.yulichang.base.MPJBaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface ApplyMapper extends MPJBaseMapper<Apply> {
}
