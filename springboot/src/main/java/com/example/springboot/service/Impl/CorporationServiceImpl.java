package com.example.springboot.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.springboot.dto.CorporationQueryDTO;
import com.example.springboot.entity.Corporation;
import com.example.springboot.entity.User;
import com.example.springboot.entity.VCorp;
import com.example.springboot.mapper.CorporationMapper;
import com.example.springboot.mapper.VCorpMapper;
import com.example.springboot.service.ICorporationService;
import com.example.springboot.utils.Tuple2;
import com.example.springboot.vo.PageVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CorporationServiceImpl implements ICorporationService {

    @Override
    public boolean saveCorporation(Corporation corporation) {
        return false;
    }

    @Autowired
    private VCorpMapper corporationMapper;

    @Override
    public PageVO<VCorp> queryCorporations(CorporationQueryDTO dto) {
        LambdaQueryWrapper<VCorp> qw = new LambdaQueryWrapper<>();
        IPage<VCorp> page = new Page<>(dto.getPageNum(), dto.getPageSize());
        page = corporationMapper.selectPage(page, qw);
        return PageVO.from(page);
    }

}
