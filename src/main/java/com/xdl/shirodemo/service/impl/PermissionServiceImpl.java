package com.xdl.shirodemo.service.impl;

import com.xdl.shirodemo.entity.Permission;
import com.xdl.shirodemo.entity.Role;
import com.xdl.shirodemo.mapper.PermissionMapper;
import com.xdl.shirodemo.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    PermissionMapper permissionMapper;

    @Override
    public List<Permission> selectPermByUserName(String userName) {
        List<Permission> permissions = permissionMapper.selectPermByUserName(userName);
        if (permissions != null){
            return permissions;
        }
        return null;
    }
}
