package com.sjkjcrm.repository;

import com.sjkjcrm.bean.permisson.UserRole;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRoleRepository extends BaseRepository<UserRole,Long> {

    @Query(nativeQuery = true,value = "select t.usercode,t1.* from base_user_role t left join base_role t1 on t.rolecode = t1.rolecode where t.usercode = :userCode")
    public List<Object> findRoleByUserCode(@Param("userCode") String userCode);
}
