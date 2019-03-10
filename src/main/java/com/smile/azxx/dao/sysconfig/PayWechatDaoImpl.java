package com.smile.azxx.dao.sysconfig;

import com.smile.azxx.entity.WechatpayInfo;
import com.smile.azxx.jpa.DaoSupport;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by smile on 2018/4/20.
 */
@Component
public class PayWechatDaoImpl extends DaoSupport<WechatpayInfo> implements PayWechatDao{

    @PersistenceContext
    public EntityManager em;

    @Override
    public void updateStatus(String appId, String status) {
        String sql = "update app_wechatpay_info set status_=? where APPID=?";
        Query query = this.getEm().createNativeQuery(sql);
        query.setParameter(1,status);
        query.setParameter(2,appId);
        query.executeUpdate();
    }

    @Override
    public void setUnuse(String status) {
        String sql = "update app_wechatpay_info set status_=? where status_='0'";
        Query query = this.getEm().createNativeQuery(sql);
        query.setParameter(1,status);
        query.executeUpdate();
    }

    @Override
    public List<WechatpayInfo> getUseWePay() {
        String sql = "SELECT w FROM  WechatpayInfo w where status='0'";
        Query query = this.getEm().createQuery(sql);
        return query.getResultList();
    }

}
