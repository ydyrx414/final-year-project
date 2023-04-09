package com.example.springboot.service;

import cn.hutool.core.lang.Tuple;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.springboot.dto.RegisterDTO;
import com.example.springboot.dto.UserDTO;
import com.example.springboot.dto.UserQueryDTO;
import com.example.springboot.entity.Corporation;
import com.example.springboot.entity.Student;
import com.example.springboot.entity.User;
import com.example.springboot.utils.Tuple2;
import com.example.springboot.vo.PageVO;
import com.example.springboot.vo.RegisterOrLoginVO;

import java.util.List;


public interface IUserService {

    RegisterOrLoginVO register(RegisterDTO userDTO);

    void update(int uid, UserDTO userDTO);

    List<User> findAllUsers();

    void delete(int... uids);

    User findById(int uid);
    PageVO<User> queryUsers(UserQueryDTO dto);

    Tuple2<User, Student> findStudent(int id);
    Tuple2<User,Student> findStudent(String username);

    Tuple2<User, Corporation> findCorporation(int id);
    Tuple2<User,Corporation> findCorporation(String username);

    List<Tuple2<User, Student>> findAllStudents();

    List<Tuple2<User, Corporation>> findAllCrops();
}
