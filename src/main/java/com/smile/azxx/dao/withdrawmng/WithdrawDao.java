package com.smile.azxx.dao.withdrawmng;

import com.smile.azxx.entity.WithdrawInfo;
import com.smile.azxx.jpa.Dao;

import java.util.List;

/**
 * Created by smile on 2018/5/4.
 */
public interface WithdrawDao extends Dao<WithdrawInfo>{

    List<WithdrawInfo> getWithdrawInfo(String userName, String startTime, String endTime);

    List<WithdrawInfo> getWithdrawInfo( String startTime, String endTime);

    List<WithdrawInfo> getWithdrawByName( String userName);

    List<WithdrawInfo> getWithdrawByPName( String userName);

    String countHasWithdrawal(String userName);

    void updateWithdrawStatus(String id,String status);

    String countWithdrawAmount(String date,boolean isSuccess);
}
