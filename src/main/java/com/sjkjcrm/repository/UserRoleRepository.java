package com.sjkjcrm.repository;

import com.sjkjcrm.bean.permisson.Role;
import com.sjkjcrm.bean.permisson.UserRole;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRoleRepository extends BaseRepository<UserRole,Long> {
}
