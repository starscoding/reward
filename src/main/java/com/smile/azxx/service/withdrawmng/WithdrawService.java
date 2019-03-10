package com.smile.azxx.service.withdrawmng;

import com.smile.azxx.dao.InvitationCodeMng.BuyInviteCodeDao;
import com.smile.azxx.dao.withdrawmng.WithdrawDao;
import com.smile.azxx.entity.WithdrawInfo;
import com.smile.azxx.service.common.BaseService;
import com.smile.azxx.service.orderinfo.OrderService;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by smile on 2018/5/4.
 */
@Service
@Transactional
public class WithdrawService extends BaseService{

    @Autowired
    private WithdrawDao withdrawDao;

    @Autowired
    private OrderService orderService;

    @Autowired
    private BuyInviteCodeDao buyInviteCodeDao;

    public void add(WithdrawInfo withdrawInfo){
        withdrawDao.save(withdrawInfo);
    }

    public List<WithdrawInfo> getWithdrawInfo(String userName,String startTime,String endTime){
        if(StringUtils.isNotBlank(userName)&&"admin".equals(userName))
            return withdrawDao.getWithdrawInfo(startTime,endTime);
        else
            return withdrawDao.getWithdrawInfo(userName,startTime,endTime);
    }
    public List<WithdrawInfo> getWithdrawByName(String userName){
        return withdrawDao.getWithdrawByName(userName);
    }
    public List<WithdrawInfo> getWithdrawByPName(String userName){
        return withdrawDao.getWithdrawByPName(userName);
    }

    public String countBalance(String userName){
        //1、计算营业收入
        String trunover = orderService.countTurnover4Agency(null,userName);
        //2、计算佣金收入
        String commission = orderService.countCommission(null,userName);
        //3、计算支出
        String expense = withdrawDao.countHasWithdrawal(userName);

        BigDecimal buyCodeAmount = buyInviteCodeDao.countPrice(userName);
        //4、计算可提现金额
        BigDecimal balance = new BigDecimal(trunover).add(new BigDecimal(commission)).subtract(new BigDecimal(expense)).subtract(buyCodeAmount);
        return balance.setScale(2,BigDecimal.ROUND_HALF_UP).toString();
    }

    public void updateWithdrawStatus(String ids[],String status){
        if(ArrayUtils.isNotEmpty(ids)){
            for (int i = 0; i < ids.length; i++) {
                withdrawDao.updateWithdrawStatus(ids[i],status);
            }
        }

    }

    public String countWithdrawAmount(String date,boolean isSuccess){
        return withdrawDao.countWithdrawAmount(date,isSuccess);
    }
}
