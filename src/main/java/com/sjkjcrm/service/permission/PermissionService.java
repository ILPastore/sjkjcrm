package com.sjkjcrm.service.permission;

import com.baomidou.mybatisplus.plugins.Page;
import com.sjkjcrm.bean.permisson.Permission;
import com.sjkjcrm.bean.permisson.User;

import java.util.List;

/**
 * @author: xianyunpeng
 */
public interface PermissionService {
    List<Permission> queryList(Page<User> page);
}
