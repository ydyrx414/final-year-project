package com.example.springboot.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.springboot.entity.User;
import com.example.springboot.mapper.UserMapper;
import com.example.springboot.service.IAuthenticationService;
import com.example.springboot.service.IUserService;
import com.example.springboot.utils.StringHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class AuthenticationServiceImpl implements IAuthenticationService {

    @Autowired
    private UserMapper userMapper;
    private Map<String, Integer> tokenMap = new HashMap<>();

    private String nextToken() {
        return UUID.randomUUID().toString();
    }

    @Override
    public String login(String userName, String password) {
        LambdaQueryWrapper<User> qw = new LambdaQueryWrapper<>();
        qw.eq(User::getUsername, userName);
        qw.eq(User::getPassword, password);
        User user = userMapper.selectOne(qw);
        assert user != null;
        String token = nextToken();
        tokenMap.put(token, user.getId());
        return token;
    }

    @Override
    public User getUserByToken(String token) {
        assert !StringHelper.isBlankOrEmptyOrNull(token);
        Integer id = tokenMap.get(token);
        assert id != null;
        return userMapper.selectById(id);
    }

    @Override
    public User getCurrentUser() {
        final ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder
                .currentRequestAttributes();
        final HttpServletRequest request = attr.getRequest();
        String token = request.getHeader("token");
        return getUserByToken(token);
    }
}
