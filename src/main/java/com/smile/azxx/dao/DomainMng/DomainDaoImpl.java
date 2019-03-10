package com.smile.azxx.dao.DomainMng;

import com.smile.azxx.entity.DomainInfo;
import com.smile.azxx.jpa.DaoSupport;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by smile on 2018/5/14.
 */
@Component
public class DomainDaoImpl extends DaoSupport<DomainInfo> implements DomainDao{

    @PersistenceContext
    public EntityManager em;

    @Override
    public List<DomainInfo> getUsingDomain() {
        String sql = "select d from DomainInfo d where d.status='0'";
        Query query = this.getEm().createQuery(sql,DomainInfo.class);
        return query.getResultList();
    }
}
