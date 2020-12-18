package com.xdl.shirodemo.controller;


import com.xdl.shirodemo.entity.Result;
import com.xdl.shirodemo.entity.User;
import com.xdl.shirodemo.service.UserService;
import com.xdl.shirodemo.utils.TokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@Controller
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping(value = "/api/user/login", method = RequestMethod.POST)
    @ResponseBody
    public Result login(@RequestBody User user, HttpServletRequest request, HttpServletResponse response) {
        try {
            User relaUser = userService.selectUserByUser(user.getUserName(),user.getPassWord());
            if (null != relaUser){
                String token = TokenUtil.getToken(relaUser.getUserName(), String.valueOf(relaUser.getId()), request.getRemoteAddr());
                log.info(relaUser.getUserName()+"登录成功");
                return Result.success("登录成功",token);
            }
            log.error(relaUser.getUserName()+"登录失败");
            return Result.error("登录失败");
        } catch (Exception e) {
            e.printStackTrace();
            log.error("登录失败");
            return Result.error("登录失败,用户名或密码错误！！！");
        }
    }
}
