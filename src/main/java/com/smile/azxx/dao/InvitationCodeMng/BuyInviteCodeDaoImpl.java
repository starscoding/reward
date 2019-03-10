package com.smile.azxx.dao.InvitationCodeMng;

import com.smile.azxx.entity.BuyInvitecode;
import com.smile.azxx.jpa.DaoSupport;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.math.BigDecimal;
import java.util.List;

/**
 * Created by smile on 2018/5/17.
 */
@Component
public class BuyInviteCodeDaoImpl extends DaoSupport<BuyInvitecode> implements BuyInviteCodeDao {

    @PersistenceContext
    public EntityManager em;

    @Override
    public BigDecimal countPrice(String userName) {
        BigDecimal result = new BigDecimal("0");
        String sql ="select sum(price) from BuyInvitecode b where b.userName=?1";
        Query query = this.getEm().createQuery(sql);
        query.setParameter(1,userName);
        List list = query.getResultList();
        if(CollectionUtils.isNotEmpty(list)) {
            System.out.println(list.get(0));

            result = new BigDecimal( list.get(0)==null?"0":list.get(0).toString());
        }
        return result;
    }
}
