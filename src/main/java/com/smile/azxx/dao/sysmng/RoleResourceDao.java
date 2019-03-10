package com.smile.azxx.dao.sysmng;

import com.smile.azxx.entity.sysmng.RoleResource;
import com.smile.azxx.entity.sysmng.RoleResourcePK;
import com.smile.azxx.jpa.Dao;

import java.util.List;

/**
 * Created by smile on 2018/4/15.
 */
public interface RoleResourceDao extends Dao<RoleResource> {

    public List<RoleResourcePK> getRoleResourcePkByRoleId(String roleId);

    public List<RoleResource> getRoleResourceByResourceId(String resourceId);
}