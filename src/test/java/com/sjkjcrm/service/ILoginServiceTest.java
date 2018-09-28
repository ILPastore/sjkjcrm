package com.sjkjcrm.service;

import com.sjkjcrm.bean.permisson.Role;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

/**
 * @author: xianyunpeng
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ILoginServiceTest {

    @Autowired
    private ILoginService iLoginService;

    @Test
    public void addUser() {
    }

    @Test
    public void queryList() {
    }

    @Test
    public void addRole() {
    }

    @Test
    public void findByUserCode() {

    }

    @Test
    public void findRolesByUserCode() {
        List<Role> roles = iLoginService.findRolesByUserCode("admin");
        System.out.println(roles.get(0).getRoleCode());
    }

    @Test
    public void findPermissionsByUserCode() {
    }
}