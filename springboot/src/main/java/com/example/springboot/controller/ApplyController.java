package com.example.springboot.controller;

import com.example.springboot.common.Result;
import com.example.springboot.dto.ApplicationQueryDTO;
import com.example.springboot.dto.InviteDTO;
import com.example.springboot.entity.User;
import com.example.springboot.service.IApplyService;
import com.example.springboot.service.IAuthenticationService;
import com.example.springboot.vo.ApplyVO;
import com.example.springboot.vo.PageVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/application")
public class ApplyController {
    @Autowired
    private IApplyService applyService;

    @Autowired
    private IAuthenticationService authenticationService;

    @GetMapping
    public Result<PageVO<ApplyVO>> queryApplications( ApplicationQueryDTO dto
    ) {
        return Result.success(applyService.query(dto));
    }

    @DeleteMapping("{job_id}")
    public Result<Void> deleteOne(@PathVariable(name = "job_id") int jobId){
        applyService.deleteOne(jobId);
        return Result.success();
    }

    @PostMapping("{job_id}")
    public Result<ApplyVO> apply(
            @PathVariable(name = "job_id") int jobId
    ) {
        User user = authenticationService.getCurrentUser();
        assert user.getType() == User.TYPE_STUDENT;
        return Result.success(applyService.apply(user.getId(), jobId));
    }

    @PostMapping("{job_id}/invite")
    public Result<List<ApplyVO>> invite(
            @PathVariable(name = "job_id") int jobId,
            @RequestBody InviteDTO dto
            ) {
        User user = authenticationService.getCurrentUser();
        assert user.getType() == User.TYPE_CORPORATION;
        return Result.success(applyService.invite(dto.getUids(), jobId));
    }

}
