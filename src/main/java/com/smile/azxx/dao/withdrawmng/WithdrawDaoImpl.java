package com.smile.azxx.dao.withdrawmng;

import com.smile.azxx.entity.WithdrawInfo;
import com.smile.azxx.jpa.DaoSupport;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.text.DecimalFormat;
import java.util.List;

/**
 * Created by smile on 2018/5/4.
 */
@Component
public class WithdrawDaoImpl extends DaoSupport<WithdrawInfo> implements WithdrawDao {

    @PersistenceContext
    public EntityManager em;

    @Override
    public List<WithdrawInfo> getWithdrawInfo(String userName, String startTime, String endTime){
//        List<WithdrawInfo> result = new ArrayList<>();
        String sql = "select w from WithdrawInfo w where w.name=?1 ";
        if(StringUtils.isNotBlank(startTime))
            sql += " and w.time>=?2";
        if(StringUtils.isNotBlank(endTime))
            sql += " and w.time<=?3";
        Query query = this.getEm().createQuery(sql,WithdrawInfo.class);
        query.setParameter(1,userName);
        if(StringUtils.isNotBlank(startTime)){
            query.setParameter(2,startTime);
        }
        if(StringUtils.isNotBlank(endTime)){
            query.setParameter(3,endTime);
        }
        log.info(sql);
        return query.getResultList();
    }

    @Override
    public List<WithdrawInfo> getWithdrawInfo( String startTime, String endTime){
//        List<WithdrawInfo> result = new ArrayList<>();
        String sql = "select w from WithdrawInfo w where 1=1";
        if(StringUtils.isNotBlank(startTime))
            sql += " and w.time>=?2";
        if(StringUtils.isNotBlank(endTime))
            sql += " and w.time<=?3";
        Query query = this.getEm().createQuery(sql,WithdrawInfo.class);
        if(StringUtils.isNotBlank(startTime)){
            query.setParameter(2,startTime);
        }
        if(StringUtils.isNotBlank(endTime)){
            query.setParameter(3,endTime);
        }
        log.info(sql);
        return query.getResultList();
    }

    @Override
    public List<WithdrawInfo> getWithdrawByName(String userName) {
        String sql = "select w from WithdrawInfo w where w.name=?1 ";
        Query query = this.getEm().createQuery(sql,WithdrawInfo.class);
        query.setParameter(1,userName);
        log.info(sql);
        return query.getResultList();
    }
    @Override
    public List<WithdrawInfo> getWithdrawByPName(String userName) {
//        String sql = "select w from WithdrawInfo w left join User u on w.name=u.username where u.parentName=?1 ";
        String sql = "select w.* from app_withdraw_info w";
        sql += " left join sys_user u on w.NAME_=u.username";
        sql += " where u.parent_name=? and w.OP_STATUS='1'";
        Query query = this.getEm().createNativeQuery(sql,WithdrawInfo.class);
        query.setParameter(1,userName);
        log.info(sql);
        return query.getResultList();
    }
    @Override
    public String countHasWithdrawal(String userName) {
        String result = "0";
        DecimalFormat df = new DecimalFormat("#.##");
        String sql = "select sum(amount) from APP_WITHDRAW_INFO where name_=?1 and op_status!='2'";
        Query query = this.getEm().createNativeQuery(sql);
        query.setParameter(1,userName);
        List list = query.getResultList();
        if(CollectionUtils.isNotEmpty(list)) {
            if(list.get(0)==null)
                result = "0";
            else
                result = df.format(list.get(0));
        }
        return result;
    }

    @Override
    public void updateWithdrawStatus(String id, String status) {
        String sql = "update WithdrawInfo w set w.opStatus=?1 where w.id=?2";
        Query query = this.getEm().createQuery(sql);
        query.setParameter(1,status);
        query.setParameter(2,id);
        int result = query.executeUpdate();
        log.info(sql+","+result);
    }

    @Override
    public String countWithdrawAmount(String date, boolean isSuccess) {
        String result = "0";
        DecimalFormat df = new DecimalFormat("#.##");
        String sql = "select sum(amount) from APP_WITHDRAW_INFO where 1=1";
        if(isSuccess){
            sql += " and op_status='1'";
        }else{
            sql += " and op_status!='1'";
        }
        if(StringUtils.isNotBlank(date)){
            sql += " and time_ like ?";
        }
        Query query = this.getEm().createNativeQuery(sql);
        if(StringUtils.isNotBlank(date)){
            query.setParameter(1,date+"%");
        }
        log.info(sql);
        List list = query.getResultList();
        if(CollectionUtils.isNotEmpty(list)) {
            if(list.get(0)==null)
                result = "0";
            else
                result = df.format(list.get(0));
        }
        return result;
    }

}
