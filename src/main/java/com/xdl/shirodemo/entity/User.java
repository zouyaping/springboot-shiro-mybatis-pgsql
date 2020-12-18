package com.xdl.shirodemo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Set;

/**
 * @Description：用户实体类
 * @Author：邹亚平
 * @Date 2020/10/28
 **/
@Data
@AllArgsConstructor
public class User {
    private int id;
    private String userName;
    private String passWord;

}
