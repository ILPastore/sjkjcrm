package com.sjkjcrm.bean.permisson;

import javax.persistence.*;

@Entity
@Table(name="base_role_permission")
public class RolePermission {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long  id;

    /**
     * 角色编码
     */
    private String roleCode;

    /**
     * 权限编码
     */
    private String permissionCode;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }

    public String getPermissionCode() {
        return permissionCode;
    }

    public void setPermissionCode(String permissionCode) {
        this.permissionCode = permissionCode;
    }
}
