package com.example.springboot.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.springboot.common.Constants;
import com.example.springboot.common.Result;
import com.example.springboot.dto.LoginDTO;
import com.example.springboot.dto.RegisterDTO;
import com.example.springboot.entity.Student;
import com.example.springboot.entity.User;
import com.example.springboot.dto.UserDTO;
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

    //批量删除
    @DeleteMapping("/batch")
    public Result deleteBatch(@RequestBody int[] ids) {
        userService.delete(ids);
        return Result.success();
    }

    //分页查询，mabatis-plus的方式
    @GetMapping("/page")
    public Result<PageVO<User>> findPage(@RequestParam Integer pageNum,
                                         @RequestParam Integer pageSize,
                                         @RequestParam(defaultValue = "") String username,
                                         @RequestParam(defaultValue = "") String nickname,
                                         @RequestParam(defaultValue = "") String email,
                                         @RequestParam(defaultValue = "") String phone) {
//        IPage<User> page = new Page<>(pageNum,pageSize);
//        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
//        queryWrapper.like(!Strings.isEmpty(username),"username",username);
//        queryWrapper.like(!Strings.isEmpty(email),"nickname",nickname);
//        queryWrapper.like(!Strings.isEmpty(email),"email",email);
//        queryWrapper.like(!Strings.isEmpty(phone),"phone",phone);
//        return Result.success(userService.page(page, queryWrapper));
        return null;
    }


}
