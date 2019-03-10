package com.smile.azxx.web.sysconfig;

import com.smile.azxx.entity.ConfInfo;
import com.smile.azxx.entity.WechatpayInfo;
import com.smile.azxx.service.sysconfig.PayWechatService;
import com.smile.azxx.service.sysconfig.SysConfService;
import com.smile.azxx.util.NumberValidationUtils;
import com.smile.azxx.util.RandomGUID;
import com.smile.azxx.web.common.BaseController;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by smile on 2018/4/20.
 */
@Controller
@RequestMapping(value = "configService")
public class sysconfigController extends BaseController{

    @Autowired
    private PayWechatService payWechatService;

    @Autowired
    private SysConfService sysConfService;

    @RequestMapping(value = "getPayConf")
    public void getPayConfig(HttpServletRequest request, HttpServletResponse response){
        try {
            List<WechatpayInfo> result = payWechatService.getAllPayConf();
            this.addResultInfo(FAILURE,"获取支付配置成功！",result);
            this.responseResult(response,this.getResultJSONStr());
        }catch (Exception e){
            e.printStackTrace();
            this.addResultInfo(FAILURE,"获取支付配置失败！",null);
            this.responseResult(response,this.getResultJSONStr());
        }
    }

    @RequestMapping(value = "addOrUpdatePayConf")
    public void addOrUpdateConfig(@RequestParam MultipartFile file,
                                  HttpServletRequest request, HttpServletResponse response){
        String appId = request.getParameter("appId");
        String appSecret = request.getParameter("appSecret");
        String comment = request.getParameter("comment");
        String bizAccount = request.getParameter("bizAccount");
        String key = request.getParameter("key");
        String reqType = request.getParameter("reqType");
        String fileName = null;
        String filePath = null;
//        Part file = null;
        //            file = request.getPart("file");
        fileName = file.getOriginalFilename();



        try {
            ConfInfo conf = sysConfService.getConfByName("authTxtPath");
            filePath = conf.getValue()+fileName;
            WechatpayInfo wechatpayInfo = null;
            if(StringUtils.isNotBlank(reqType)&& "update".equals(reqType)){
                wechatpayInfo = payWechatService.getPayConf(appId);
                wechatpayInfo.setAppsecret(appSecret);
                wechatpayInfo.setBizAccount(bizAccount);
                wechatpayInfo.setComment(comment);
                wechatpayInfo.setKey(key);
                wechatpayInfo.setTxtUrl(filePath);
                wechatpayInfo.setFileName(fileName);
            }else{
                wechatpayInfo = new WechatpayInfo();
                wechatpayInfo.setAppid(appId);
                wechatpayInfo.setAppsecret(appSecret);
                wechatpayInfo.setBizAccount(bizAccount);
                wechatpayInfo.setComment(comment);
                wechatpayInfo.setKey(key);
                wechatpayInfo.setTxtUrl(filePath);
                wechatpayInfo.setFileName(fileName);
                wechatpayInfo.setStatus("1");
            }
            this.saveFile(file.getInputStream(),conf.getValue(),fileName);
            if(StringUtils.isNotBlank(reqType)&& "update".equals(reqType)){
                payWechatService.updatePayConf(wechatpayInfo);
            }else{
                payWechatService.addPayConf(wechatpayInfo);
            }
            this.addResultInfo(SUCCESS,"新增/编辑支付配置成功！",null);
            this.responseResult(response,this.getResultJSONStr());
        }catch (Exception e){
            e.printStackTrace();
            this.addResultInfo(FAILURE,"新增/编辑支付配置失败！",null);
            this.responseResult(response,this.getResultJSONStr());
        }
    }
    @RequestMapping(value = "updatePayConf")
    public void updatePayConf(HttpServletRequest request, HttpServletResponse response){
        String appId = request.getParameter("appId");
        String appSecret = request.getParameter("appSecret");
        String comment = request.getParameter("comment");
        String bizAccount = request.getParameter("bizAccount");
        String key = request.getParameter("key");

        WechatpayInfo wechatpayInfo = payWechatService.getPayConf(appId);
        wechatpayInfo.setAppsecret(appSecret);
        wechatpayInfo.setBizAccount(bizAccount);
        wechatpayInfo.setComment(comment);
        wechatpayInfo.setKey(key);

        try {
            payWechatService.updatePayConf(wechatpayInfo);
            this.addResultInfo(SUCCESS,"新增/编辑支付配置成功！",null);
            this.responseResult(response,this.getResultJSONStr());
        }catch (Exception e){
            e.printStackTrace();
            this.addResultInfo(FAILURE,"新增/编辑支付配置失败！",null);
            this.responseResult(response,this.getResultJSONStr());
        }
    }

    @RequestMapping(value = "delPayConf")
    public void delConfig(HttpServletRequest request, HttpServletResponse response){
        String[] appIds = request.getParameterValues("appIds[]");
        try {
            payWechatService.delPayConf(appIds);
            this.addResultInfo(SUCCESS,"删除支付配置成功！",null);
            this.responseResult(response,this.getResultJSONStr());
        }catch (Exception e){
            e.printStackTrace();
            this.addResultInfo(FAILURE,"删除支付配置失败！",null);
            this.responseResult(response,this.getResultJSONStr());
        }
    }

    @RequestMapping(value = "updateStatus")
    public void updateStatus(HttpServletRequest request, HttpServletResponse response){
        String[] appIds = request.getParameterValues("appIds[]");
        String status = request.getParameter("status");
        try {
            payWechatService.updateStatus(appIds,status);
            this.addResultInfo(SUCCESS,"更新支付接口状态成功！",null);
            this.responseResult(response,this.getResultJSONStr());
        }catch (Exception e){
            e.printStackTrace();
            this.addResultInfo(FAILURE,"更新支付接口状态失败！",null);
            this.responseResult(response,this.getResultJSONStr());
        }
    }

    @RequestMapping(value = "updateAgencyConf")
    public void updateAgencyConf(HttpServletRequest request,HttpServletResponse response){
        String effectiveJunior = request.getParameter("effectiveJunior");
        String generalReward = request.getParameter("generalReward");
        String primaryAgency = request.getParameter("primaryAgency");
        String primaryReward = request.getParameter("primaryReward");
        String advancedAgency = request.getParameter("advancedAgency");
        String advancedReward = request.getParameter("advancedReward");

        String msg = "";

        if(isNotInt(effectiveJunior)||isNotInt(primaryAgency)||isNotInt(generalReward)||isNotInt(primaryReward)||isNotInt(advancedAgency)||isNotInt(advancedReward))
            msg = "输入参数必须是整数！";



        try {
            if(StringUtils.isBlank(msg)){
                List<ConfInfo> confInfos = new ArrayList<>();
                confInfos.add(new ConfInfo(new RandomGUID().getUUID32(),"agencyConf","effectiveJunior","有效下级",effectiveJunior));
                confInfos.add(new ConfInfo(new RandomGUID().getUUID32(),"agencyConf","generalReward","初级代理佣金",generalReward));
                confInfos.add(new ConfInfo(new RandomGUID().getUUID32(),"agencyConf","primaryAgency","普通代理有效下级数量",primaryAgency));
                confInfos.add(new ConfInfo(new RandomGUID().getUUID32(),"agencyConf","primaryReward","普通代理佣金",primaryReward));
                confInfos.add(new ConfInfo(new RandomGUID().getUUID32(),"agencyConf","advancedAgency","高级代理有效下级数量",advancedAgency));
                confInfos.add(new ConfInfo(new RandomGUID().getUUID32(),"agencyConf","advancedReward","高级代理佣金",advancedReward));
                sysConfService.updateAgencyConf(confInfos,"agencyConf");
                msg = "更新成功！";
                this.addResultInfo(SUCCESS,msg,null);
                this.responseResult(response,this.getResultJSONStr());
            }else{
                this.addResultInfo(FAILURE,msg,null);
                this.responseResult(response,this.getResultJSONStr());
            }


        }catch (Exception e){
            e.printStackTrace();
            this.addResultInfo(FAILURE,"更新代理配置失败！",null);
            this.responseResult(response,this.getResultJSONStr());
        }

    }

    @RequestMapping(value = "updateSysConf")
    public void updateSysConf(HttpServletRequest request,HttpServletResponse response){
        String safeDomain = request.getParameter("safeDomain");
        String invitationCode = request.getParameter("invitationCode");
        String invitationCondition = request.getParameter("invitationCondition");
        String kfWechat = request.getParameter("kfWechat");
        String withdraw = request.getParameter("withdraw");
        String notice = request.getParameter("notice");
        String authDomain = request.getParameter("authDomain");
        String maintainflag = request.getParameter("maintainflag");
        String codeNotice = request.getParameter("codeNotice");
        String withdrawNotice = request.getParameter("withdrawNotice");

        String msg = "";

        if(!NumberValidationUtils.isPositiveInteger(invitationCode))
            msg = "邀请码价格必须为整数！";
        if(!NumberValidationUtils.isPositiveInteger(invitationCondition))
            msg = "邀请码条件必须为整数！";
        if(!NumberValidationUtils.isPositiveInteger(withdraw))
            msg = "佣金比例必须为整数";


        try {
            if(StringUtils.isBlank(msg)){
                List<ConfInfo> confInfos = new ArrayList<>();
                confInfos.add(new ConfInfo(new RandomGUID().getUUID32(),"sysConf","safeDomain","安全域名",safeDomain));
                confInfos.add(new ConfInfo(new RandomGUID().getUUID32(),"sysConf","invitationCode","邀请码价格",invitationCode));
                confInfos.add(new ConfInfo(new RandomGUID().getUUID32(),"sysConf","invitationCondition","邀请码条件",invitationCondition));
                confInfos.add(new ConfInfo(new RandomGUID().getUUID32(),"sysConf","kfWechat","客服微信",kfWechat));
                confInfos.add(new ConfInfo(new RandomGUID().getUUID32(),"sysConf","withdraw","提现扣除佣金（百分比)",withdraw));
                confInfos.add(new ConfInfo(new RandomGUID().getUUID32(),"sysConf","notice","公告",notice));
                confInfos.add(new ConfInfo(new RandomGUID().getUUID32(),"sysConf","authDomain","支付接口鉴权域名",authDomain));
                confInfos.add(new ConfInfo(new RandomGUID().getUUID32(),"sysConf","maintainflag","是否开启维护0-开启，1-不开启",maintainflag));
                confInfos.add(new ConfInfo(new RandomGUID().getUUID32(),"sysConf","codeNotice","邀请码说明",codeNotice));
                confInfos.add(new ConfInfo(new RandomGUID().getUUID32(),"sysConf","withdrawNotice","提现说明",withdrawNotice));
                sysConfService.updateAgencyConf(confInfos,"sysCOnf" );
                msg = "更新成功！";
                this.addResultInfo(SUCCESS,msg,null);
                this.responseResult(response,this.getResultJSONStr());
            }else{
                this.addResultInfo(FAILURE,msg,null);
                this.responseResult(response,this.getResultJSONStr());
            }
        }catch (Exception e){
            e.printStackTrace();
            this.addResultInfo(FAILURE,"更新系统配置失败！",null);
            this.responseResult(response,this.getResultJSONStr());
        }

    }

    @RequestMapping(value = "updatePathConf")
    public void updatePathConf(HttpServletRequest request,HttpServletResponse response){
        String authTxtPath = request.getParameter("authTxtPath");
        String videoPath = request.getParameter("videoPath");
        String videoImgPath = request.getParameter("videoImgPath");
        log.info(authTxtPath+","+videoPath+","+videoImgPath);
        try {
            List<ConfInfo> confInfos = new ArrayList<>();
            confInfos.add(new ConfInfo(new RandomGUID().getUUID32(),"filepath","authTxtPath","验证文件路径",authTxtPath));
            confInfos.add(new ConfInfo(new RandomGUID().getUUID32(),"filepath","videoPath","视频保存路径",videoPath));
            confInfos.add(new ConfInfo(new RandomGUID().getUUID32(),"filepath","videoImgPath","视频缩略图",videoImgPath));
            sysConfService.updateAgencyConf(confInfos,"filepath");
            this.addResultInfo(SUCCESS,"更新路径配置成功！",null);
            this.responseResult(response,this.getResultJSONStr());
        }catch (Exception e){
            e.printStackTrace();
            this.addResultInfo(FAILURE,"更新路径配置失败！",null);
            this.responseResult(response,this.getResultJSONStr());
        }

    }

    @RequestMapping(value = "updateShieldPayConf")
    public void updateShieldPayConf(HttpServletRequest request,HttpServletResponse response){
        String shieldAppId = request.getParameter("shieldAppId");
        String shieldKey = request.getParameter("shieldKey");
        String payType = request.getParameter("payType");
        String shieldUrl = request.getParameter("shieldUrl");
        String cloudUrl = request.getParameter("cloudUrl");
        String cloudName = request.getParameter("cloudName");
        String cloudAppId = request.getParameter("cloudAppId");
        String cloudKey = request.getParameter("cloudKey");
        try {
            List<ConfInfo> confInfos = new ArrayList<>();
            confInfos.add(new ConfInfo(new RandomGUID().getUUID32(),"shieldPay","shieldAppId","三方支付appid",shieldAppId));
            confInfos.add(new ConfInfo(new RandomGUID().getUUID32(),"shieldPay","shieldKey","三方支付key",shieldKey));
            confInfos.add(new ConfInfo(new RandomGUID().getUUID32(),"shieldPay","payType","支付方式",payType));
            confInfos.add(new ConfInfo(new RandomGUID().getUUID32(),"shieldPay","shieldUrl","支付地址",shieldUrl));
            confInfos.add(new ConfInfo(new RandomGUID().getUUID32(),"shieldPay","cloudUrl","接口地址",cloudUrl));
            confInfos.add(new ConfInfo(new RandomGUID().getUUID32(),"shieldPay","cloudName","商户名称",cloudName));
            confInfos.add(new ConfInfo(new RandomGUID().getUUID32(),"shieldPay","cloudAppId","商户号",cloudAppId));
            confInfos.add(new ConfInfo(new RandomGUID().getUUID32(),"shieldPay","cloudKey","商户密钥",cloudKey));
            sysConfService.updateAgencyConf(confInfos,"shieldPay");
            this.addResultInfo(SUCCESS,"更新支付配置成功！",null);
            this.responseResult(response,this.getResultJSONStr());
        }catch (Exception e){
            e.printStackTrace();
            this.addResultInfo(FAILURE,"更新支付配置失败！",null);
            this.responseResult(response,this.getResultJSONStr());
        }

    }
    @RequestMapping(value = "getConfByGroupName")
    public void getConfByGroupName(HttpServletRequest request,HttpServletResponse response){
        String groupName = request.getParameter("groupName");

        try {
            Map<String,String> result = sysConfService.getConfByGroupName(groupName);
            this.addResultInfo(SUCCESS,"获取代理配置成功！",result);
            this.responseResult(response,this.getResultJSONStr());
        }catch (Exception e){
            e.printStackTrace();
            this.addResultInfo(FAILURE,"获取代理配置失败！",null);
            this.responseResult(response,this.getResultJSONStr());
        }

    }

    @RequestMapping(value = "getConfByName")
    public void getConfByName(HttpServletRequest request,HttpServletResponse response){
        String name = request.getParameter("name");

        try {
            ConfInfo result = sysConfService.getConfByName(name);
            this.addResultInfo(SUCCESS,"获取配置成功！",result.getValue());
            this.responseResult(response,this.getResultJSONStr());
        }catch (Exception e){
            e.printStackTrace();
            this.addResultInfo(FAILURE,"获取配置失败！",null);
            this.responseResult(response,this.getResultJSONStr());
        }

    }
}
