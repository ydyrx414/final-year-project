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
import com.example.springboot.entity.Student;
import com.example.springboot.entity.User;
import com.example.springboot.entity.VStudent;
import com.example.springboot.exception.ServiceException;
import com.example.springboot.mapper.StudentMapper;
import com.example.springboot.mapper.VStudentMapper;
import com.example.springboot.service.IStudentService;
import com.example.springboot.utils.StringHelper;
import com.example.springboot.utils.Tuple2;
import com.example.springboot.vo.PageVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

@Service
public class StudentServiceImpl  implements IStudentService {
    private static final Log LOG = Log.get();

    @Autowired
    private VStudentMapper vStudentMapper;

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
