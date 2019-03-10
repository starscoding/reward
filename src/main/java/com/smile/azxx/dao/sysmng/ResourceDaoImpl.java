package com.smile.azxx.dao.sysmng;

import com.smile.azxx.entity.sysmng.Resource;
import com.smile.azxx.jpa.DaoSupport;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by smile on 2018/4/10.
 */
@Component
public class ResourceDaoImpl extends DaoSupport<Resource> implements ResourceDao {

    @PersistenceContext
    public EntityManager em;

    @Override
    public List<Resource> getResourcesByUser(String userName) {
        List<Resource> result = new ArrayList<>();
        StringBuffer sql = new StringBuffer("select r.* from sys_user u");
        sql.append(" left join sys_user_role ur on u.id=ur.user_id");
        sql.append(" left join sys_role_resource rs on ur.role_id=rs.role_id");
        sql.append(" left join sys_resource r on r.id=rs.resource_id");
        sql.append(" where u.username=?");
        Query query = this.getEm().createNativeQuery(sql.toString(),Resource.class);
        query.setParameter(1,userName);
        result = query.getResultList();
        return result;
    }

    @Override
    public List<Resource> getResourceByRoleId(String roleId) {
        List<Resource> result = new ArrayList<>();
        StringBuffer sql = new StringBuffer("select b.* from sys_role_resource a");
        sql.append(" left join sys_resource b on a.resource_id=b.id");
        sql.append(" where a.role_id=?");
        Query query = this.getEm().createNativeQuery(sql.toString(),Resource.class);
        query.setParameter(1,roleId);
        result = query.getResultList();
        return result;
    }
}
