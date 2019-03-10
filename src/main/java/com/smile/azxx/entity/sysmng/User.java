package com.smile.azxx.entity.sysmng;

import javax.persistence.*;

/**
 * Created by smile on 2018/4/7.
 */
@Entity
@Table(name = "sys_user", schema = "", catalog = "")
public class User {
    private String id;
    private String locked;
    private String password;
    private String username;
    private String wechat;
    private String invitationCode;
    private String type;
    private String qq;
    private String createTime;
    private String parentName;
    private String fetchPwd;

    @Id
    @Column(name = "id", nullable = false, length = 32)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "locked", nullable = true, length = 2)
    public String getLocked() {
        return locked;
    }

    public void setLocked(String locked) {
        this.locked = locked;
    }

    @Basic
    @Column(name = "password", nullable = false, length = 255)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "username", nullable = true, length = 255)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Basic
    @Column(name = "wechat", nullable = true, length = 255)
    public String getWechat() {
        return wechat;
    }

    public void setWechat(String wechat) {
        this.wechat = wechat;
    }

    @Basic
    @Column(name = "invitation_code", nullable = true, length = 100)
    public String getInvitationCode() {
        return invitationCode;
    }

    public void setInvitationCode(String invitationCode) {
        this.invitationCode = invitationCode;
    }

    @Basic
    @Column(name = "type_", nullable = true, length = 32)
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Basic
    @Column(name = "qq", nullable = true, length = 18)
    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    @Basic
    @Column(name = "create_time", nullable = true, length = 25)
    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    @Basic
    @Column(name = "parent_name", nullable = true, length = 100)
    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    @Basic
    @Column(name = "fetch_pwd",nullable = true,length = 100)
    public String getFetchPwd() {
        return fetchPwd;
    }

    public void setFetchPwd(String fetchPwd) {
        this.fetchPwd = fetchPwd;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User that = (User) o;

        if (id != that.id) return false;
        if (locked != null ? !locked.equals(that.locked) : that.locked != null) return false;
        if (password != null ? !password.equals(that.password) : that.password != null) return false;
        if (username != null ? !username.equals(that.username) : that.username != null) return false;
        if (wechat != null ? !wechat.equals(that.wechat) : that.wechat != null) return false;
        if (invitationCode != null ? !invitationCode.equals(that.invitationCode) : that.invitationCode != null)
            return false;
        if (type != null ? !type.equals(that.type) : that.type != null) return false;
        if (qq != null ? !qq.equals(that.qq) : that.qq != null) return false;
        if (createTime != null ? !createTime.equals(that.createTime) : that.createTime != null) return false;
        if (parentName != null ? !parentName.equals(that.parentName) : that.parentName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result =  (id != null ? id.hashCode() : 0);
        result = 31 * result + (locked != null ? locked.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (username != null ? username.hashCode() : 0);
        result = 31 * result + (wechat != null ? wechat.hashCode() : 0);
        result = 31 * result + (invitationCode != null ? invitationCode.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (qq != null ? qq.hashCode() : 0);
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        result = 31 * result + (parentName != null ? parentName.hashCode() : 0);
        return result;
    }
}
