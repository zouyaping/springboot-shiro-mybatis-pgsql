package com.xdl.shirodemo.service;

import com.xdl.shirodemo.entity.Role;

import java.util.List;


public interface RoleService {
    List<Role> selectRoleByUserName(String userName);
}
