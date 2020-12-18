package com.xdl.shirodemo.controller;

import com.xdl.shirodemo.entity.Result;
import com.xdl.shirodemo.entity.User;
import com.xdl.shirodemo.service.UserService;
import com.xdl.shirodemo.utils.TokenUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Controller
public class LoginController {

    @Autowired
    UserService userService;

    @RequiresPermissions("insert")
    @RequestMapping(value = "/hello",method = RequestMethod.GET)
    @ResponseBody
    public Result getHello(){
        return Result.successWithData("hello");
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String notLogin() {
        return "login";
    }

    @RequestMapping("/main")
    @ResponseBody
    public String test() {
        return "main";
    }

    @RequestMapping(value = "/noAuthorize")
    @ResponseBody
    public Result notRole() {
        return Result.error("没有权限");
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    @ResponseBody
    public String logout() {
        Subject subject = SecurityUtils.getSubject();
        //注销
        subject.logout();
        return "成功注销！";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public Result login(@RequestBody User user, HttpServletRequest request, HttpServletResponse response) {
        try {
            Subject subject = SecurityUtils.getSubject();
            // UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(user.getUserName(), user.getPassWord());
            // subject.login(usernamePasswordToken);
            User relaUser = userService.selectUserByUser(user.getUserName(),user.getPassWord());
            if (null != relaUser){
                String token = TokenUtil.getToken(relaUser.getUserName(), String.valueOf(relaUser.getId()), request.getRemoteAddr());
                return Result.success("登录成功",token);
            }
            return Result.error("登录失败");

        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("登录失败");
        }
    }
}
