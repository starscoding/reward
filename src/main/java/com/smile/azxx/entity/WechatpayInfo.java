package com.smile.azxx.entity;

import javax.persistence.*;

/**
 * Created by smile on 2018/4/20.
 */
@Entity
@Table(name = "app_wechatpay_info", schema = "", catalog = "")
public class WechatpayInfo {
    private String appid;
    private String comment;
    private String appsecret;
    private String bizAccount;
    private String key;
    private String txtUrl;
    private String fileName;
    private String status;

    @Id
    @Column(name = "APPID", nullable = false, length = 100)
    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    @Basic
    @Column(name = "COMMENT_", nullable = true, length = 100)
    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Basic
    @Column(name = "APPSECRET", nullable = true, length = 50)
    public String getAppsecret() {
        return appsecret;
    }

    public void setAppsecret(String appsecret) {
        this.appsecret = appsecret;
    }

    @Basic
    @Column(name = "BIZ_ACCOUNT", nullable = true, length = 50)
    public String getBizAccount() {
        return bizAccount;
    }

    public void setBizAccount(String bizAccount) {
        this.bizAccount = bizAccount;
    }

    @Basic
    @Column(name = "KEY_", nullable = true, length = 50)
    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    @Basic
    @Column(name = "TXT_URL", nullable = true, length = 100)
    public String getTxtUrl() {
        return txtUrl;
    }

    public void setTxtUrl(String txtUrl) {
        this.txtUrl = txtUrl;
    }

    @Basic
    @Column(name = "FILE_NAME", nullable = true, length = 100)
    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    @Basic
    @Column(name = "STATUS_", nullable = true, length = 2)
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        WechatpayInfo that = (WechatpayInfo) o;

        if (appid != null ? !appid.equals(that.appid) : that.appid != null) return false;
        if (comment != null ? !comment.equals(that.comment) : that.comment != null) return false;
        if (appsecret != null ? !appsecret.equals(that.appsecret) : that.appsecret != null) return false;
        if (bizAccount != null ? !bizAccount.equals(that.bizAccount) : that.bizAccount != null) return false;
        if (key != null ? !key.equals(that.key) : that.key != null) return false;
        if (txtUrl != null ? !txtUrl.equals(that.txtUrl) : that.txtUrl != null) return false;
        if (fileName != null ? !fileName.equals(that.fileName) : that.fileName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = appid != null ? appid.hashCode() : 0;
        result = 31 * result + (comment != null ? comment.hashCode() : 0);
        result = 31 * result + (appsecret != null ? appsecret.hashCode() : 0);
        result = 31 * result + (bizAccount != null ? bizAccount.hashCode() : 0);
        result = 31 * result + (key != null ? key.hashCode() : 0);
        result = 31 * result + (txtUrl != null ? txtUrl.hashCode() : 0);
        result = 31 * result + (fileName != null ? fileName.hashCode() : 0);
        return result;
    }
}
