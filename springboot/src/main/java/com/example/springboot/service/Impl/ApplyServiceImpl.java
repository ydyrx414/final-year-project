package com.example.springboot.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.springboot.dto.ApplicationQueryDTO;
import com.example.springboot.entity.*;
import com.example.springboot.mapper.*;
import com.example.springboot.service.IApplyService;
import com.example.springboot.utils.IntegerHelper;
import com.example.springboot.vo.ApplyVO;
import com.example.springboot.vo.PageVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service
public class ApplyServiceImpl implements IApplyService {
    @Autowired
    private ApplyMapper mapper;
    @Autowired
    private CorporationMapper corporationMapper;
    @Autowired
    private JobMapper jobMapper;
    @Autowired
    private StudentMapper studentMapper;
    @Autowired
    private UserMapper userMapper;

    private ApplyVO toVO(Apply raw) {
        Job job = jobMapper.selectById(raw.getJobId());
        assert job != null;
        Corporation corporation = corporationMapper.selectById(job.getCorporation());
        assert corporation != null;
        Student student = studentMapper.selectById(raw.getStudentId());
        assert student != null;
        User studentUser = userMapper.selectById(raw.getStudentId());
        assert studentUser != null;
        User corporationUser = userMapper.selectById(corporation.getId());
        assert corporationUser != null;
        return ApplyVO.builder()
                .studentId(studentUser.getId())
                .studentName(studentUser.getNickname())
                .corporationId(corporation.getId())
                .corporationName(corporationUser.getNickname())
                .jobId(job.getId())
                .jobName(job.getName())
                .createdTime(raw.getTime())
                .type(raw.getType())
                .build();
    }

    @Override
    public ApplyVO apply(int studentId, int jobId) {
        Apply apply = new Apply();
        apply.setStudentId(studentId);
        apply.setJobId(jobId);
        apply.setTime(new Timestamp(System.currentTimeMillis()));
        apply.setType(Apply.TYPE_APPLY);
        mapper.insert(apply);
        return toVO(apply);
    }

    @Override
    public ApplyVO invite(int studentId, int jobId) {
        Apply apply = new Apply();
        apply.setStudentId(studentId);
        apply.setJobId(jobId);
        apply.setTime(new Timestamp(System.currentTimeMillis()));
        apply.setType(Apply.TYPE_INVITATION);
        mapper.insert(apply);
        return toVO(apply);
    }

    @Override
    public PageVO<ApplyVO> query(ApplicationQueryDTO dto) {
        LambdaQueryWrapper<Apply> qw = new LambdaQueryWrapper<>();
        if (IntegerHelper.isValid(dto.getJobId())) {
            qw.eq(Apply::getJobId, dto.getCorporationId());
        }
        if (IntegerHelper.isValid(dto.getStudentId())) {
            qw.eq(Apply::getStudentId, dto.getCorporationId());
        }
        IPage<Apply> page = new Page<>(dto.getPageNum(), dto.getPageSize());
        page = mapper.selectPage(page, qw);
        return PageVO.from(page, this::toVO);
    }
}
