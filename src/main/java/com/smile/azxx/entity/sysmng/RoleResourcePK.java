package com.smile.azxx.entity.sysmng;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by smile on 2018/4/7.
 */
public class RoleResourcePK implements Serializable {
    private String roleId;
    private String resourceId;

    @Column(name = "role_id", nullable = false, length = 32)
    @Id
    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    @Column(name = "resource_id", nullable = false, length = 32)
    @Id
    public String getResourceId() {
        return resourceId;
    }

    public void setResourceId(String resourceId) {
        this.resourceId = resourceId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RoleResourcePK that = (RoleResourcePK) o;

        if (roleId != null ? !roleId.equals(that.roleId) : that.roleId != null) return false;
        if (resourceId != null ? !resourceId.equals(that.resourceId) : that.resourceId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = roleId != null ? roleId.hashCode() : 0;
        result = 31 * result + (resourceId != null ? resourceId.hashCode() : 0);
        return result;
    }

    public RoleResourcePK() {
    }

    public RoleResourcePK(String roleId, String resourceId) {
        this.roleId = roleId;
        this.resourceId = resourceId;
    }
}
