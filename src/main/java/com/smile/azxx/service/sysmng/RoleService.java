package com.smile.azxx.service.sysmng;

import com.smile.azxx.dao.sysmng.RoleDao;
import com.smile.azxx.dao.sysmng.RoleResourceDao;
import com.smile.azxx.dao.sysmng.UserRoleDao;
import com.smile.azxx.entity.sysmng.Role;
import com.smile.azxx.entity.sysmng.RoleResource;
import com.smile.azxx.entity.sysmng.RoleResourcePK;
import com.smile.azxx.entity.sysmng.UserRolePK;
import com.smile.azxx.service.common.BaseService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by smile on 2018/4/12.
 */
@Service
@Transactional
public class RoleService extends BaseService {

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private UserRoleDao userRoleDao;

    @Autowired
    private RoleResourceDao roleResourceDao;

    public void addRole(Role role){
        roleDao.save(role);
    }

    public void updateRole(Role role){
        roleDao.update(role);
    }

    public void deleteRoles(String[] ids){
        for (int i = 0; i < ids.length; i++) {
            List<UserRolePK> userRoles = userRoleDao.getUserRoleByRoleId(ids[i]);
            for (int j = 0; j < userRoles.size(); j++) {
                userRoleDao.delete(userRoles.get(j));
            }
            List<RoleResourcePK> roleResources = roleResourceDao.getRoleResourcePkByRoleId(ids[i]);
            for (int j = 0; j < roleResources.size(); j++) {
                roleResourceDao.delete(roleResources.get(j));
            }
            roleDao.delete(ids[i]);
        }
    }

    public Role getRole(String id){
        return roleDao.findOne(id);
    }

    public List<Role> getAllRoles(){
        return  roleDao.findAll();
    }

    public void roleAccredit(String[] delIds,String[] addIs,String roleId){
        List<RoleResource> dels = new ArrayList<>();
        List<RoleResource> adds = new ArrayList<>();
        if(ArrayUtils.isNotEmpty(delIds)){
            for (int i = 0; i < delIds.length; i++) {
                dels.add(new RoleResource(roleId,delIds[i]));
            }
        }
        if(ArrayUtils.isNotEmpty(addIs)){
            for (int i = 0; i < addIs.length; i++) {
                adds.add(new RoleResource(roleId,addIs[i]));
            }
        }
        if(!CollectionUtils.isEmpty(dels)){
            roleResourceDao.delete(dels);
        }if(!CollectionUtils.isEmpty(adds)){
            roleResourceDao.saveIterable(adds);
        }


    }
}
