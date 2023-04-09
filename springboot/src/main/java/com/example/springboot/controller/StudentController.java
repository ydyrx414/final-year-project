package com.example.springboot.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.springboot.common.Constants;
import com.example.springboot.common.Result;
import com.example.springboot.dto.UpdateStudentDTO;
import com.example.springboot.entity.Student;
import com.example.springboot.dto.StudentDTO;
import com.example.springboot.dto.StudentQueryDTO;
import com.example.springboot.entity.User;
import com.example.springboot.entity.VStudent;
import com.example.springboot.service.IStudentService;
import com.example.springboot.service.IUserService;
import com.example.springboot.utils.Tuple2;
import com.example.springboot.vo.PageVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/student")
public class StudentController {
    @Resource
    private IStudentService studentService;
    @Autowired
    private IUserService userService;

    // 修改
    @PostMapping
    public Result<VStudent> save(@RequestBody UpdateStudentDTO dto) {
        return Result.success(studentService.update(dto));
    }

    @GetMapping("/username/{username}")
    public Result<Tuple2<User, Student>> findOne(@PathVariable String username) {
        return Result.success(userService.findStudent(username));
    }


    //分页查询，mabatis-plus的方式
    @GetMapping("/page")
    public Result<PageVO<VStudent>> findPage(StudentQueryDTO dto) {
        return Result.success(studentService.queryStudent(dto));
    }
}
