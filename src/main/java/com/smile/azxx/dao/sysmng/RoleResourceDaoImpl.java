package com.smile.azxx.dao.sysmng;

import com.smile.azxx.entity.sysmng.RoleResource;
import com.smile.azxx.entity.sysmng.RoleResourcePK;
import com.smile.azxx.jpa.DaoSupport;
import com.smile.azxx.util.ListUtil;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by smile on 2018/4/15.
 */
@Component
public class RoleResourceDaoImpl extends DaoSupport<RoleResource> implements RoleResourceDao {

    @PersistenceContext
    public EntityManager em;

    @Override
    public List<RoleResourcePK> getRoleResourcePkByRoleId(String roleId) {
        List<RoleResourcePK> result = new ArrayList<>();
        String sql = "select * from sys_role_resource where role_id=?";
        Query query = this.getEm().createNativeQuery(sql);
        query.setParameter(1,roleId);
        List list = query.getResultList();
        if(ListUtil.isNotBlank(list)){
            for (int i = 0; i < list.size(); i++) {
                Object[] obj = (Object[]) list.get(i);
                RoleResourcePK temp = new RoleResourcePK();
                temp.setRoleId(StringUtils.isBlank(obj[0].toString())?"":obj[0].toString());
                temp.setResourceId(StringUtils.isBlank(obj[1].toString())?"":obj[1].toString());
                result.add(temp);
            }
        }
        return result;
    }

    @Override
    public List<RoleResource> getRoleResourceByResourceId(String resourceId) {
        List<RoleResource> result = new ArrayList<>();
        String sql = "select role_id,resource_id from sys_role_resource where resource_id=?";
        Query query = this.getEm().createNativeQuery(sql, RoleResource.class);
        query.setParameter(1,resourceId);
        List list = query.getResultList();
        if(CollectionUtils.isNotEmpty(list)){
            result = list;
        }
        return result;
    }
}
