package com.example.springboot.service.Impl;

import cn.hutool.log.Log;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.springboot.dto.RegisterDTO;
import com.example.springboot.dto.UserDTO;
import com.example.springboot.entity.Corporation;
import com.example.springboot.entity.Student;
import com.example.springboot.entity.User;
import com.example.springboot.mapper.CorporationMapper;
import com.example.springboot.mapper.StudentMapper;
import com.example.springboot.mapper.UserMapper;
import com.example.springboot.service.IAuthenticationService;
import com.example.springboot.service.IUserService;
import com.example.springboot.utils.StringHelper;
import com.example.springboot.utils.Tuple2;
import com.example.springboot.vo.RegisterOrLoginVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class UserServiceImpl implements IUserService {
    private static final Log LOG = Log.get();
    @Autowired
    private IAuthenticationService authenticationService;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private StudentMapper studentMapper;
    @Autowired
    private CorporationMapper corpMapper;

    @Override
    public RegisterOrLoginVO register(RegisterDTO userDTO) {
        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setType(userDTO.getType());
        user.setNickname(userDTO.getNickName());
        user.setPassword(userDTO.getPassword());
        userMapper.insert(user);
        switch (userDTO.getType()) {
            case User.TYPE_CORPORATION:
                Corporation c = new Corporation();
                c.setAddress("???");
                c.setId(user.getId());
                corpMapper.insert(c);
                break;
            case User.TYPE_STUDENT:
                Student s = new Student();
                s.setId(user.getId());
                studentMapper.insert(s);
                break;
            case User.TYPE_ADMIN:
            default:
                break;
        }
        String token = authenticationService.login(user.getUsername(), user.getPassword());
        return new RegisterOrLoginVO(user, token);
    }

    @Override
    public void update(int uid, UserDTO userDTO) {
        User currentUser = authenticationService.getCurrentUser();
        User user = userMapper.selectById(uid);
        if (currentUser.getType() != User.TYPE_ADMIN && currentUser.getId() != user.getId()) {
            throw new RuntimeException("权限不足");
        }
        if (!StringHelper.isBlankOrEmptyOrNull(userDTO.getNickname())) {
            user.setNickname(userDTO.getNickname());
        }
        if (!StringHelper.isBlankOrEmptyOrNull(userDTO.getEmail())) {
            user.setEmail(userDTO.getEmail());
        }
        if (!StringHelper.isBlankOrEmptyOrNull(userDTO.getPhone())) {
            user.setPhone(userDTO.getPhone());
        }
        userMapper.updateById(user);
    }

    @Override
    public List<User> findAllUsers() {
        return userMapper.selectList(new QueryWrapper<>());
    }

    @Override
    public void delete(int[] uids) {
        List<Integer> b = Arrays.stream(uids)
                .boxed()
                .collect(Collectors.toList());
        corpMapper.deleteBatchIds(b);
        userMapper.deleteBatchIds(b);
        studentMapper.deleteBatchIds(b);
    }

    @Override
    public User findById(int uid) {
        return userMapper.selectById(uid);
    }

    @Override
    public Tuple2<User, Student> findStudent(int id) {
        User user = userMapper.selectById(id);
        assert user != null;
        Student student = studentMapper.selectById(id);
        assert student != null;
        return new Tuple2<>(user, student);
    }

    @Override
    public Tuple2<User, Student> findStudent(String username) {
        LambdaQueryWrapper<User> qw = new LambdaQueryWrapper<>();
        qw.eq(User::getUsername,username);
        User user = userMapper.selectOne(qw);
        assert user != null;
        Student c = studentMapper.selectById(user.getId());
        assert c != null;
        return new Tuple2<>(user, c);
    }

    @Override
    public Tuple2<User, Corporation> findCorporation(int id) {
        User user = userMapper.selectById(id);
        assert user != null;
        Corporation c = corpMapper.selectById(id);
        assert c != null;
        return new Tuple2<>(user, c);
    }

    @Override
    public Tuple2<User, Corporation> findCorporation(String username) {
        LambdaQueryWrapper<User> qw = new LambdaQueryWrapper<>();
        qw.eq(User::getUsername,username);
        User user = userMapper.selectOne(qw);
        assert user != null;
        Corporation c = corpMapper.selectById(user.getId());
        assert c != null;
        return new Tuple2<>(user, c);
    }

    @Override
    public List<Tuple2<User, Student>> findAllStudents() {
        LambdaQueryWrapper<User> qw = new LambdaQueryWrapper<>();
        qw.eq(User::getType, User.TYPE_STUDENT);
        return userMapper.selectList(qw).stream().map((u) -> {
            return findStudent(u.getId());
        }).collect(Collectors.toList());
    }

    @Override
    public List<Tuple2<User, Corporation>> findAllCrops() {
        LambdaQueryWrapper<User> qw = new LambdaQueryWrapper<>();
        qw.eq(User::getType, User.TYPE_CORPORATION);
        return userMapper.selectList(qw).stream().map((u) -> {
            return findCorporation(u.getId());
        }).collect(Collectors.toList());
    }
}
