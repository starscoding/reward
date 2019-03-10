package com.smile.azxx.dao.InvitationCodeMng;

import com.smile.azxx.entity.BuyInvitecode;
import com.smile.azxx.jpa.Dao;

import java.math.BigDecimal;

/**
 * Created by smile on 2018/5/17.
 */
public interface BuyInviteCodeDao extends Dao<BuyInvitecode>{

    BigDecimal countPrice(String userName);
}
