package com.sjkjcrm.repository;


import com.sjkjcrm.bean.permisson.Role;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @author: xianyunpeng
 */


public interface RoleRepository extends BaseRepository<Role,Long> {
    @Query(nativeQuery = true,value = "SELECT\n" +
            "\tt.*\n" +
            "FROM\n" +
            "\tbase_role t\n" +
            "where \n" +
            "EXISTS(select 1 from base_user_role t1,base_user t2 WHERE t1.user_code = t2.user_code and t.role_code = t1.role_code and t1.user_code = :userCode)")
    public List<Role> findRoleByUserCode(@Param("userCode") String userCode);
}
