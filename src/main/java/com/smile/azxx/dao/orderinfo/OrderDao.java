package com.smile.azxx.dao.orderinfo;

import com.smile.azxx.entity.RewardInfo;
import com.smile.azxx.jpa.Dao;
import com.smile.azxx.model.*;

import java.util.List;

/**
 * Created by smile on 2018/4/18.
 */
public interface OrderDao extends Dao<RewardInfo>{

    /**
     * 获取时间范围内订单数量
     * @param startTime
     * @param endTime
     * @return
     */
    List<RewarViedoInfo> getOrders(String startTime, String endTime);

    /**
     * 根据用户名获取工单
     * @param userName
     * @return
     */
    List<RewarViedoInfo> getOrdersByName(String userName);

    /**
     * 获取某用户时间范围内订单数量
     * @param startTime
     * @param endTime
     * @return
     */
    List<RewarViedoInfo> getOrders(String startTime, String endTime,String userName);

    /**
     *统计营业额总量(合伙人)
     * @param date
     * @param userName
     * @return
     */
    String countTurnover(String date,String userName);

    /**
     *统计营业额总量(代理)
     * @param date
     * @param userName
     * @return
     */
    String countTurnover4Agency(String date,String userName);

    /**
     * 统计营业额总量(管理员)
     * @param date
     * @return
     */
    String countTurnover4Admin(String date);

    /**
     * 统计订单数量（管理员）
     * @param date
     * @return
     */
    String countOrderNum(String date);

    /**
     * 统计订单数量（代理）
     * @param date
     * @return
     */
    String countOrderNum(String date,String userName);

    /**
     *统计推广数总量(合伙人)
     * @param date
     * @param userName
     * @return
     */
    int countMarketNum(String date,String userName);

    List<MarketCountInfo> getCountInfo(String startTime,String endTime,String userName);

    List<MarketUserInfo> getMarketUserInfo(String userName);

    /**
     * 计算代理用户有效下级
     * @param userName
     * @param threshold
     * @return
     */
    int countEffectiveJunior(String userName,int threshold);

    /**
     * 根据LinkId获取订单信息
     * @param linkId
     * @return
     */
    List<RewardInfo> getOrdersByLinkId(String linkId,String ip);


    List<AgencyDetail> getAgencyDetail(String effectiveJunior);

    List<PartnerDetail> getPartnerDetail();

    List<RewarViedoInfo> getOrdersByPName(String userName);

}
