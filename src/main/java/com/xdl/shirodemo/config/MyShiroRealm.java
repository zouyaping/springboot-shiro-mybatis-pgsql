package com.xdl.shirodemo.config;

import com.xdl.shirodemo.entity.AuthToken;
import com.xdl.shirodemo.entity.Permission;
import com.xdl.shirodemo.entity.Role;
import com.xdl.shirodemo.entity.User;
import com.xdl.shirodemo.service.PermissionService;
import com.xdl.shirodemo.service.RoleService;
import com.xdl.shirodemo.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @Description：
 * @Author：邹亚平
 * @Date 2020/11/5
 **/
public class MyShiroRealm extends AuthorizingRealm {
    @Autowired
    RoleService roleService;

    @Autowired
    PermissionService permissionService;

    @Autowired
    UserService userService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
       // System.out.println("============用户授权==============");
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        /*获取当前的用户,已经登录后可以使用在任意的地方获取用户的信息*/
        //String username = (String) SecurityUtils.getSubject().getPrincipal();
        String userName = (String) principalCollection.getPrimaryPrincipal();
        /*查询用户的权限*/
        try {
            List<Role> roles = roleService.selectRoleByUserName(userName);
            for (Role role:roles){
                // 角色存储
                authorizationInfo.addRole(role.getRoleName());
            }
            List<Permission> permissions = permissionService.selectPermByUserName(userName);
            for (Permission permission:permissions){
                // 权限存储
                authorizationInfo.addStringPermission(permission.getPermissionsName());
               // System.out.println(permission.getPermissionsName());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return authorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        // System.out.println("============用户验证==============");
        //从token中获取信息,此token只是shiro用于身份验证的,并非前端传过来的token.
        AuthToken token = (AuthToken) authenticationToken;
        String userName = (String) token.getPrincipal();
        /*String password = userService.getPassWordByName(userName);
        if (null == password) {
            throw new AuthenticationException("doGetAuthenticationInfo中的用户名不对");
        } else if (!password.equals(new String(token.getPassword()))){
            throw new AuthenticationException("doGetAuthenticationInfo中的密码不对");
        }*/
        // 组合一个验证信息
        // System.out.println("token.getPrincipal()默认返回的username======"+userName);
        // System.out.println("getName()"+getName());
        SimpleAuthenticationInfo authenticationInfo =
                new SimpleAuthenticationInfo(userName,token.getCredentials(),getName());
        return authenticationInfo;
    }

}
