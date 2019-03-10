package com.smile.azxx.model;

import java.math.BigDecimal;

/**
 * Created by smile on 2018/5/13.
 */
public class AgencyDetail {

    private String userName;
    private String createTime;
    private String wechat;
    private String qq;
    private String level;//代理级别
    private String parent;//上级推广人
    private String partner;//所属分销
    private BigDecimal total;//总额
    private BigDecimal hasWithdraw;//已提现
    private BigDecimal notWithdraw;//未提现
    private String effectNum;//有效代理数量
    private String totalAgency;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getWechat() {
        return wechat;
    }

    public void setWechat(String wechat) {
        this.wechat = wechat;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }

    public String getPartner() {
        return partner;
    }

    public void setPartner(String partner) {
        this.partner = partner;
    }

    public String getEffectNum() {
        return effectNum;
    }

    public void setEffectNum(String effectNum) {
        this.effectNum = effectNum;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public BigDecimal getHasWithdraw() {
        return hasWithdraw;
    }

    public void setHasWithdraw(BigDecimal hasWithdraw) {
        this.hasWithdraw = hasWithdraw;
    }

    public BigDecimal getNotWithdraw() {
        return notWithdraw;
    }

    public void setNotWithdraw(BigDecimal notWithdraw) {
        this.notWithdraw = notWithdraw;
    }

    public String getTotalAgency() {
        return totalAgency;
    }

    public void setTotalAgency(String totalAgency) {
        this.totalAgency = totalAgency;
    }
}
