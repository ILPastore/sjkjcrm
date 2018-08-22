package com.sjkjcrm.service;


import com.sjkjcrm.bean.permisson.Role;
import com.sjkjcrm.bean.permisson.User;

import java.util.Map;

/**
 * @author: xianyunpeng
 */
public interface ILoginService {
    //添加用户
    User addUser(Map<String, Object> map);

    //添加角色
    Role addRole(Map<String, Object> map);

    //查询用户通过用户名
    User findByName(String name);
}
