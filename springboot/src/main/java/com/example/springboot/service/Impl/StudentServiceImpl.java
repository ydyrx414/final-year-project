package com.example.springboot.service.Impl;

import cn.hutool.log.Log;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.springboot.common.Constants;
import com.example.springboot.dto.StudentDTO;
import com.example.springboot.dto.StudentQueryDTO;
import com.example.springboot.dto.UpdateStudentDTO;
import com.example.springboot.entity.Student;
import com.example.springboot.entity.User;
import com.example.springboot.entity.VStudent;
import com.example.springboot.exception.ServiceException;
import com.example.springboot.mapper.StudentMapper;
import com.example.springboot.mapper.UserMapper;
import com.example.springboot.mapper.VStudentMapper;
import com.example.springboot.service.IStudentService;
import com.example.springboot.service.IUserService;
import com.example.springboot.utils.StringHelper;
import com.example.springboot.utils.Tuple2;
import com.example.springboot.vo.PageVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

@Service
public class StudentServiceImpl implements IStudentService {
    @Autowired
    private VStudentMapper vStudentMapper;
    @Autowired
    private IUserService userService;
    @Autowired
    private StudentMapper studentMapper;
    @Autowired
    private UserMapper userMapper;

    @Override
    public VStudent update(UpdateStudentDTO dto) {
        Tuple2<User, Student> t = userService.findStudent(dto.getId());
        User user = t.getA();
        Student student = t.getB();

        if (!StringHelper.isBlankOrEmptyOrNull(dto.getEmail())) {
            user.setEmail(dto.getEmail());
        }
        if (!StringHelper.isBlankOrEmptyOrNull(dto.getPhone())) {
            user.setPhone(dto.getPhone());
        }
        if (!StringHelper.isBlankOrEmptyOrNull(dto.getIntention())) {
            student.setIntention(dto.getIntention());
        }
        if (!StringHelper.isBlankOrEmptyOrNull(dto.getNature())) {
            student.setNature(dto.getNature());
        }
        if (!StringHelper.isBlankOrEmptyOrNull(dto.getSubject())) {
            student.setSubject(dto.getSubject());
        }
        if (!StringHelper.isBlankOrEmptyOrNull(dto.getNickName())) {
            user.setNickname(dto.getNickName());
        }
        if (!StringHelper.isBlankOrEmptyOrNull(dto.getUsername())) {
            user.setUsername(dto.getUsername());
        }

        try {
            userMapper.updateById(user);
        } catch (Exception e) {
        }
        try {
            studentMapper.updateById(student);
        } catch (Exception e) {
        }


        return vStudentMapper.selectById(dto.getId());
    }

    @Override
    public PageVO<VStudent> queryStudent(StudentQueryDTO dto) {

        LambdaQueryWrapper<VStudent> qw = new LambdaQueryWrapper<>();

        if (!StringHelper.isBlankOrEmptyOrNull(dto.getEmail())) {
            qw.like(VStudent::getEmail, dto.getEmail());
        }
        if (!StringHelper.isBlankOrEmptyOrNull(dto.getPhone())) {
            qw.like(VStudent::getPhone, dto.getPhone());
        }
        if (!StringHelper.isBlankOrEmptyOrNull(dto.getNickname())) {
            qw.like(VStudent::getNickname, dto.getNickname());
        }

        if (!StringHelper.isBlankOrEmptyOrNull(dto.getNature())) {
            qw.like(VStudent::getNature, dto.getNature());
        }
        if (!StringHelper.isBlankOrEmptyOrNull(dto.getSubject())) {
            qw.like(VStudent::getSubject, dto.getSubject());
        }

        if (!StringHelper.isBlankOrEmptyOrNull(dto.getIntention())) {
            qw.like(VStudent::getIntention, dto.getIntention());
        }

        IPage<VStudent> page = new Page<>(dto.getPageNum(), dto.getPageSize());
        IPage<VStudent> result = vStudentMapper.selectPage(page, qw);
        return PageVO.from(result);

    }
}
