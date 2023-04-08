package com.example.springboot.controller;

import com.example.springboot.common.Result;
import com.example.springboot.entity.User;
import com.example.springboot.service.IApplyService;
import com.example.springboot.service.IAuthenticationService;
import com.example.springboot.vo.ApplyVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/application")
public class ApplyController {
    @Autowired
    private IApplyService applyService;

    @Autowired
    private IAuthenticationService authenticationService;

    @PostMapping("{job_id}/apply")
    public Result<ApplyVO> apply(
            @PathVariable(name = "job_id") int jobId
    ) {
        User user = authenticationService.getCurrentUser();
        assert user.getType() == User.TYPE_STUDENT;
        return Result.success(applyService.apply(user.getId(), jobId));
    }

    @PostMapping("{job_id}/invite/{sid}")
    public Result<ApplyVO> apply(
            @PathVariable(name = "job_id") int jobId,
            @PathVariable(name = "sid") int studentId
    ) {
        User user = authenticationService.getCurrentUser();
        assert user.getType() == User.TYPE_CORPORATION;
        return Result.success(applyService.invite(studentId, jobId));
    }

}
