package com.xdl.shirodemo.service;

import com.xdl.shirodemo.entity.User;


public interface UserService {
    User selectUserByUserName(String userName);
    String getPassWordByName(String name);
    User selectUserByUser(String userName,String passWord);
}
