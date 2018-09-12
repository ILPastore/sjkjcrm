package com.sjkjcrm.service;


import com.sjkjcrm.bean.permisson.Permission;
import com.sjkjcrm.bean.permisson.Role;
import com.sjkjcrm.bean.permisson.User;

import java.util.List;
import java.util.Map;

/**
 * @author: xianyunpeng
 */
public interface ILoginService {
    /**
     * 添加用户
     * @param map
     * @return
     */
    User addUser(Map<String, Object> map);

    /**
     * 添加角色
     * @param map
     * @return
     */
    Role addRole(Map<String, Object> map);

    //查询用户通过用户名

    /**
     * 通过用户编码查询用户
     * @param userCode
     * @return
     */
    User findByUserCode(String userCode);

    /**
     * 通过用户编码查询角色
     * @param userCode
     * @return
     */
    List<Role> findRolesByUserCode(String userCode);

    /**
     * 查询用户权限
     * @param userCode
     * @return
     */
    List<Permission> findPermissionsByUserCode(String userCode);
}
