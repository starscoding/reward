package com.smile.azxx.service.invitationcode;

import com.smile.azxx.dao.InvitationCodeMng.BuyInviteCodeDao;
import com.smile.azxx.dao.InvitationCodeMng.InvitationCodeDao;
import com.smile.azxx.entity.BuyInvitecode;
import com.smile.azxx.entity.ConfInfo;
import com.smile.azxx.entity.InvitationCodeInfo;
import com.smile.azxx.service.common.BaseService;
import com.smile.azxx.service.orderinfo.OrderService;
import com.smile.azxx.service.sysconfig.SysConfService;
import com.smile.azxx.service.withdrawmng.WithdrawService;
import com.smile.azxx.util.RandomGUID;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * Created by smile on 2018/4/26.
 */
@Service
@Transactional
public class InvitationCodeService extends BaseService {

    @Autowired
    private InvitationCodeDao invitationCodeDao;

    @Autowired
    private SysConfService sysConfService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private WithdrawService withdrawService;

    @Autowired
    private BuyInviteCodeDao buyInviteCodeDao;

    /**
     * 创建邀请码
     *
     * @param num 邀请码数量
     */
    public List<InvitationCodeInfo> createInvitationCode(int num, String createName) {

        List<InvitationCodeInfo> createList = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = sdf.format(new Date());
        if (num > 0) {
            for (int i = 0; i < num; i++) {
                InvitationCodeInfo code = new InvitationCodeInfo(this.getCodeId(), time, "0", createName);
                createList.add(code);
            }
            invitationCodeDao.saveIterable(createList);
        }
        return createList;
    }

    /**
     * 获取邀请码
     *
     * @param status   邀请码激活状态 0-未激活 1-已激活
     * @param userName 创建者名称
     * @return
     */
    public List<InvitationCodeInfo> getCodesByStatus(String status, String userName) {
        if ("admin".equals(userName)) {
            return invitationCodeDao.getCodesByStatus(status);
        } else {
            return invitationCodeDao.getCodesByCreator(status, userName);
        }

    }

    /**
     * 判断邀请码是否被使用
     *
     * @param code
     * @return true-已使用  false-未使用
     */
    public boolean hasUsed(String code) {
        return invitationCodeDao.hasUsed(code);
    }

    public void updateInvitationCodeInfo(String activeName, String status, String code) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        InvitationCodeInfo codeInfo = invitationCodeDao.findOne(code);
        codeInfo.setStatus(status);
        codeInfo.setActiveName(activeName);
        codeInfo.setActiveTime(sdf.format(new Date()));
        invitationCodeDao.update(codeInfo);
    }

    /**
     * 生成邀请码
     *
     * @return
     */
    public String getCodeId() {
        String val = "";

        Random random = new Random();
        for (int i = 0; i < 12; i++) {
            String charOrNum = random.nextInt(2) % 2 == 0 ? "char" : "num"; // 输出字母还是数字

            if ("char".equalsIgnoreCase(charOrNum)) // 字符串
            {
                int choice = random.nextInt(2) % 2 == 0 ? 65 : 97; //取得大写字母还是小写字母
                val += (char) (choice + random.nextInt(26));
            } else if ("num".equalsIgnoreCase(charOrNum)) // 数字
            {
                val += String.valueOf(random.nextInt(10));
            }
        }
        return val;
    }

    public String buyInviteCode(String userName, String num) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String msg = "";
        //1.获取邀请码条件和价格
        ConfInfo invitationCondition = sysConfService.getConfByName("invitationCondition");
        ConfInfo invitationCodePrice = sysConfService.getConfByName("invitationCode");
        //2.获取用户营业额
        String trunover = orderService.countTurnover4Agency(null, userName);
        if (new BigDecimal(invitationCondition.getValue()).compareTo(new BigDecimal(trunover)) > -1) {
            msg = "未达到购买邀请码条件！";
            return msg;
        }
        //3.获取用户余额
        String balance = withdrawService.countBalance(userName);
        //4.计算要花费的金额
        BigDecimal payTotal = new BigDecimal(invitationCodePrice.getValue()).multiply(new BigDecimal(num));
        //5.不满足条件则返回错误信息
        if (payTotal.compareTo(new BigDecimal(balance)) > -1) {
            msg = "余额不足！";
            return msg;
        }
        //6.若满足条件，则生成邀请码，并保存购买记录
        List<InvitationCodeInfo> codes = this.createInvitationCode(Integer.parseInt(num), userName);
        List<BuyInvitecode> buys = new ArrayList<>();
        String nowTime = sdf.format(new Date());
        if(CollectionUtils.isNotEmpty(codes)){
            for (int i = 0; i < codes.size(); i++) {
                BigDecimal price = new BigDecimal(invitationCodePrice.getValue());
                buys.add(new BuyInvitecode(new RandomGUID().getUUID32(),userName,codes.get(i).getCode(),price,nowTime));
            }
        }
        if(CollectionUtils.isNotEmpty(buys)){
            buyInviteCodeDao.saveIterable(buys);
        }
        return msg;
    }
}
