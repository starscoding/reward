package com.smile.azxx.dao.sysmng;

import com.smile.azxx.entity.sysmng.Resource;
import com.smile.azxx.jpa.Dao;

import java.util.List;

/**
 * Created by smile on 2018/4/10.
 */
public interface ResourceDao extends Dao<Resource>{

    public List<Resource> getResourcesByUser(String userName);

    public List<Resource> getResourceByRoleId(String roleId);
}
