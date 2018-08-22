package com.sjkjcrm.service.impl;


import com.sjkjcrm.bean.permisson.Permission;
import com.sjkjcrm.bean.permisson.Role;
import com.sjkjcrm.bean.permisson.User;
import com.sjkjcrm.repository.RoleRepository;
import com.sjkjcrm.repository.UserRepository;
import com.sjkjcrm.service.ILoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;


/**
 * @author: xianyunpeng
 */
@Service
@Transactional
public class LoginServiceImpl implements ILoginService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    //添加用户
    @Override
    public User addUser(Map<String, Object> map) {
        User user = new User();
        user.setName(map.get("username").toString());
        user.setPassword(Integer.valueOf(map.get("password").toString()));
        userRepository.save(user);
        return user;
    }

    //添加角色
    @Override
    public Role addRole(Map<String, Object> map) {
        Optional<User> user = userRepository.findById(Long.valueOf(map.get("userId").toString()));
        Role role = new Role();
        role.setRoleName(map.get("roleName").toString());
        role.setUser(user.get());
        Permission permission1 = new Permission();
        permission1.setPermission("create");
        permission1.setRole(role);
        Permission permission2 = new Permission();
        permission2.setPermission("update");
        permission2.setRole(role);
        List<Permission> permissions = new ArrayList<Permission>();
        permissions.add(permission1);
        permissions.add(permission2);
        role.setPermissions(permissions);
        roleRepository.save(role);
        return role;
    }

    //查询用户通过用户名
    @Override
    public User findByName(String name) {
        return userRepository.findByName(name);
    }
}
