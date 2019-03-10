package com.smile.azxx.entity;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * Created by smile on 2018/5/4.
 */
@Entity
@Table(name = "app_withdraw_info", schema = "", catalog = "")
public class WithdrawInfo {
    private String id;
    private String name;
    private BigDecimal amount;
    private String alipayaccount;
    private String fullName;
    private String time;
    private String opStatus;
    private String url;

    @Id
    @Column(name = "ID_", nullable = false, length = 32)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "NAME_", nullable = false, length = 100)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "AMOUNT", nullable = false, precision = 4)
    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    @Basic
    @Column(name = "ALIPAYACCOUNT", nullable = false, length = 50)
    public String getAlipayaccount() {
        return alipayaccount;
    }

    public void setAlipayaccount(String alipayaccount) {
        this.alipayaccount = alipayaccount;
    }

    @Basic
    @Column(name = "FULL_NAME", nullable = false, length = 50)
    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    @Basic
    @Column(name = "TIME_", nullable = true, length = 20)
    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Basic
    @Column(name = "OP_STATUS", nullable = true, length = 5)
    public String getOpStatus() {
        return opStatus;
    }

    public void setOpStatus(String opStatus) {
        this.opStatus = opStatus;
    }

    @Basic
    @Column(name = "URL_", nullable = true, length = 100)
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        WithdrawInfo that = (WithdrawInfo) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (amount != null ? !amount.equals(that.amount) : that.amount != null) return false;
        if (alipayaccount != null ? !alipayaccount.equals(that.alipayaccount) : that.alipayaccount != null)
            return false;
        if (fullName != null ? !fullName.equals(that.fullName) : that.fullName != null) return false;
        if (time != null ? !time.equals(that.time) : that.time != null) return false;
        if (opStatus != null ? !opStatus.equals(that.opStatus) : that.opStatus != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (amount != null ? amount.hashCode() : 0);
        result = 31 * result + (alipayaccount != null ? alipayaccount.hashCode() : 0);
        result = 31 * result + (fullName != null ? fullName.hashCode() : 0);
        result = 31 * result + (time != null ? time.hashCode() : 0);
        result = 31 * result + (opStatus != null ? opStatus.hashCode() : 0);
        return result;
    }
}
