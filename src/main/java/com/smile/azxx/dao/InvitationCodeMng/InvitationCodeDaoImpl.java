package com.smile.azxx.dao.InvitationCodeMng;

import com.smile.azxx.entity.InvitationCodeInfo;
import com.smile.azxx.jpa.DaoSupport;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by smile on 2018/4/26.
 */
@Component
public class InvitationCodeDaoImpl extends DaoSupport<InvitationCodeInfo> implements InvitationCodeDao {

    @PersistenceContext
    public EntityManager em;

    @Override
    public List<InvitationCodeInfo> getCodesByStatus(String status) {
        String sql = "select code from InvitationCodeInfo code ";
        if(StringUtils.isNotBlank(status))
            sql += " where code.status=?";
        Query query = this.getEm().createQuery(sql,InvitationCodeInfo.class);
        if(StringUtils.isNotBlank(status))
            query.setParameter(1,status);
        return query.getResultList();
    }

    @Override
    public List<InvitationCodeInfo> getCodesByCreator(String status,String creatorName) {
        String sql = "select code from InvitationCodeInfo code where code.createName=?";
        if(StringUtils.isNotBlank(status))
            sql += " and code.status=?";
        Query query = this.getEm().createQuery(sql,InvitationCodeInfo.class);
        query.setParameter(1,creatorName);
        if(StringUtils.isNotBlank(status))
            query.setParameter(2,status);
        return query.getResultList();
    }

    @Override
    public boolean hasUsed(String code) {
        boolean hasUsed = true;
        String sql = "select code from InvitationCodeInfo code where code.code=?1 and code.status='0'";
        Query query = this.getEm().createQuery(sql,InvitationCodeInfo.class);
        query.setParameter(1,code);
        List<InvitationCodeInfo> list = query.getResultList();
        if(CollectionUtils.isNotEmpty(list))
            hasUsed = false;
        return hasUsed;
    }
}
