package com.example.springboot.service;

import com.example.springboot.entity.User;

public interface IAuthenticationService {
    String login(String userName,String password);
    User getUserByToken(String token);
    User getCurrentUser();
}
