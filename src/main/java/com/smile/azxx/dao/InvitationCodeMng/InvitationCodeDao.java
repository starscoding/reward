package com.smile.azxx.dao.InvitationCodeMng;

import com.smile.azxx.entity.InvitationCodeInfo;
import com.smile.azxx.jpa.Dao;

import java.util.List;

/**
 * Created by smile on 2018/4/26.
 */
public interface InvitationCodeDao extends Dao<InvitationCodeInfo>{

    public List<InvitationCodeInfo> getCodesByStatus(String status);

    public List<InvitationCodeInfo> getCodesByCreator(String status,String creatorName);

    public boolean hasUsed(String code);
}
