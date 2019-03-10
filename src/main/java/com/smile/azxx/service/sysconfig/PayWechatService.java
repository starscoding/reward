package com.smile.azxx.service.sysconfig;

import com.smile.azxx.dao.sysconfig.PayWechatDao;
import com.smile.azxx.entity.WechatpayInfo;
import com.smile.azxx.service.common.BaseService;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by smile on 2018/4/20.
 */
@Service
@Transactional
public class PayWechatService extends BaseService {

    @Autowired
    private PayWechatDao payWechatDao;


    public List<WechatpayInfo> getAllPayConf(){
        return  payWechatDao.findAll();
    }

    public WechatpayInfo getPayConf(String appId){
        return  payWechatDao.findOne(appId);
    }

    public WechatpayInfo getUsePay(){
        WechatpayInfo result = new WechatpayInfo();
        List<WechatpayInfo> list = payWechatDao.getUseWePay();
        if(CollectionUtils.isNotEmpty(list)){
            result=list.get(0);
        }
        return result;
    }

    public void updatePayConf(WechatpayInfo wechatpayInfo){
        payWechatDao.update(wechatpayInfo);
    }

    public void addPayConf(WechatpayInfo wechatpayInfo){
        payWechatDao.save(wechatpayInfo);
    }

    public  void delPayConf(String[] appIds){
        if(ArrayUtils.isNotEmpty(appIds)){
            for (int i = 0; i < appIds.length; i++) {
                payWechatDao.delete(appIds[i]);
            }
        }

    }

    public void updateStatus(String[] appIds, String status){
        if("0".equals(status)){
            payWechatDao.setUnuse("1");
        }
        if(ArrayUtils.isNotEmpty(appIds)&& StringUtils.isNotBlank(status)){
            for (int i = 0; i < appIds.length; i++) {
                payWechatDao.updateStatus(appIds[i],status);
            }
        }

    }
}
