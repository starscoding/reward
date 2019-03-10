package com.smile.azxx.web.cloudpay;

import com.google.gson.Gson;
import com.smile.azxx.entity.LinkInfo;
import com.smile.azxx.entity.RewardInfo;
import com.smile.azxx.service.LinkMng.LinkMngService;
import com.smile.azxx.service.orderinfo.OrderService;
import com.smile.azxx.service.sysconfig.SysConfService;
import com.smile.azxx.util.HttpClientUtil;
import com.smile.azxx.web.common.BaseController;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Smile on 2018/6/12.
 */
@Controller
@RequestMapping(value = "cloudPay")
public class CloudPayController extends BaseController{

    @Autowired
    private SysConfService sysConfService;

    @Autowired
    private LinkMngService linkMngService;

    @Autowired
    private OrderService orderService;

    @RequestMapping(value = "toPay")
    private void toPay(HttpServletRequest request, HttpServletResponse response){
        String linkId = request.getParameter("linkId");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String domain = sysConfService.getLDDomain("0");
        Map<String,String> payConf = sysConfService.getConfByGroupName("shieldPay");
        LinkInfo linkInfo = linkMngService.getLinkInfo(linkId);
        if(linkInfo==null){
            log.info("创建订单失败！失败原因：链接不存在，链接ID:"+linkId);
            this.addResultInfo(FAILURE,"创建订单失败！",null);
            this.responseResult(response,this.getResultJSONStr());
        }
        String tradeType = "wechat_app";//APP 内填 sdk（App 直接接入支付） 微信外填 wap2 微信内填 weixinwap
        String merId = payConf.get("cloudAppId");//商户编号
        String key = payConf.get("cloudKey");//商户密钥
        String preorderApi=payConf.get("cloudUrl");//商户URL
        String orderId = merId+ System.currentTimeMillis()/1000+(int)((Math.random()*9+1)*1000);
        String notify = "http://"+domain+"/reward/cloudPay/payNotify";
        String totalMoney = linkInfo.getVideoPrice().multiply(new BigDecimal("100")).intValue()+"";
        String redirectUrl = "http://"+domain+"/reward/cloudPay/checkpaystatus/"+orderId;

        SortedMap<String,String> params = new TreeMap<>();
        params.put("merId",merId);
        params.put("orderId",orderId);
        params.put("totalMoney",totalMoney);
        params.put("tradeType",tradeType);
        log.info(merId+"_"+ System.currentTimeMillis()/1000+(int)(Math.random()*9+1)*1000);
        try {
            params.put("sign",this.generateSignatureKeyValue(params,key,"=").toUpperCase());
            params.put("version", "1.0");
            params.put("describe","vip");
            params.put("notify",notify);
            params.put("redirectUrl",redirectUrl);
            params.put("remark",linkId);
            params.put("fromtype","wap2");
            params.put("ip",this.getIpAddr(request));

            Gson gson = new Gson();
            String uparams = gson.toJson(params);
//            for (Map.Entry<String, String> entry : params.entrySet()) {
//                uparams +=entry.getKey()+"="+entry.getValue()+"&";
//            }
//            uparams = uparams.substring(0,uparams.lastIndexOf("&"));
            log.info("URL参数："+uparams);
            log.info("url : "+preorderApi);
            String result = HttpClientUtil.doPostJson(preorderApi,uparams);
            if(StringUtils.isBlank(result)){
                this.addResultInfo(FAILURE,"下单失败！",null);
                this.responseResult(response,this.getResultJSONStr());
                return;
            }
            log.info(result);
            Map<String,String> map = new HashMap<>();
            map = gson.fromJson(result,HashMap.class);
            if("0.0".equals(String.valueOf(map.get("code")))){
//            if(map.get("code")==0){
                log.info("下单成功!");

                String oJson = gson.toJson(map.get("object"));
                Map<String,String> oMap = gson.fromJson(oJson,LinkedHashMap.class);
                log.info("返回地址："+oMap.get("wxPayWay"));
//                return "redirect:"+oMap.get("wxPayWay");
                this.addResultInfo(SUCCESS,"下单成功！",oMap.get("wxPayWay"));
                this.responseResult(response,this.getResultJSONStr());
            }else{
                log.info("下单失败!");
                log.info("错误信息："+map.get("errMsg"));
                this.addResultInfo(FAILURE,"下单失败！",map);
                this.responseResult(response,this.getResultJSONStr());
            }
        } catch (Exception e) {
            e.printStackTrace();
            this.addResultInfo(FAILURE,"下单失败！",null);
            this.responseResult(response,this.getResultJSONStr());
        }
    }

    @RequestMapping("/checkpaystatus/{orderId}")
    public String  checkpaystatus(@PathVariable String orderId,HttpServletRequest request, HttpServletResponse response) {
        log.info("重定向URL:"+"redirect:/shieldpay/checkStatus.jsp?ordersn="+orderId);
        return "redirect:/shieldpay/checkStatus.jsp?ordersn="+orderId;
    }

    @RequestMapping(value = "payNotify" ,method = RequestMethod.GET)
    @ResponseBody
    public String payNotify(HttpServletRequest request, HttpServletResponse response) {
        log.info("支付成功回调......");
        String code = request.getParameter("code");//状态
        String merId = request.getParameter("merId");//商户编号
        String orderId = request.getParameter("orderId");//商户订单号
        String tradeId = request.getParameter("tradeId");//平台订单号
        String payWay = request.getParameter("payWay");//支付类型 1 微信 2 支付宝
        String money = request.getParameter("money");
        String remark = request.getParameter("remark");
        String time = request.getParameter("time");
        String sign = request.getParameter("sign");
        SortedMap<String, String> signMap = new TreeMap<>();
        String result = "";
        try {
            Map<String,String> payConf = sysConfService.getConfByGroupName("shieldPay");
            signMap.put("code",code);
            signMap.put("merId",merId);
            signMap.put("money",money);
            signMap.put("orderId",orderId);
            signMap.put("payWay",payWay);
            signMap.put("remark",remark);
            signMap.put("time",time);
            signMap.put("tradeId",tradeId);
            String sign2 = this.generateSignatureNOSplit(signMap,payConf.get("cloudKey")).toUpperCase();
            log.info(sign+","+sign2);
            if(sign.equals(sign2)){
                result = "success";
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                this.saveOrder(orderId,remark,sdf.format(new Date()),money,this.getIpAddr(request));
            }else{
                result = "invalid sign";
            }
            log.info(result);
        }catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public void saveOrder(String orderNo,String linkId,String payTime,String amount,String ip){

        if(StringUtils.isNotBlank(linkId)&&linkId.length()>32){
            linkId = linkId.substring(linkId.length()-32,linkId.length());
        }
        log.info("保存订单记录......linkId"+linkId);
        if(linkId.length()<32){
            log.info("linkId:"+linkId+",保存失败，linkId错误！");
            return;
        }

        LinkInfo linkInfo = linkMngService.getLinkInfo(linkId);
        if(linkInfo==null){
            log.info("保存失败，未在库中查找到相关链接！");
            return;
        }

        RewardInfo reward = orderService.getOrderById(linkId);
        if(reward!=null) {
            log.info("保存失败，订单记录已存在！ ");
            return;
        }
        RewardInfo r = new RewardInfo();
        r.setId(orderNo);
        r.setRewardTime(payTime);
        r.setLinkId(linkId);
        r.setIpAddress(ip);
        r.setName(linkInfo.getUserName());
        r.setAmount(new BigDecimal(amount).divide(new BigDecimal("100")));
        r.setVideoId(linkInfo.getVideoId());
        orderService.add(r);
    }

}
