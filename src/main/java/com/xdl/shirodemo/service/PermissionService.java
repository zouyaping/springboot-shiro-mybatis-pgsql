package com.xdl.shirodemo.service;

import com.xdl.shirodemo.entity.Permission;
import com.xdl.shirodemo.entity.Role;

import java.util.List;


public interface PermissionService {
    List<Permission> selectPermByUserName(String userName);
}
