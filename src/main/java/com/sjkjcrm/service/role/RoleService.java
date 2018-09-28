package com.sjkjcrm.service.role;

import com.baomidou.mybatisplus.plugins.Page;
import com.sjkjcrm.bean.permisson.Role;
import com.sjkjcrm.bean.permisson.User;

import java.util.List;

/**
 * @author: xianyunpeng
 */
public interface RoleService {
    List<Role> queryList(Page<User> page);
}
