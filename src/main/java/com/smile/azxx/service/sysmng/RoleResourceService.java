package com.smile.azxx.service.sysmng;

import com.smile.azxx.dao.sysmng.RoleResourceDao;
import com.smile.azxx.entity.sysmng.RoleResource;
import com.smile.azxx.service.common.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by smile on 2018/4/16.
 */
@Service
@Transactional
public class RoleResourceService extends BaseService{

    @Autowired
    private RoleResourceDao roleResourceDao;

    public List<RoleResource> getRoleResourceByResourceId(String resourceId){
        return  roleResourceDao.getRoleResourceByResourceId(resourceId);
    }

    public void delete(List<RoleResource> roleResources){
        roleResourceDao.delete(roleResources);
    }
}
