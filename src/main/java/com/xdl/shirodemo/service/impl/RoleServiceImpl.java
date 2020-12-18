package com.xdl.shirodemo.service.impl;

import com.xdl.shirodemo.entity.Role;
import com.xdl.shirodemo.mapper.RoleMapper;
import com.xdl.shirodemo.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description：
 * @Author：邹亚平
 * @Date 2020/11/5
 **/
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    RoleMapper roleMapper;

    @Override
    public List<Role> selectRoleByUserName(String userName) {
        List<Role> roles = roleMapper.selectRoleByUserName(userName);
        if (roles != null){
            return roles;
        }
        return null;
    }
}
