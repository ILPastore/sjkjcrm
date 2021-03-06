package com.sjkjcrm.bean.permisson;


import javax.persistence.*;


/**
 * @author: xianyunpeng
 */
@Entity
@Table(name="base_permission")
public class Permission {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long  id;

    /**
     * 权限编码
     */
    private String permissionCode;
    /**
     * 权限标志符
     */
    private String permission;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public String getPermissionCode() {
        return permissionCode;
    }

    public void setPermissionCode(String permissionCode) {
        this.permissionCode = permissionCode;
    }
}
