package com.xdl.shirodemo.config;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.xdl.shirodemo.entity.AuthToken;
import com.xdl.shirodemo.entity.Result;
import com.xdl.shirodemo.utils.TokenUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.AuthenticatingFilter;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

public class AuthFilter extends AuthenticatingFilter {

    // 定义jackson对象
    private static final ObjectMapper MAPPER = new ObjectMapper();

    @Override
    protected AuthenticationToken createToken(ServletRequest request, ServletResponse response) throws Exception {
        //获取请求token
        String token = TokenUtil.getRequestToken((HttpServletRequest) request);
        return new AuthToken(token);
    }


    @Override
    protected boolean onAccessDenied(ServletRequest servletRequest, ServletResponse servletResponse) throws Exception {
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String token = TokenUtil.getRequestToken((HttpServletRequest) servletRequest);
        if (StringUtils.isEmpty(token)) {
            HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;
            httpResponse.setHeader("Access-Control-Allow-Credentials", "true");
            // httpResponse.setHeader("Access-Control-Allow-Origin", HttpContextUtil.getOrigin());
            httpResponse.setCharacterEncoding("UTF-8");
            Map<String, Object> result = new HashMap<>();
            result.put("status", 400);
            result.put("msg", "请先登录");
            String json = MAPPER.writeValueAsString(result);
            httpResponse.getWriter().print(json);
            return false;
        }
        return executeLogin(request, response);
    }

    @Override
    protected boolean isAccessAllowed(ServletRequest servletRequest, ServletResponse servletResponse, Object mappedValue) {
        /*HttpServletRequest request = (HttpServletRequest) servletRequest;
        String token = request.getHeader(TokenUtil.tokenHeard);
        System.out.println("================"+token);
        if (null == token||"".equals(token)) {
            System.out.println("-------------------token为空");
            return false;
        }
        //验证token的真实性
        try {
            TokenUtil.getTokenBody(token);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("----------------token有问题");
            return false;
        }*/
        if (((HttpServletRequest) servletRequest).getMethod().equals(RequestMethod.OPTIONS.name())) {
            return true;
        }
        String servletPath = ((HttpServletRequest) servletRequest).getServletPath();
        if(servletPath.equals("/api/user/login")){
            return true;
        }
        return false;
        // return true;
    }

    protected boolean executeLogin(ServletRequest request, ServletResponse response) throws Exception {
        // AuthenticationToken token = createToken(request, response);
        AuthToken token = (AuthToken)createToken(request, response);
        if (token == null) {
            String msg = "createToken method implementation returned null. A valid non-null AuthenticationToken " +
                    "must be created in order to execute a login attempt.";
            throw new IllegalStateException(msg);
        }
        try {
            Subject subject = getSubject(request, response);
            String token1 = token.getToken();
            Claims tokenBody = TokenUtil.getTokenBody(token1);
            if(tokenBody != null) {
                //重点！
                subject.login(token);
                return onLoginSuccess(token, subject, request, response);
            }
            return false;
        } catch (AuthenticationException e) {
            return onLoginFailure(token, e, request, response);
        } catch (ExpiredJwtException e){
            HttpServletResponse httpResponse = (HttpServletResponse) response;
            httpResponse.setHeader("Access-Control-Allow-Credentials", "true");
            // httpResponse.setHeader("Access-Control-Allow-Origin", HttpContextUtil.getOrigin());
            httpResponse.setCharacterEncoding("UTF-8");
            httpResponse.getWriter().print(JSONObject.toJSONString(Result.tokenExpire()));
            return false;
        }
    }
}
