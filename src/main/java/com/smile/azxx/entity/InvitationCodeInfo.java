package com.smile.azxx.entity;

import javax.persistence.*;

/**
 * Created by smile on 2018/4/26.
 */
@Entity
@Table(name = "app_invitation_code_info", schema = "", catalog = "")
public class InvitationCodeInfo {
    private String code;
    private String createTime;
    private String status;
    private String activeName;
    private String createName;
    private String activeTime;

    @Id
    @Column(name = "CODE_", nullable = false, length = 32)
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Basic
    @Column(name = "CREATE_TIME", nullable = true, length = 25)
    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    @Basic
    @Column(name = "STATUS_", nullable = true, length = 5)
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Basic
    @Column(name = "ACTIVE_NAME", nullable = true, length = 100)
    public String getActiveName() {
        return activeName;
    }

    public void setActiveName(String activeName) {
        this.activeName = activeName;
    }

    @Basic
    @Column(name = "CREATE_NAME", nullable = true, length = 100)
    public String getCreateName() {
        return createName;
    }

    public void setCreateName(String createName) {
        this.createName = createName;
    }

    @Basic
    @Column(name = "ACTIVE_TIME", nullable = true, length = 100)
    public String getActiveTime() {
        return activeTime;
    }

    public void setActiveTime(String activeTime) {
        this.activeTime = activeTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        InvitationCodeInfo that = (InvitationCodeInfo) o;

        if (code != null ? !code.equals(that.code) : that.code != null) return false;
        if (createTime != null ? !createTime.equals(that.createTime) : that.createTime != null) return false;
        if (status != null ? !status.equals(that.status) : that.status != null) return false;
        if (activeName != null ? !activeName.equals(that.activeName) : that.activeName != null) return false;
        if (createName != null ? !createName.equals(that.createName) : that.createName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = code != null ? code.hashCode() : 0;
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (activeName != null ? activeName.hashCode() : 0);
        result = 31 * result + (createName != null ? createName.hashCode() : 0);
        return result;
    }

    public InvitationCodeInfo() {
    }

    public InvitationCodeInfo(String code, String createTime, String status, String createName) {
        this.code = code;
        this.createTime = createTime;
        this.status = status;
        this.createName = createName;
    }
}
