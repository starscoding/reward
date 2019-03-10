package com.smile.azxx.entity;

import javax.persistence.*;

/**
 * Created by smile on 2018/5/14.
 */
@Entity
@Table(name = "app_domain_info", schema = "", catalog = "")
public class DomainInfo {
    private String domain;
    private String status;

    @Id
    @Column(name = "domain", nullable = false, length = 100)
    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    @Basic
    @Column(name = "status_", nullable = true, length = 2)
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

        DomainInfo that = (DomainInfo) o;

        if (domain != null ? !domain.equals(that.domain) : that.domain != null) return false;
        if (status != null ? !status.equals(that.status) : that.status != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = domain != null ? domain.hashCode() : 0;
        result = 31 * result + (status != null ? status.hashCode() : 0);
        return result;
    }

    public DomainInfo() {
    }

    public DomainInfo(String domain, String status) {
        this.domain = domain;
        this.status = status;
    }
}
