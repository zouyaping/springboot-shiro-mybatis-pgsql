package com.xdl.shirodemo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @Description：
 * @Author：邹亚平
 * @Date 2020/11/5
 **/

@Data
@AllArgsConstructor
public class RolePermission {
    private int id;
    private int roleId;
    private int permissionId;
}
