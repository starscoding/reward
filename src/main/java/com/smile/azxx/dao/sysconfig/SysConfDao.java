package com.smile.azxx.dao.sysconfig;

import com.smile.azxx.entity.ConfInfo;
import com.smile.azxx.jpa.Dao;

import java.util.List;
import java.util.Map;

/**
 * Created by smile on 2018/4/21.
 */
public interface SysConfDao extends Dao<ConfInfo> {

    public void deleteByGroupName(String groupName);

    public List<ConfInfo> getConfByGroupName(String groupName);

    public ConfInfo getConfByName(String name);

    public Map<String,String> getMapConf(String groupName);

    public String getLDDomain(String status);
}
