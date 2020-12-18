package com.xdl.shirodemo.mapper;

import com.xdl.shirodemo.entity.User;
import org.springframework.stereotype.Repository;


@Repository
public interface UserMapper {
    User selectUserByUserName(String userName);
    User selectUserByUser(String userName,String passWord);
    String getPassWordByName(String name);
}
