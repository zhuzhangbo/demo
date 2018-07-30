package com.dao;

import com.model.User;

public interface UserDao {

    //用户注册验证
    void register(String username, String password);
    User findUser(String username);


    //用户登录验证
    User find(String username, String password);


}
