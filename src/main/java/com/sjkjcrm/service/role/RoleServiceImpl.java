package com.sjkjcrm.service.role;

import com.baomidou.mybatisplus.plugins.Page;
import com.sjkjcrm.bean.permisson.Role;
import com.sjkjcrm.bean.permisson.User;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: xianyunpeng
 */
@Service
public class RoleServiceImpl implements RoleService{
    @Override
    public List<Role> queryList(Page<User> page) {
        return null;
    }
}
