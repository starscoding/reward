package com.smile.azxx.service.sysmng;

import com.smile.azxx.dao.sysmng.UserRoleDao;
import com.smile.azxx.entity.sysmng.UserRole;
import com.smile.azxx.entity.sysmng.UserRolePK;
import com.smile.azxx.service.common.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by smile on 2018/4/14.
 */
@Component
@Transactional
public class UserRoleService extends BaseService{

    @Autowired
    private UserRoleDao userRoleDao;

    public void add(UserRole userRole){
        userRoleDao.save(userRole);
    }

    public List<UserRolePK> getUserRoleByUserId(String userId){
        return userRoleDao.getUserRoleByUserId(userId);
    }

    public List<UserRolePK> getUserRoleByRoleId(String roleId){
        return userRoleDao.getUserRoleByRoleId(roleId);
    }
}
