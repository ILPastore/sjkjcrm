package com.sjkjcrm.service.permission;

import com.baomidou.mybatisplus.plugins.Page;
import com.sjkjcrm.bean.permisson.Permission;
import com.sjkjcrm.bean.permisson.User;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: xianyunpeng
 */
@Service
public class PermissionServiceImpl implements PermissionService {
    @Override
    public List<Permission> queryList(Page<User> page) {
        return null;
    }
}
