package com.sjkjcrm.repository;


import com.sjkjcrm.bean.permisson.User;

/**
 * @author: xianyunpeng
 */


public interface UserRepository extends BaseRepository<User,Long>{
    /**
     * 按名称查询
     * @param name
     * @return
     */
    User findByName(String name);
}

