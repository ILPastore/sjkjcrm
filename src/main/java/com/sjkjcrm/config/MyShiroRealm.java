package com.sjkjcrm.config;

import com.sjkjcrm.bean.permisson.Permission;
import com.sjkjcrm.bean.permisson.Role;
import com.sjkjcrm.bean.permisson.User;
import com.sjkjcrm.service.ILoginService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author: xianyunpeng
 * 实现AuthorizingRealm接口用户用户认证
 */
public class MyShiroRealm extends AuthorizingRealm {


    @Autowired
    private ILoginService loginService;

    /**
     * 角色权限和对应权限添加
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //获取登录用户名
        String userCode= (String) principalCollection.getPrimaryPrincipal();
        //查询用户名称
        User user = loginService.findByUserCode(userCode);
        List<Role> roles = loginService.findRolesByUserCode(user.getUserCode());
        List<Permission> permissions = loginService.findPermissionsByUserCode(user.getUserCode());
        //添加角色和权限
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        for (Role role:roles) {
            //添加角色
            simpleAuthorizationInfo.addRole(role.getRoleCode());
        }
        for(Permission permission:permissions){
            //添加权限
            simpleAuthorizationInfo.addStringPermission(permission.getPermissionCode());
        }
        return simpleAuthorizationInfo;
    }

    /**
     * 用户认证
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //加这一步的目的是在Post请求的时候会先进认证，然后在到请求
        if (authenticationToken.getPrincipal() == null) {
            return null;
        }
        //获取用户信息
        String userCode = authenticationToken.getPrincipal().toString();
        User user = loginService.findByUserCode(userCode);
        if (user == null) {
            //这里返回后会报出对应异常
            return null;
        } else {
            //这里验证authenticationToken和simpleAuthenticationInfo的信息
            SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(userCode, user.getPassword(), getName());
            return simpleAuthenticationInfo;
        }
    }
}
