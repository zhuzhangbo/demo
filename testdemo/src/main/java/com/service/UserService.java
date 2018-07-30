package com.service;

import com.model.User;

public interface UserService {
    //用户注册
    String register(String username, String password);

    //用户登录
    User find(String username,String password);
}
