package com.sjkjcrm.repository;

import com.sjkjcrm.bean.permisson.Permission;
import com.sjkjcrm.bean.permisson.Role;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @author: xianyunpeng
 */
public interface PermissionRepository extends BaseRepository<Permission,Long> {
    @Query(nativeQuery = true,value = "SELECT\n" +
            "\tt.*\n" +
            "FROM\n" +
            "\tbase_permission t\n" +
            "WHERE\n" +
            "\tEXISTS (\n" +
            "\t\tSELECT\n" +
            "\t\t\t1\n" +
            "\t\tFROM\n" +
            "\t\t\tbase_user_role t1,\n" +
            "\t\t\tbase_user t2,\n" +
            "\t\t\tbase_role_permission t3\n" +
            "\t\tWHERE\n" +
            "\t\t\tt1.user_code = t2.user_code\n" +
            "\t\t AND t.permission_code = t3.permission_code\n" +
            "\t\tand t3.role_code = t1.role_code\n" +
            "\t\tAND t1.user_code = :userCode\n" +
            "\t)")
    public List<Permission> findPermissionsByUserCode(@Param("userCode") String userCode);
}
