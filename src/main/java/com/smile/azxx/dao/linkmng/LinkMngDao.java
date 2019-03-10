package com.smile.azxx.dao.linkmng;

import com.smile.azxx.entity.LinkInfo;
import com.smile.azxx.jpa.Dao;
import com.smile.azxx.model.VideoAndLinkInfo;

import java.util.List;

/**
 * Created by smile on 2018/5/6.
 */
public interface LinkMngDao extends Dao<LinkInfo>{

    List<LinkInfo> getLinkInfo(String userName);

    List<VideoAndLinkInfo> getVideoAndLinkInfo(String userName);

    void updatePrice(String[] ids,String price);
}
