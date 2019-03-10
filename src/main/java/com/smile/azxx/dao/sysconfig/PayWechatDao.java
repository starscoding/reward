package com.smile.azxx.dao.sysconfig;

import com.smile.azxx.entity.WechatpayInfo;
import com.smile.azxx.jpa.Dao;

import java.util.List;

/**
 * Created by smile on 2018/4/20.
 */
public interface PayWechatDao extends Dao<WechatpayInfo>{

    public void updateStatus(String appId,String status);

    public void setUnuse(String status);

    List<WechatpayInfo> getUseWePay();
}
