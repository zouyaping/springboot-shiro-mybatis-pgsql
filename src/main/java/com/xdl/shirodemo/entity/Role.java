package com.xdl.shirodemo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Set;

/**
 * @Description：角色对应实体类
 * @Author：邹亚平
 * @Date 2020/10/28
 **/

@Data
@AllArgsConstructor
public class Role {
    private int id;
    private String roleName;
}
