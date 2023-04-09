package com.example.springboot.service;

import com.example.springboot.dto.ApplicationQueryDTO;
import com.example.springboot.entity.Apply;
import com.example.springboot.vo.ApplyVO;
import com.example.springboot.vo.PageVO;

import java.util.List;

public interface IApplyService {
    ApplyVO apply(int studentId, int jobId);
    List<ApplyVO> invite(int[] studentIds,int jobId);
    PageVO<ApplyVO> query( ApplicationQueryDTO dto);
}
