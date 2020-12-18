package com.xdl.shirodemo.entity;

import com.xdl.shirodemo.utils.TokenUtil;
import org.apache.shiro.authc.UsernamePasswordToken;

public class AuthToken extends UsernamePasswordToken {

    private String token;

    public AuthToken(String token) {
        this.token = token;
    }

    @Override
    public Object getPrincipal() {
        return TokenUtil.getTokenBody(token).getSubject();
    }
    public String getToken(){
        return this.token;
    }
    @Override
    public Object getCredentials() {
        return TokenUtil.getTokenBody(token).getId();
    }
}
