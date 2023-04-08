package com.example.springboot.service;

import com.example.springboot.dto.JobQueryDTO;
import com.example.springboot.dto.PostJobDTO;
import com.example.springboot.vo.JobVO;
import com.example.springboot.vo.PageVO;

public interface IJobService {
    PageVO<JobVO> queryJobs(JobQueryDTO dto);
    JobVO postJob(PostJobDTO dto);
    JobVO updateJob(String id,PostJobDTO dto);
    void deleteJob(String id);
}
