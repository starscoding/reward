package com.smile.azxx.entity;

import javax.persistence.*;

/**
 * Created by smile on 2018/4/21.
 */
@Entity
@Table(name = "sys_conf_info", schema = "", catalog = "")
public class ConfInfo {
    private String id;
    private String groupName;
    private String name;
    private String nameCh;
    private String value;

    @Id
    @Column(name = "id", nullable = false, length = 32)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "group_name", nullable = true, length = 100)
    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    @Basic
    @Column(name = "name_", nullable = true, length = 100)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "name_ch", nullable = true, length = 100)
    public String getNameCh() {
        return nameCh;
    }

    public void setNameCh(String nameCh) {
        this.nameCh = nameCh;
    }

    @Basic
    @Column(name = "value_", nullable = true, length = 100)
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ConfInfo that = (ConfInfo) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (groupName != null ? !groupName.equals(that.groupName) : that.groupName != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (nameCh != null ? !nameCh.equals(that.nameCh) : that.nameCh != null) return false;
        if (value != null ? !value.equals(that.value) : that.value != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (groupName != null ? groupName.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (nameCh != null ? nameCh.hashCode() : 0);
        result = 31 * result + (value != null ? value.hashCode() : 0);
        return result;
    }

    public ConfInfo() {
    }

    public ConfInfo(String id, String groupName, String name, String nameCh, String value) {
        this.id = id;
        this.groupName = groupName;
        this.name = name;
        this.nameCh = nameCh;
        this.value = value;
    }
}
