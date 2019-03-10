package com.smile.azxx.dao.orderinfo;

import com.smile.azxx.entity.RewardInfo;
import com.smile.azxx.jpa.DaoSupport;
import com.smile.azxx.model.*;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by smile on 2018/4/18.
 */

@Component
public class OrderDaoImpl extends DaoSupport<RewardInfo> implements OrderDao {

    @PersistenceContext
    public EntityManager em;

    @Override
    public List<RewarViedoInfo> getOrders(String startTime, String endTime) {
        List<RewarViedoInfo> result = new ArrayList<>();
        String sql = "select r.AMOUNT,r.REWARD_TIME,r.VIDEO_ID,v.TITLE_,v.URL_,v.IMG_NAME from app_reward_info r left join app_video_info v on r.VIDEO_ID=v.ID_ where r.REWARD_TIME>=? and r.REWARD_TIME<=?";
        Query query = this.getEm().createNativeQuery(sql);
        query.setParameter(1,startTime);
        query.setParameter(2,endTime);
        List list = query.getResultList();
        if(CollectionUtils.isNotEmpty(list)){
            for (int i = 0; i < list.size(); i++) {
                Object[] obj = (Object[]) list.get(i);
                RewarViedoInfo temp = new RewarViedoInfo();
                temp.setAmout((BigDecimal) obj[0]);
                temp.setRewardTime(obj[1]==null?"":obj[1].toString());
                temp.setVideoId(obj[2]==null?"":obj[2].toString());
                temp.setVideoTitle(obj[3]==null?"":obj[3].toString());
                temp.setUrl(obj[4]==null?"":obj[4].toString());
                temp.setImgName(obj[5]==null?"":obj[5].toString());
                result.add(temp);
            }
        }
        return result;
    }

    @Override
    public List<RewarViedoInfo> getOrdersByName(String userName) {
        List<RewarViedoInfo> result = new ArrayList<>();
        String sql = "select r.AMOUNT,r.REWARD_TIME,r.VIDEO_ID,v.TITLE_,v.URL_ from app_reward_info r left join app_video_info v on r.VIDEO_ID=v.ID_ where r.NAME_=?";
        Query query = this.getEm().createNativeQuery(sql);
        query.setParameter(1,userName);
        List list = query.getResultList();
        if(CollectionUtils.isNotEmpty(list)){
            for (int i = 0; i < list.size(); i++) {
                Object[] obj = (Object[]) list.get(i);
                RewarViedoInfo temp = new RewarViedoInfo();
                temp.setAmout((BigDecimal) obj[0]);
                temp.setRewardTime(obj[1]==null?"":obj[1].toString());
                temp.setVideoId(obj[2]==null?"":obj[2].toString());
                temp.setVideoTitle(obj[3]==null?"":obj[3].toString());
                temp.setUrl(obj[4]==null?"":obj[4].toString());
                result.add(temp);
            }
        }
        return result;
    }

    @Override
    public List<RewarViedoInfo> getOrdersByPName(String userName) {
        List<RewarViedoInfo> result = new ArrayList<>();
        StringBuffer sql = new StringBuffer("select r.NAME_,r.AMOUNT,r.REWARD_TIME,v.TITLE_,u.parent_name from app_reward_info r ");
        sql.append(" left join sys_user u on r.NAME_=u.username");
        sql.append(" left join app_video_info v on r.VIDEO_ID=v.ID_");
        sql.append(" where u.parent_name=?");
        Query query = this.getEm().createNativeQuery(sql.toString());
        query.setParameter(1,userName);
        List list = query.getResultList();
        if(CollectionUtils.isNotEmpty(list)){
            for (int i = 0; i < list.size(); i++) {
                Object[] obj = (Object[]) list.get(i);
                RewarViedoInfo temp = new RewarViedoInfo();
                temp.setUserName(obj[0]==null?"":obj[0].toString());
                temp.setAmout(obj[1]==null?new BigDecimal("0"):new BigDecimal(obj[1].toString()));
                temp.setRewardTime(obj[2]==null?"":obj[2].toString());
                temp.setVideoTitle(obj[3]==null?"":obj[3].toString());
                result.add(temp);
            }
        }
        return result;
    }

    @Override
    public List<RewarViedoInfo> getOrders(String startTime, String endTime, String userName) {
        List<RewarViedoInfo> result = new ArrayList<>();
        String sql = "select r.AMOUNT,r.REWARD_TIME,r.VIDEO_ID,v.TITLE_,v.URL_,v.IMG_NAME from app_reward_info r left join app_video_info v on r.VIDEO_ID=v.ID_ where r.REWARD_TIME>=? and r.REWARD_TIME<=? and r.NAME_=?";
        Query query = this.getEm().createNativeQuery(sql);
        query.setParameter(1,startTime);
        query.setParameter(2,endTime);
        query.setParameter(3,userName);
        List list = query.getResultList();
        if(CollectionUtils.isNotEmpty(list)){
            for (int i = 0; i < list.size(); i++) {
                Object[] obj = (Object[]) list.get(i);
                RewarViedoInfo temp = new RewarViedoInfo();
                temp.setAmout((BigDecimal) obj[0]);
                temp.setRewardTime(obj[1]==null?"":obj[1].toString());
                temp.setVideoId(obj[2]==null?"":obj[2].toString());
                temp.setVideoTitle(obj[3]==null?"":obj[3].toString());
                temp.setUrl(obj[4]==null?"":obj[4].toString());
                temp.setImgName(obj[5]==null?"":obj[5].toString());
                result.add(temp);
            }
        }
        return result;
    }

    @Override
    public String countTurnover(String date, String userName) {
        DecimalFormat df = new DecimalFormat("#.##");
        String result = "0";
        StringBuffer sql = new StringBuffer("select sum(AMOUNT) AMOUNT from app_reward_info where NAME_ in");
        sql.append("(");
        sql.append("select ACTIVE_NAME from app_invitation_code_info where STATUS_='1' and CREATE_NAME=?");
        sql.append(")");
        if(StringUtils.isNotBlank(date)){
            sql.append(" and REWARD_TIME like ?");
        }
        Query query = this.getEm().createNativeQuery(sql.toString());
        query.setParameter(1,userName);
        if(StringUtils.isNotBlank(date)){
            query.setParameter(2,date+"%");
        }
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
    public String countTurnover4Agency(String date, String userName) {
        DecimalFormat df = new DecimalFormat("#.##");
        String result = "0";
        StringBuffer sql = new StringBuffer("select sum(AMOUNT) AMOUNT from app_reward_info where NAME_=?");
        if(StringUtils.isNotBlank(date)){
            sql.append(" and REWARD_TIME like ?");
        }
        Query query = this.getEm().createNativeQuery(sql.toString());
        query.setParameter(1,userName);
        if(StringUtils.isNotBlank(date)){
            query.setParameter(2,date+"%");
        }
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
    public String countTurnover4Admin(String date) {
        DecimalFormat df = new DecimalFormat("#.##");
        String result = "0";
        StringBuffer sql = new StringBuffer("select sum(AMOUNT) AMOUNT from app_reward_info where 1=1");
        if(StringUtils.isNotBlank(date)){
            sql.append(" and REWARD_TIME like ?");
        }
        Query query = this.getEm().createNativeQuery(sql.toString());
        if(StringUtils.isNotBlank(date)){
            query.setParameter(1,date+"%");
        }
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
    public String countOrderNum(String date) {
        String result = "0";
        StringBuffer sql = new StringBuffer("select count(*) AMOUNT from app_reward_info where 1=1");
        if(StringUtils.isNotBlank(date)){
            sql.append(" and REWARD_TIME like ?");
        }
        Query query = this.getEm().createNativeQuery(sql.toString());
        if(StringUtils.isNotBlank(date)){
            query.setParameter(1,date+"%");
        }
        List list = query.getResultList();
        if(CollectionUtils.isNotEmpty(list)) {
            if(list.get(0)==null)
                result = "0";
            else
                result = list.get(0).toString();
        }
        return result;
    }

    @Override
    public String countOrderNum(String date, String userName) {
        String result = "0";
        StringBuffer sql = new StringBuffer("select count(*) AMOUNT from app_reward_info where NAME_=?");
        if(StringUtils.isNotBlank(date)){
            sql.append(" and REWARD_TIME like ?");
        }
        Query query = this.getEm().createNativeQuery(sql.toString());
        query.setParameter(1,userName);
        if(StringUtils.isNotBlank(date)){
            query.setParameter(2,date+"%");
        }
        List list = query.getResultList();
        if(CollectionUtils.isNotEmpty(list)) {
            if(list.get(0)==null)
                result = "0";
            else
                result = list.get(0).toString();
        }
        return result;
    }

    @Override
    public int countMarketNum(String date, String userName) {
        int result = 0;
        StringBuffer sql = new StringBuffer("select count(CODE_) NUM from app_invitation_code_info where STATUS_='1' and CREATE_NAME=?");
        if(StringUtils.isNotBlank(date)){
            sql.append(" and ACTIVE_TIME like ?");
        }
        Query query = this.getEm().createNativeQuery(sql.toString());
        query.setParameter(1,userName);
        if(StringUtils.isNotBlank(date)){
            query.setParameter(2,date+"%");
        }
        List list = query.getResultList();
        if(CollectionUtils.isNotEmpty(list)) {
//            Object[] obj = (Object[]) list.get(0);
            if(list.get(0)==null)
                result = 0;
            else
                result = Integer.parseInt(list.get(0).toString());
        }
        return result;
    }

    @Override
    public List<MarketCountInfo> getCountInfo(String startTime, String endTime, String userName) {
        List<MarketCountInfo> result = new ArrayList<>();
        StringBuffer sql = new StringBuffer("select a.ACTIVE_TIME,a.NUM,b.AMOUNT from");
        sql.append(" (select substring(ACTIVE_TIME, 1,10) as ACTIVE_TIME,count(CODE_) NUM from app_invitation_code_info where STATUS_='1' and CREATE_NAME=?");
        sql.append(" and substring(ACTIVE_TIME, 1,10) >=? and substring(ACTIVE_TIME, 1,10)<=? group by substring(ACTIVE_TIME, 1,10)) a,");
        sql.append(" (select substring(REWARD_TIME, 1,10) as REWARD_TIME,sum(AMOUNT) AMOUNT from app_reward_info where NAME_ in");
        sql.append(" (select ACTIVE_NAME from app_invitation_code_info where STATUS_='1' and CREATE_NAME=?) and substring(REWARD_TIME, 1,10) >= ? and substring(REWARD_TIME, 1,10)<=?");
        sql.append(" group by substring(REWARD_TIME, 1,10)) b where");
        sql.append(" a.ACTIVE_TIME=b.REWARD_TIME");
        log.info(sql.toString());
        Query query = this.getEm().createNativeQuery(sql.toString());
        query.setParameter(1,userName);
        query.setParameter(2,startTime);
        query.setParameter(3,endTime);
        query.setParameter(4,userName);
        query.setParameter(5,startTime);
        query.setParameter(6,endTime);
        List list = query.getResultList();
        if(CollectionUtils.isNotEmpty(list)) {
            for (int i = 0; i <list.size() ; i++) {
                Object[] obj = (Object[]) list.get(i);
                MarketCountInfo temp = new MarketCountInfo();
                temp.setDate(obj[0]==null?"":obj[0].toString());
                temp.setMarketNum(obj[1]==null?"":obj[1].toString());
                temp.setTurnover(obj[2]==null?"":obj[2].toString());
                result.add(temp);
            }
        }
        return result;
    }

    @Override
    public List<MarketUserInfo> getMarketUserInfo(String userName) {
        List<MarketUserInfo> result = new ArrayList<>();
        StringBuffer sql = new StringBuffer("select u.username,u.create_time,r.amount,u.wechat,u.invitation_code,u.parent_name from sys_user u");
        sql.append(" left join (select NAME_,sum(AMOUNT) amount from app_reward_info group by NAME_) r on u.username=r.name_");
        sql.append(" where u.parent_name=?");
        Query query = this.getEm().createNativeQuery(sql.toString());
        query.setParameter(1,userName);
        List list = query.getResultList();
        if(CollectionUtils.isNotEmpty(list)){
            for (int i = 0; i < list.size(); i++) {
                Object[] obj = (Object[]) list.get(i);
                MarketUserInfo temp = new MarketUserInfo();
                temp.setUserName(obj[0]==null?"":obj[0].toString());
                temp.setCreateTime(obj[1]==null?"":obj[1].toString());
                temp.setAmount(obj[2]==null?"":obj[2].toString());
                temp.setWechat(obj[3]==null?"":obj[3].toString());
                temp.setInvitationCode(obj[4]==null?"":obj[4].toString());
                temp.setInvitationName(obj[5]==null?"":obj[5].toString());
                result.add(temp);
            }
        }
        return result;
    }

    @Override
    public int countEffectiveJunior(String userName, int threshold) {
        int result = 0;
        StringBuffer sql = new StringBuffer("select NAME_,sum(AMOUNT) AMOUNT from app_reward_info where NAME_ in");
        sql.append("(");
        sql.append("select ACTIVE_NAME from app_invitation_code_info where STATUS_='1' and CREATE_NAME=?");
        sql.append(") group by NAME_ having AMOUNT>?");

        Query query = this.getEm().createNativeQuery(sql.toString());
        query.setParameter(1,userName);
        query.setParameter(2,threshold);

        List list = query.getResultList();
        if(CollectionUtils.isNotEmpty(list)) {
            result = list.size();
        }
        return result;
    }

    @Override
    public List<RewardInfo> getOrdersByLinkId(String linkId,String ip) {
        String sql = "select r from RewardInfo r where r.linkId=?1 ";
        if(StringUtils.isNotBlank(ip)){
            sql += " and r.ipAddress=?2";
        }
        Query query = this.getEm().createQuery(sql,RewardInfo.class);
        query.setParameter(1,linkId);
        if(StringUtils.isNotBlank(ip)) {
            query.setParameter(2, ip);
        }
        return query.getResultList();
    }

    @Override
    public List<AgencyDetail> getAgencyDetail(String effectiveJunior) {
        List<AgencyDetail> result = new ArrayList<>();
        StringBuffer sql = new StringBuffer("select u.username,u.create_time,u.wechat,u.qq,u.parent_name,d.CREATE_NAME,a.amount,c.num,e.famount,f.effectNum from sys_user u");
        sql.append(" left join (select NAME_ name,sum(AMOUNT) amount from app_reward_info group by NAME_) a on u.username=a.name");
        sql.append(" left join (select parent_name name, count(id) num from sys_user group by parent_name) c on u.username=c.name");
        sql.append(" left join app_invitation_code_info d on u.username=d.ACTIVE_NAME");
        sql.append(" left join (select NAME_ name,sum(AMOUNT) famount from app_withdraw_info where OP_STATUS!='1' group by NAME_) e on u.username=e.name");
        sql.append(" left join (select b.parent_name name,count(*) effectNum from ");
        sql.append(" (select NAME_ name,sum(AMOUNT) num from app_reward_info  group by NAME_ having num>?) a");
        sql.append(" left join sys_user b on a.name = b.username");
        sql.append(" group by b.parent_name");
        sql.append(" ) f on u.username = f.name");
        sql.append(" where u.type_='4BE44AC464FFCE79E15B1DF31F9DAB27'");
        log.info(sql.toString());
        Query query = this.getEm().createNativeQuery(sql.toString());
        query.setParameter(1,new BigDecimal(effectiveJunior));
        List list = query.getResultList();
        if(CollectionUtils.isNotEmpty(list)){
            for (int i = 0; i < list.size(); i++) {
                Object[] obj = (Object[]) list.get(i);
                AgencyDetail temp = new AgencyDetail();
                temp.setUserName(obj[0]==null?"":obj[0].toString());
                temp.setCreateTime(obj[1]==null?"":obj[1].toString());
                temp.setWechat(obj[2]==null?"":obj[2].toString());
                temp.setQq(obj[3]==null?"":obj[3].toString());
                temp.setParent(obj[4]==null?"":obj[4].toString());
                temp.setPartner(obj[5]==null?"":obj[5].toString());
                temp.setTotal(obj[6]==null? BigDecimal.valueOf(0) :new BigDecimal(obj[6].toString()));
                temp.setTotalAgency(obj[7]==null?"0":obj[7].toString());
                temp.setHasWithdraw(obj[8]==null? BigDecimal.valueOf(0) :new BigDecimal(obj[8].toString()));
                temp.setEffectNum(obj[9]==null?"0":obj[9].toString());
                result.add(temp);
            }
        }
        return result;
    }

    @Override
    public List<PartnerDetail> getPartnerDetail() {
        List<PartnerDetail> result = new ArrayList<>();
        StringBuffer sql = new StringBuffer("select u.username,u.wechat,u.qq,n.num,m.amount,h.famount from sys_user u");
        sql.append(" left join sys_role r on r.id=u.type_");
        sql.append(" left join (select CREATE_NAME name,count(CODE_) num from app_invitation_code_info where STATUS_='1' group by CREATE_NAME) n on u.username=n.name");
        sql.append(" left join (select us.parent_name name,sum(re.AMOUNT) amount from app_reward_info re left join sys_user us on re.NAME_=us.username group by us.parent_name) m on m.name=u.username");
        sql.append(" left join (select us.parent_name name,sum(wi.AMOUNT) famount from app_withdraw_info wi left join sys_user us on wi.NAME_=us.username where wi.OP_STATUS='1' group by us.parent_name) h on h.name=u.username ");
        sql.append(" where r.name='合伙人'");
        log.info(sql.toString());
        Query query = this.getEm().createNativeQuery(sql.toString());
        List list = query.getResultList();
        if(CollectionUtils.isNotEmpty(list)){
            for (int i = 0; i < list.size(); i++) {
                Object[] obj = (Object[]) list.get(i);
                PartnerDetail temp = new PartnerDetail();
                temp.setUserName(obj[0]==null?"":obj[0].toString());
                temp.setWechat(obj[1]==null?"":obj[1].toString());
                temp.setQq(obj[2]==null?"":obj[2].toString());
                temp.setAgencyNum(obj[3]==null?"":obj[3].toString());
                temp.setTotal(obj[4]==null?"":obj[4].toString());
                temp.setWithdraw(obj[5]==null?"":obj[5].toString());
                result.add(temp);
            }
        }
        return result;
    }

}