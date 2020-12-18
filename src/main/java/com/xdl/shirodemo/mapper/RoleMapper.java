package com.xdl.shirodemo.mapper;

import com.xdl.shirodemo.entity.Role;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Description：
 * @Author：邹亚平
 * @Date 2020/11/5
 **/

@Repository
public interface RoleMapper {
    List<Role> selectRoleByUserName(String userName);
}
