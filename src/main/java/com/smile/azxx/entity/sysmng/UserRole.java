package com.smile.azxx.entity.sysmng;

import javax.persistence.*;

/**
 * Created by smile on 2018/4/7.
 */
@Entity
@Table(name = "sys_user_role", schema = "")
@IdClass(UserRolePK.class)
public class UserRole {
    private String userId;
    private String roleId;
    private User sysUserByUserId;
    private Role roleByRoleId;

    @Id
    @Column(name = "user_id", nullable = false, length = 32)
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Id
    @Column(name = "role_id", nullable = false, length = 32)
    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserRole that = (UserRole) o;

        if (userId != that.userId) return false;
        if (roleId != that.roleId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result =  (userId != null ? userId.hashCode() : 0);
        result = 31 * result +  (roleId != null ? roleId.hashCode() : 0);;
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false , insertable = false,updatable = false)
    public User getSysUserByUserId() {
        return sysUserByUserId;
    }

    public void setSysUserByUserId(User sysUserByUserId) {
        this.sysUserByUserId = sysUserByUserId;
    }

    @ManyToOne
    @JoinColumn(name = "role_id", referencedColumnName = "id", nullable = false, insertable = false,updatable = false)
    public Role getRoleByRoleId() {
        return roleByRoleId;
    }

    public void setRoleByRoleId(Role roleByRoleId) {
        this.roleByRoleId = roleByRoleId;
    }

    public UserRole(String userId, String roleId) {
        this.userId = userId;
        this.roleId = roleId;
    }

    public UserRole() {
    }
}
