package com.smile.azxx.dao.sysconfig;

import com.smile.azxx.entity.ConfInfo;
import com.smile.azxx.jpa.DaoSupport;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by smile on 2018/4/21.
 */
@Component
public class SysConfDaoImpl extends DaoSupport<ConfInfo> implements SysConfDao {

    @PersistenceContext
    public EntityManager em;

    @Override
    public void deleteByGroupName(String groupName) {
        String sql = "delete from sys_conf_info where group_name=?";
        Query query = this.getEm().createNativeQuery(sql);
        query.setParameter(1,groupName);
        query.executeUpdate();
    }

    @Override
    public List<ConfInfo> getConfByGroupName(String groupName) {
        String sql = "select r from ConfInfo r where r.groupName=?";
        Query query = this.getEm().createQuery(sql,ConfInfo.class);
        query.setParameter(1,groupName);
        return query.getResultList();
    }

    @Override
    public Map<String,String> getMapConf(String groupName) {
        Map<String,String> result = new HashMap<>();
        String sql = "select r from ConfInfo r where r.groupName=?";
        Query query = this.getEm().createQuery(sql,ConfInfo.class);
        query.setParameter(1,groupName);
        List<ConfInfo> list = query.getResultList();
        if(CollectionUtils.isNotEmpty(list)){
            for (int i = 0; i < list.size(); i++) {
                ConfInfo temp = list.get(i);
                result.put(temp.getName(),temp.getValue());
            }
        }
        return result;
    }

    @Override
    public ConfInfo getConfByName(String name) {
        ConfInfo conf = new ConfInfo();
        String sql = "select r from ConfInfo r where r.name=?1";
        Query query = this.getEm().createQuery(sql,ConfInfo.class);
        query.setParameter(1,name);
        List<ConfInfo> list = query.getResultList();
        if(CollectionUtils.isNotEmpty(list))
            conf = list.get(0);
        return conf;
    }

    public String getLDDomain(String status){
        String result = "";
        String sql = "select domain from app_domain_info where status_=?";
        Query query = this.getEm().createNativeQuery(sql);
        query.setParameter(1,status);
        List list = query.getResultList();
        if(CollectionUtils.isNotEmpty(list))
            result = list.get(0).toString();
        return result;
    }
}
