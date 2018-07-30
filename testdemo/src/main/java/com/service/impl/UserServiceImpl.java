package com.service.impl;

import com.dao.UserDao;
import com.model.User;
import com.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service("userServiceImpl")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    /*
    注册用户
     */
    @Transactional
    @Override
    public String register(String username, String password) {
        User user = userDao.findUser(username);
        //System.out.println(user);
        if(null == user){
            userDao.register(username,password);
            return "0";
        }else{

            return "1";
        }

    }

    @Override
    public User find(String username,String password) {
        try{
            User user = userDao.find(username,password);
            return user;
        }
        catch (Exception e){
            e.printStackTrace();
            return null;
        }

    }

}
