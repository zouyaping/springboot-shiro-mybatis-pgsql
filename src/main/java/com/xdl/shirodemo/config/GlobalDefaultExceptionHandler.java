package com.xdl.shirodemo.config;

import com.xdl.shirodemo.entity.Result;
import io.jsonwebtoken.ExpiredJwtException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalDefaultExceptionHandler {


    @ExceptionHandler(UnauthorizedException.class)
    @ResponseBody
    public Result unauthorizedExceptionHandler(HttpServletRequest req, Exception e){
        return Result.error("权限不足 !!!");
    }

    @ExceptionHandler(ExpiredJwtException.class)
    @ResponseBody
    public Result expiredJwtExceptionHandler(HttpServletRequest req,Exception e){
        return Result.error("认证已过期，请重新登录！！！");
    }


}

