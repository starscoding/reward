package com.smile.azxx.web.shieldpay;

import com.google.gson.Gson;
import com.smile.azxx.entity.ConfInfo;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * Created by smile on 2018/5/15.
 */
@Controller
@RequestMapping("shieldPay")
public class ShieldPayController extends BaseController{

    @Autowired
    private SysConfService sysConfService;

    @Autowired
    private LinkMngService linkMngService;

    @Autowired
    private OrderService orderService;

    @RequestMapping(value = "getPayWay")
    public void getPayWay(HttpServletRequest request, HttpServletResponse response) {

        try {
            ConfInfo payType = sysConfService.getConfByName("payType");
            String method = "";
            if(payType!=null&&"shield".equals(payType.getValue())){
                method = "/shieldPay/toPay";
            }else if(payType!=null&&"cloud".equals(payType.getValue())){
                method = "/cloudPay/toPay";
            }
            this.addResultInfo(SUCCESS,"成功！",method);
            this.responseResult(response,this.getResultJSONStr());

        }catch (Exception e) {
            e.printStackTrace();
            this.addResultInfo(FAILURE,"未查到支付信息！",null);
            this.responseResult(response,this.getResultJSONStr());
        }
    }

    @RequestMapping(value = "toPay")
    private void toPay(HttpServletRequest request, HttpServletResponse response){
        String linkId = request.getParameter("linkId");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

        String payway = "weixin";
        String paytype = "wap";//h5, wap , android , ios

//        ConfInfo domain = sysConfService.getConfByName("ldDomain");
        String domain = sysConfService.getLDDomain("0");
        Map<String,String> payConf = sysConfService.getConfByGroupName("shieldPay");
        LinkInfo linkInfo = linkMngService.getLinkInfo(linkId);
        if(linkInfo==null){
            log.info("创建订单失败！失败原因：链接不存在，链接ID:"+linkId);
            this.addResultInfo(FAILURE,"创建订单失败！",null);
            this.responseResult(response,this.getResultJSONStr());
        }
//        String appid = "20309";
        String appid = payConf.get("shieldAppId");
        String appkey = payConf.get("shieldKey");
        String preorderApi=payConf.get("shieldUrl");
        String ordersn = appid+"_"+ System.currentTimeMillis()/1000+(int)((Math.random()*9+1)*1000);
//        String notifyurl = "http://"+domain.getValue()+"/reward/wxpay/saveOrder";
        String notifyurl = "http://"+domain+"/reward/shieldPay/payNotify";
        String amount = linkInfo.getVideoPrice().multiply(new BigDecimal("100")).intValue()+"";
//        String appkey = "e4a1fbe1398e0f684ee7a15a9609030a";
        SortedMap<String,String> params = new TreeMap<>();
        params.put("appid",appid);
//        params.put("amount",amount);
        params.put("amount",amount);
        params.put("itemname","VIP");
        params.put("notifyurl",notifyurl);
        params.put("ordersn",ordersn);
        params.put("orderdesc","VIP");
        log.info(appid+"_"+ System.currentTimeMillis()/1000+(int)(Math.random()*9+1)*1000);
        try {
            params.put("sign",this.generateSignature(params,appkey,"|"));

            params.put("returnurl",URLEncoder.encode("http://"+domain+"/reward/shieldpay/checkStatus.jsp?ordersn="+ordersn));
            params.put("payway",payway);
            params.put("ext",linkId);
            params.put("paytype",paytype);

            String uparams = "";
            for (Map.Entry<String, String> entry : params.entrySet()) {
                uparams +=entry.getKey()+"="+entry.getValue()+"&";
            }
            uparams = uparams.substring(0,uparams.lastIndexOf("&"));
            log.info("URL参数："+uparams);
            String url = preorderApi+"?"+ uparams;
            log.info(url);
            String result = HttpClientUtil.doGet(url);
            if(StringUtils.isBlank(result)){
                this.addResultInfo(FAILURE,"下单失败！",null);
                this.responseResult(response,this.getResultJSONStr());
                return;
            }
            log.info(result);
            Map<String,String> map = new HashMap<>();
            Gson gson = new Gson();
            map = gson.fromJson(result,HashMap.class);
            if("1".equals(map.get("status"))){
                log.info("下单成功!");
                log.info("返回地址："+map.get("data"));
                this.addResultInfo(SUCCESS,"下单成功！",map);
                this.responseResult(response,this.getResultJSONStr());
            }else{
                log.info("下单失败!");
                log.info("错误信息："+map.get("msg"));
                this.addResultInfo(FAILURE,"下单失败！",map);
                this.responseResult(response,this.getResultJSONStr());
            }
        } catch (Exception e) {
            e.printStackTrace();
            this.addResultInfo(FAILURE,"下单失败！",null);
            this.responseResult(response,this.getResultJSONStr());
        }


    }

//    public String generateSignature(Map<String, String> data, String key) throws Exception {
//        Set<String> keySet = data.keySet();
//        String[] keyArray = keySet.toArray(new String[keySet.size()]);
//        Arrays.sort(keyArray);
//        StringBuilder sb = new StringBuilder();
//        StringBuffer keySort = new StringBuffer();
//        for (String k : keyArray) {
//            if (k.equals(WXPayConstants.FIELD_SIGN)) {
//                continue;
//            }
//            keySort.append(k+"|");
//            if (StringUtils.isNotBlank(data.get(k))&& data.get(k).trim().length() > 0) // 参数值为空，则不参与签名
//                sb.append(data.get(k).trim()).append("|");
//
////                sb.append(k).append("=").append(data.get(k).trim()).append("&");
//        }
////        sb.append("key=").append(key);
//        sb.append(key);
//        log.info("asci 排序:"+keySort.toString()+"appkey");
//        log.info("加密字符串："+sb.toString());
//        log.info("sign : "+this.MD5(sb.toString()));
//        return this.MD5(sb.toString());
//    }
//    public String MD5(String data) throws Exception {
//        MessageDigest md = MessageDigest.getInstance("MD5");
//        byte[] array = md.digest(data.getBytes("UTF-8"));
//        StringBuilder sb = new StringBuilder();
//        for (byte item : array) {
//            sb.append(Integer.toHexString((item & 0xFF) | 0x100).substring(1, 3));
//        }
//        return sb.toString();
//    }

    @RequestMapping(value = "payNotify")
    @ResponseBody
    public String payNotify(HttpServletRequest request, HttpServletResponse response) {
        log.info("支付成功回调......");
        String appid = request.getParameter("appid");
        String amount = request.getParameter("amount");
        String itemname = request.getParameter("itemname");
        String ordersn = request.getParameter("ordersn");
        String orderdesc = request.getParameter("orderdesc");
        String serialno = request.getParameter("serialno");
        String sign = request.getParameter("sign");
        String ext = request.getParameter("ext");
        String paytime = request.getParameter("paytime");
        String linkId = ext;
        SortedMap<String, String> signMap = new TreeMap<>();
        String result = "";
        try {
            Map<String,String> payConf = sysConfService.getConfByGroupName("shieldPay");
            signMap.put("appid",appid);
            signMap.put("amount",amount);
            signMap.put("itemname",itemname);
            signMap.put("ordersn",ordersn);
            signMap.put("serialno",serialno);
            signMap.put("orderdesc",orderdesc);
            String sign2 = this.generateSignature(signMap,payConf.get("shieldKey"),"|");
            log.info(sign+","+sign2);
            if(sign.equals(sign2)){
                result = "success";
                this.saveOrder(ordersn,linkId,paytime,amount,this.getIpAddr(request));
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
            log.info("保存失败，订单记录已存在！");
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

    @RequestMapping(value = "hasPay")
    public void hasPay(HttpServletRequest request, HttpServletResponse response) {
        String id = request.getParameter("id");
        try {
           RewardInfo result = orderService.getOrderById(id);
           if(result!=null){
               //若支付成功，更新请求的IP
               result.setIpAddress(this.getIpAddr(request));
               log.info("更新订单信息！");
               orderService.updateOrderInfo(result);
               this.addResultInfo(SUCCESS,"支付成功！",result.getLinkId());
               this.responseResult(response,this.getResultJSONStr());
           }else{
               this.addResultInfo(FAILURE,"未查到支付信息！",null);
               this.responseResult(response,this.getResultJSONStr());
           }

        }catch (Exception e) {
            e.printStackTrace();
            this.addResultInfo(FAILURE,"未查到支付信息！",null);
            this.responseResult(response,this.getResultJSONStr());
        }
    }

    public static void main(String[] args) {
        ShieldPayController controller = new ShieldPayController();
        controller.toPay(null,null);
    }
}

