package com.example.springboot.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.springboot.dto.CorporationQueryDTO;
import com.example.springboot.dto.UpdateCorpDTO;
import com.example.springboot.entity.Corporation;
import com.example.springboot.entity.Student;
import com.example.springboot.entity.User;
import com.example.springboot.entity.VCorp;
import com.example.springboot.mapper.*;
import com.example.springboot.service.ICorporationService;
import com.example.springboot.service.IUserService;
import com.example.springboot.utils.StringHelper;
import com.example.springboot.utils.Tuple2;
import com.example.springboot.vo.PageVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CorporationServiceImpl implements ICorporationService {

    @Autowired
    private VStudentMapper vStudentMapper;
    @Autowired
    private IUserService userService;
    @Autowired
    private StudentMapper studentMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private CorporationMapper corporationMapper;
    @Autowired
    private VCorpMapper vCorpMapper;

    @Override
    public VCorp update(UpdateCorpDTO dto) {
        Tuple2<User, Corporation> t = userService.findCorporation(dto.getId());
        User user = t.getA();
        Corporation corp = t.getB();

        if (!StringHelper.isBlankOrEmptyOrNull(dto.getEmail())) {
            user.setEmail(dto.getEmail());
        }
        if (!StringHelper.isBlankOrEmptyOrNull(dto.getPhone())) {
            user.setPhone(dto.getPhone());
        }
        if (!StringHelper.isBlankOrEmptyOrNull(dto.getAddress())) {
            corp.setAddress(dto.getAddress());
        }

        if (!StringHelper.isBlankOrEmptyOrNull(dto.getNickName())) {
            user.setNickname(dto.getNickName());
        }

        if (!StringHelper.isBlankOrEmptyOrNull(dto.getUsername())) {
            user.setUsername(dto.getUsername());
        }

        try {
            userMapper.updateById(user);
        } catch (Exception e) {
        }
        try {
            corporationMapper.updateById(corp);
        } catch (Exception e) {
        }


        return vCorpMapper.selectById(dto.getId());
    }

    @Override
    public boolean saveCorporation(Corporation corporation) {
        return false;
    }



    @Override
    public PageVO<VCorp> queryCorporations(CorporationQueryDTO dto) {
        LambdaQueryWrapper<VCorp> qw = new LambdaQueryWrapper<>();
        IPage<VCorp> page = new Page<>(dto.getPageNum(), dto.getPageSize());
        page = vCorpMapper.selectPage(page, qw);
        return PageVO.from(page);
    }

}
