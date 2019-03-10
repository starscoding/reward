package com.smile.azxx.dao.sysmng;

import com.smile.azxx.entity.sysmng.UserRole;
import com.smile.azxx.entity.sysmng.UserRolePK;
import com.smile.azxx.jpa.Dao;

import java.util.List;

/**
 * Created by smile on 2018/4/14.
 */

public interface UserRoleDao extends Dao<UserRole> {

    List<UserRolePK>  getUserRoleByUserId(String userId);

    List<UserRolePK> getUserRoleByRoleId(String roleId);

    void delUserRoleByUname(String[] names);
}
