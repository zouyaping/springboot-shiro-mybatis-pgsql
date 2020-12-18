package com.xdl.shirodemo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @Description：权限实体类
 * @Author：邹亚平
 * @Date 2020/10/28
 **/

@Data
@AllArgsConstructor
public class Permission {
    private int id;
    private String permissionsName;
}
