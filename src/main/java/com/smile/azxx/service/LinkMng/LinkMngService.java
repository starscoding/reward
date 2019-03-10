package com.smile.azxx.service.LinkMng;

import com.smile.azxx.dao.linkmng.LinkMngDao;
import com.smile.azxx.dao.orderinfo.OrderDao;
import com.smile.azxx.entity.LinkInfo;
import com.smile.azxx.entity.RewardInfo;
import com.smile.azxx.model.VideoAndLinkInfo;
import com.smile.azxx.service.common.BaseService;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by smile on 2018/5/6.
 */
@Service
@Transactional
public class LinkMngService extends BaseService{

    @Autowired
    private LinkMngDao linkMngDao;

    @Autowired
    private OrderDao orderDao;

    public void addLinkInfos(List<LinkInfo> linkInfos){
        if(CollectionUtils.isNotEmpty(linkInfos))
            linkMngDao.saveIterable(linkInfos);
    }

    public List<LinkInfo> getLinkInfos(String userName){
        return linkMngDao.getLinkInfo(userName);
    }

    public void delLinkInfos(String[] ids){
        if(ArrayUtils.isNotEmpty(ids)){
            for (int i = 0; i < ids.length; i++) {
                linkMngDao.delete(ids[i]);
            }
        }
    }

    public List<VideoAndLinkInfo> getVideoAndLinkInfo(String userName){
        return linkMngDao.getVideoAndLinkInfo(userName);
    }

    public List<RewardInfo> getOrdersByLinkId(String linkId){
        return orderDao.getOrdersByLinkId(linkId,null);
    }

    public LinkInfo getLinkInfo(String linkId){
        return linkMngDao.findOne(linkId);
    }

    public void updatePrice(String[] ids,String price){
        linkMngDao.updatePrice(ids,price);
    }
}
