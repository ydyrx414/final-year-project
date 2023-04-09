package com.example.springboot.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.springboot.dto.ApplicationQueryDTO;
import com.example.springboot.entity.*;
import com.example.springboot.mapper.*;
import com.example.springboot.service.IApplyService;
import com.example.springboot.service.IAuthenticationService;
import com.example.springboot.utils.IntegerHelper;
import com.example.springboot.vo.ApplyVO;
import com.example.springboot.vo.PageVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

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

    private ApplyVO toVO(VApplication raw) {
        return ApplyVO.builder()
                .id(raw.getId())
                .studentId(raw.getStudentId())
                .studentName(raw.getStudentName())
                .corporationId(raw.getCorpId())
                .corporationName(raw.getCorpName())
                .jobId(raw.getJobId())
                .jobName(raw.getJobName())
                .createdTime(raw.getTime())
                .type(raw.getType())
                .build();
    }

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
                .id(raw.getId())
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
    public void deleteOne(int jobId) {
        mapper.deleteById(jobId);
    }

    @Override
    public List<ApplyVO> invite(int[] studentIds, int jobId) {
        List<ApplyVO> vos = new ArrayList<>(studentIds.length);
        for (int id : studentIds) {
            Apply apply = new Apply();
            apply.setStudentId(id);
            apply.setJobId(jobId);
            apply.setTime(new Timestamp(System.currentTimeMillis()));
            apply.setType(Apply.TYPE_INVITATION);
            mapper.insert(apply);
            vos.add(toVO(apply));
        }
        return vos;
    }

    @Autowired
    private IAuthenticationService authenticationService;

    @Autowired
    private VAppMapper vAppMapper;

    @Override
    public PageVO<ApplyVO> query(ApplicationQueryDTO dto) {
        LambdaQueryWrapper<VApplication> qw = new LambdaQueryWrapper<>();

        if (IntegerHelper.isValid(dto.getStudentId())) {
            qw.eq(VApplication::getStudentId, dto.getStudentId());
        }

        if (IntegerHelper.isValid(dto.getJobId())) {
            qw.eq(VApplication::getJobId, dto.getJobId());
        }

        if (IntegerHelper.isValid(dto.getCorporationId())) {
            qw.eq(VApplication::getCorpId, dto.getCorporationId());
        }

        IPage<VApplication> page = new Page<>(dto.getPageNum(), dto.getPageSize());
        page = vAppMapper.selectPage(page, qw);

        return PageVO.from(page, this::toVO);
    }
}
