package com.smile.azxx.service.orderinfo;

import com.smile.azxx.dao.orderinfo.OrderDao;
import com.smile.azxx.dao.sysconfig.SysConfDao;
import com.smile.azxx.dao.videomng.VideoDao;
import com.smile.azxx.entity.RewardInfo;
import com.smile.azxx.entity.VideoInfo;
import com.smile.azxx.model.*;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by smile on 2018/4/18.
 */
@Service
@Transactional
public class OrderService {

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private SysConfDao sysConfDao;

    @Autowired
    private VideoDao videoDao;

    public RewardInfo getOrderById(String id){
        return orderDao.findOne(id);
    }

    public void updateOrderInfo(RewardInfo rewardInfo){
        orderDao.update(rewardInfo);
    }

    public List<RewarViedoInfo> getOrders(String startTime, String endTime, String userName) {
        List<RewarViedoInfo> result = new ArrayList<>();
        if (StringUtils.isNotBlank(userName) && !userName.equals("admin")) {
            result = orderDao.getOrders(startTime, endTime, userName);
        } else if(StringUtils.isNotBlank(userName) && userName.equals("admin")){
            result = orderDao.getOrders(startTime, endTime);
        }
        return result;
    }

    public List<MarketCountInfo> getCountInfo(String startTime, String endTime, String userName) {
        return orderDao.getCountInfo(startTime, endTime, userName);
    }

    /**
     * 统计营业额总量（合伙人）
     *
     * @param date
     * @param userName
     * @return
     */
    public String countTurnover(String date, String userName) {
        return orderDao.countTurnover(date, userName);
    }

    /**
     * 统计营业额总量(代理）
     *
     * @param date
     * @param userName
     * @return
     */
    public String countTurnover4Agency(String date, String userName) {
        return orderDao.countTurnover4Agency(date, userName);
    }

    /**
     * 统计营业额总量(管理员）
     *
     * @param date
     * @return
     */
    public String countTurnover4Admin(String date) {
        return orderDao.countTurnover4Admin(date);
    }

    /**
     * 统计订单数量
     *
     * @param date
     * @param userName
     * @return
     */
    public String countOrderNum(String date, String userName) {
        if (StringUtils.isNotBlank(userName) && "admin".equals(userName)) {
            return orderDao.countOrderNum(date);
        } else {
            return orderDao.countOrderNum(date, userName);
        }
    }

    /**
     * 统计推广数总量
     *
     * @param date
     * @param userName
     * @return
     */
    public int countMarketNum(String date, String userName) {
        return orderDao.countMarketNum(date, userName);
    }

    /**
     * 获取合伙人推广的代理信息
     *
     * @param userName
     * @return
     */
    public List<MarketUserInfo> getMarketUserInfo(String userName) {
        return orderDao.getMarketUserInfo(userName);
    }


    /**
     * 统计佣金金额（代理）
     *
     * @param date
     * @param userName
     * @return
     */
    public String countCommission(String date, String userName) {

        //1、计算代理等级
        //1.1、获取有效下级条件
        Map<String, String> confInfo = sysConfDao.getMapConf("agencyConf");
        //1.2、计算代理用户下级个数
        int effectiveJuniorNum = orderDao.countEffectiveJunior(userName, Integer.parseInt(confInfo.get("effectiveJunior")));
        //1.3、计算代理等级,获取佣金比例
        int commission = 0;//佣金比例
        if (effectiveJuniorNum < Integer.parseInt(confInfo.get("primaryAgency"))) {
            commission = Integer.parseInt(confInfo.get("generalReward"));
        } else if (effectiveJuniorNum < Integer.parseInt(confInfo.get("advancedAgency"))) {
            commission = Integer.parseInt(confInfo.get("primaryReward"));
        } else {
            commission = Integer.parseInt(confInfo.get("advancedReward"));
        }
        //2、计算下级用户收入
        String rewardLowStr = orderDao.countTurnover(date, userName);
        BigDecimal rewardLow = new BigDecimal(rewardLowStr);
        BigDecimal commssionDec = new BigDecimal(commission);
        //3、计算佣金（下级用户收入*佣金比例）
        String result = rewardLow.multiply(commssionDec).toString();
        return result;
    }

    public List<AgencyDetail> getAgencyDetail() {
        List<AgencyDetail> list = new ArrayList<>();
        Map<String, String> confInfo = sysConfDao.getMapConf("agencyConf");
        list = orderDao.getAgencyDetail(confInfo.get("effectiveJunior"));
        if (CollectionUtils.isNotEmpty(list)) {
            for (int i = 0; i < list.size(); i++) {
                list.get(i).setLevel(this.countAgencyLevel(confInfo, list.get(i).getEffectNum()));
                list.get(i).setNotWithdraw(list.get(i).getTotal().subtract(list.get(i).getHasWithdraw()));
            }
        }
        return list;
    }

    public boolean isReward(String linkId, String ip) {
        List list = orderDao.getOrdersByLinkId(linkId, ip);
        if (CollectionUtils.isNotEmpty(list)) {
            return true;
        } else {
            return false;
        }
    }

    public List<VideoInfo> getVideoByLinkId(String linkId) {
        return videoDao.getVideoByLinkId(linkId);
    }

    public void add(RewardInfo rewardInfo) {
        orderDao.save(rewardInfo);
    }

    public String countAgencyLevel(Map<String, String> conf, String effectiveNumStr) {
        String result = "";
        if (StringUtils.isBlank(effectiveNumStr))
            result = "普通代理";
        else {
            int effectiveNum = Integer.parseInt(effectiveNumStr);
            if (effectiveNum < Integer.parseInt(conf.get("primaryAgency"))) {
                result = "普通代理";
            } else if (effectiveNum < Integer.parseInt(conf.get("advancedAgency"))) {
                result = "初级代理";
            } else {
                result = "高级代理";
            }
        }
        return result;
    }

    public List<RewarViedoInfo> getOrdersByName(String userName) {
        return orderDao.getOrdersByName(userName);
    }

    public List<PartnerDetail> getPartnerDetail() {
        return orderDao.getPartnerDetail();
    }

    public List<RewarViedoInfo> getOrdersByPName(String userName){
        return orderDao.getOrdersByPName(userName);
    }

}
