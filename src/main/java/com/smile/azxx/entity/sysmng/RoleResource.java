package com.smile.azxx.entity.sysmng;

import javax.persistence.*;

/**
 * Created by smile on 2018/4/7.
 */
@Entity
@Table(name = "sys_role_resource", schema = "", catalog = "")
@IdClass(RoleResourcePK.class)
public class RoleResource {
    private String roleId;
    private String resourceId;
    private Role roleByRoleId;
    private Resource sysResourceByResourceId;

    @Id
    @Column(name = "role_id", nullable = false, length = 32)
    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    @Id
    @Column(name = "resource_id", nullable = false, length = 32)
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

        RoleResource that = (RoleResource) o;

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

    @ManyToOne
    @JoinColumn(name = "role_id", referencedColumnName = "id", nullable = false , insertable = false,updatable = false)
    public Role getRoleByRoleId() {
        return roleByRoleId;
    }

    public void setRoleByRoleId(Role roleByRoleId) {
        this.roleByRoleId = roleByRoleId;
    }

    @ManyToOne
    @JoinColumn(name = "resource_id", referencedColumnName = "id", nullable = false , insertable = false,updatable = false)
    public Resource getSysResourceByResourceId() {
        return sysResourceByResourceId;
    }

    public void setSysResourceByResourceId(Resource sysResourceByResourceId) {
        this.sysResourceByResourceId = sysResourceByResourceId;
    }

    public RoleResource() {
    }

    public RoleResource(String roleId, String resourceId) {
        this.roleId = roleId;
        this.resourceId = resourceId;
    }
}
