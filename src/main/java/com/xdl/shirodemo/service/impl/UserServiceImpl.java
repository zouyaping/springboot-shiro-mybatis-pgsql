package com.xdl.shirodemo.service.impl;

import com.xdl.shirodemo.entity.User;
import com.xdl.shirodemo.mapper.UserMapper;
import com.xdl.shirodemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Override
    public User selectUserByUserName(String userName) {
        User user = userMapper.selectUserByUserName(userName);
        if (user != null){
            return user;
        }
        return null;
    }

    @Override
    public String getPassWordByName(String name) {
        String passWord = userMapper.getPassWordByName(name);
        if (passWord != null){
            return passWord;
        }
        return null;
    }

    @Override
    public User selectUserByUser(String userName, String passWord) {
        User user = userMapper.selectUserByUser(userName, passWord);
        return user != null?user:null;
    }
}
