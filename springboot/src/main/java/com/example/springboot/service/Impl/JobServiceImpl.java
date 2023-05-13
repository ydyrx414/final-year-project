package com.example.springboot.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.springboot.dto.JobQueryDTO;
import com.example.springboot.dto.PostJobDTO;
import com.example.springboot.entity.Corporation;
import com.example.springboot.entity.Job;
import com.example.springboot.entity.User;
import com.example.springboot.mapper.JobMapper;
import com.example.springboot.service.IAuthenticationService;
import com.example.springboot.service.ICorporationService;
import com.example.springboot.service.IJobService;
import com.example.springboot.service.IUserService;
import com.example.springboot.utils.StringHelper;
import com.example.springboot.utils.Tuple2;
import com.example.springboot.vo.JobVO;
import com.example.springboot.vo.PageVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JobServiceImpl implements IJobService {

    @Autowired
    private JobMapper mapper;
    @Autowired
    private IUserService userService;
    @Autowired
    private ICorporationService corporationService;
    @Autowired
    private IAuthenticationService authenticationService;

    @Override
    public PageVO<JobVO> queryJobs(JobQueryDTO dto) {

        LambdaQueryWrapper<Job> qw = new LambdaQueryWrapper<>();

        if (!StringHelper.isBlankOrEmptyOrNull(dto.getExpTime())) {
            qw.like(Job::getExptime, dto.getExpTime());
        }
        if (!StringHelper.isBlankOrEmptyOrNull(dto.getName())) {
            qw.like(Job::getName, dto.getName());
        }

        if (dto.getView() == JobQueryDTO.VIEW_MY_POSTS) {
            User user = authenticationService.getCurrentUser();
            qw.eq(Job::getCorporation, user.getId());
        }

        IPage<Job> page = new Page<>(dto.getPageNum(), dto.getPageSize());
        IPage<Job> result = mapper.selectPage(page, qw);

        return PageVO.from(result, (job) -> {
            Tuple2<User, Corporation> c = userService.findCorporation(job.getCorporation());
            return new JobVO(job, c.getA().getNickname(), c.getB().getAddress(), c.getA().getPhone(),c.getA().getEmail());
        });
    }

    @Override
    public JobVO postJob(PostJobDTO dto) {
        // 检查公司是否存在
        userService.findCorporation(dto.getCorporationId());

        Job job = new Job();
        job.setCorporation(dto.getCorporationId());
        job.setExptime(dto.getExpTime());
        job.setName(dto.getName());
        mapper.insert(job);
        Tuple2<User, Corporation> c = userService.findCorporation(job.getCorporation());
        return new JobVO(job, c.getA().getNickname(), c.getB().getAddress(), c.getA().getPhone(),c.getA().getEmail());
    }

    @Override
    public JobVO updateJob(String id, PostJobDTO dto) {
        Job job = mapper.selectById(id);
        if (job == null) {
            throw new RuntimeException("No such job");
        }
        job.setName(dto.getName());
        job.setCorporation(dto.getCorporationId());
        job.setExptime(dto.getExpTime());
        mapper.updateById(job);
        Tuple2<User, Corporation> c = userService.findCorporation(job.getCorporation());
        return new JobVO(job, c.getA().getNickname(), c.getB().getAddress(), c.getA().getPhone(),c.getA().getEmail());
    }

    @Override
    public void deleteJob(String id) {
        mapper.deleteById(id);
    }


}
