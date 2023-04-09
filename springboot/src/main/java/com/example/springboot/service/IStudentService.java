package com.example.springboot.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.springboot.dto.UpdateStudentDTO;
import com.example.springboot.entity.Student;
import com.example.springboot.dto.StudentDTO;
import com.example.springboot.dto.StudentQueryDTO;
import com.example.springboot.entity.User;
import com.example.springboot.entity.VStudent;
import com.example.springboot.utils.Tuple2;
import com.example.springboot.vo.PageVO;

public interface IStudentService {

    VStudent update(UpdateStudentDTO dto);

    PageVO<VStudent> queryStudent(StudentQueryDTO dto);
}
