package com.example.springboot.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.springboot.common.Constants;
import com.example.springboot.common.Result;
import com.example.springboot.dto.LoginDTO;
import com.example.springboot.dto.RegisterDTO;
import com.example.springboot.dto.UserQueryDTO;
import com.example.springboot.entity.Student;
import com.example.springboot.entity.User;
import com.example.springboot.dto.UserDTO;
import com.example.springboot.entity.VCorp;
import com.example.springboot.entity.VStudent;
import com.example.springboot.mapper.VCorpMapper;
import com.example.springboot.mapper.VStudentMapper;
import com.example.springboot.service.IAuthenticationService;
import com.example.springboot.service.IUserService;
import com.example.springboot.utils.Tuple2;
import com.example.springboot.vo.PageVO;
import com.example.springboot.vo.RegisterOrLoginVO;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import javax.annotation.Resource;
import java.util.List;


@CrossOrigin
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private IUserService userService;

    @Autowired
    private VStudentMapper vStudentMapper;

    @Autowired
    private VCorpMapper vCorpMapper;

    @Autowired
    private IAuthenticationService authenticationService;

    @PostMapping("login")
    public Result<RegisterOrLoginVO> login(@RequestBody LoginDTO userDTO) {
        String token = authenticationService.login(userDTO.getUsername(), userDTO.getPassword());
        User user = authenticationService.getUserByToken(token);
        return Result.success(new RegisterOrLoginVO(user, token));
    }

    @PostMapping("register")
    public Result<RegisterOrLoginVO> register(@RequestBody RegisterDTO userDTO) {
        String username = userDTO.getUsername();
        String password = userDTO.getPassword();
        if (StrUtil.isBlank(username) || StrUtil.isBlank(password)) {
            return Result.error(Constants.CODE_400, "参数错误");
        }
        return Result.success(userService.register(userDTO));
    }

    //查询所有数据
    @GetMapping
    public Result<List<User>> findAll() {
        return Result.success(userService.findAllUsers());
    }

    //修改
    @PostMapping("/{id}")
    public Result<Void> save(
            @PathVariable int id,
            @RequestBody UserDTO user) {
        userService.update(id, user);
        return Result.success();
    }

    @DeleteMapping("/{id}")
    public Result<Void> delete(@PathVariable int id) {
        userService.delete(id);
        return Result.success();
    }


    @GetMapping("/{id}")
    public Result<User> findOne(@PathVariable int id) {
        return Result.success(userService.findById(id));
    }

    @GetMapping("/{id}/as-student")
    public Result<VStudent> findOneAsStudent(@PathVariable int id) {
        return Result.success(vStudentMapper.selectById(id));
    }
    @GetMapping("/{id}/as-corp")
    public Result<VCorp> findOneAsCorp(@PathVariable int id) {
        return Result.success(vCorpMapper.selectById(id));
    }



    //批量删除
    @DeleteMapping("/batch")
    public Result deleteBatch(@RequestBody int[] ids) {
        userService.delete(ids);
        return Result.success();
    }

    //分页查询，mabatis-plus的方式
    @GetMapping("/page")
    public Result<PageVO<User>> findPage(UserQueryDTO dto) {
        return Result.success(userService.queryUsers(dto));
    }


}
