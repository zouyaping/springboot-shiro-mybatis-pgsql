package com.xdl.shirodemo.mapper;


import com.xdl.shirodemo.entity.Permission;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PermissionMapper {
    List<Permission> selectPermByUserName(String userName);
}
