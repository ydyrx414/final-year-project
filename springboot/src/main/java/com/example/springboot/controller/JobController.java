package com.example.springboot.controller;

import com.example.springboot.common.Result;
import com.example.springboot.dto.JobQueryDTO;
import com.example.springboot.dto.PostJobDTO;
import com.example.springboot.service.IJobService;
import com.example.springboot.vo.JobVO;
import com.example.springboot.vo.PageVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/job")
public class JobController {
    @Autowired
    private IJobService jobService;

    @GetMapping
    public Result<PageVO<JobVO>> queryJobs(JobQueryDTO dto){
        return Result.success(jobService.queryJobs(dto));
    }

    @PostMapping("{id}")
    public Result<JobVO> postJob(
            @PathVariable String id,
            @RequestBody PostJobDTO dto){
        return Result.success(jobService.updateJob(id,dto));
    }

    @PostMapping
    public Result<JobVO> postJob(
            @RequestBody PostJobDTO dto){
        return Result.success(jobService.postJob(dto));
    }

    @DeleteMapping("{id}")
    public Result<Void> deleteJob(@PathVariable String id){
        jobService.deleteJob(id);
        return Result.success();
    }
}
