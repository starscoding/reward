package com.smile.azxx.web.wxpay;

import com.google.gson.Gson;
import com.smile.azxx.entity.*;
import com.smile.azxx.service.LinkMng.LinkMngService;
import com.smile.azxx.service.orderinfo.OrderService;
import com.smile.azxx.service.sysconfig.PayWechatService;
import com.smile.azxx.service.sysconfig.SysConfService;
import com.smile.azxx.util.HttpClientUtil;
import com.smile.azxx.util.RandomGUID;
import com.smile.azxx.web.common.BaseController;
import com.smile.azxx.wxpay.WXPay;
import com.smile.azxx.wxpay.WXPayConfig;
import com.smile.azxx.wxpay.WXPayUtil;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by smile on 2018/5/7.
 */
@Controller
@RequestMapping(value = "wxpay")
public class WxPayController extends BaseController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private SysConfService sysConfService;

    @Autowired
    private LinkMngService linkMngService;

    @Autowired
    private PayWechatService payWechatService;

    @RequestMapping(value = "isReward")
    public void isReward(HttpServletRequest request, HttpServletResponse response, Model model) {
        String linkId = request.getParameter("linkId");
        String resultUrl = "";
        try {
            //linkId为空不做处理
            if (StringUtils.isBlank(linkId)) {
                this.addResultInfo(FAILURE, "缺少必要的参数！", resultUrl);
                this.responseResult(response, this.getResultJSONStr());
            } else {
                StringBuffer page = new StringBuffer();

                //是否开启维护
                ConfInfo maintainflag = sysConfService.getConfByName("maintainflag");
                log.info("是否开启维护：" + maintainflag.getValue());
                String ldDomain = sysConfService.getLDDomain("0");
                log.info("落地域名：" + ldDomain);
                //开启维护则跳转维护界面
                if (StringUtils.isNotBlank(maintainflag.getValue()) && maintainflag.getValue().equals("0")) {
                    page.append(ldDomain + "/reward/shieldpay/maintain.jsp");
                    resultUrl = "http://" + page.toString();
                    this.addResultInfo(SUCCESS, "系统维护中", resultUrl);
                    this.responseResult(response, this.getResultJSONStr());
                    return;
                }

                String ip = this.getIpAddr(request);
                log.info("请求IP：" + ip);
                //是否已打赏，若已打赏则直接跳转视频
                boolean hasRecord = orderService.isReward(linkId, ip);
                log.info("是否打赏：" + hasRecord);
                if (hasRecord) {
                    page.append(ldDomain + "/reward/wxpay/video.jsp?");
                    page.append("linkId=" + linkId);
                    resultUrl = "http://" + page.toString();
                    this.addResultInfo(SUCCESS, "已打赏！", resultUrl);
                    this.responseResult(response, this.getResultJSONStr());
                    return;
                }

                //支付类型 weixin,shieldpay
                ConfInfo payType = sysConfService.getConfByName("payType");
                log.info("支付类型：" + payType.getValue());
                if ("weixin".equals(payType.getValue())) {
                    page.append(ldDomain + "/reward/wxpay/order.jsp?");
                    page.append("&linkId=" + linkId);
                    resultUrl = "http://" + page.toString();
                } else {
                    page.append(ldDomain + "/reward/shieldpay/shieldpayOrder.jsp?");
                    page.append("&linkId=" + linkId);
                    resultUrl = "http://" + page.toString();
                }
                this.addResultInfo(SUCCESS, "", resultUrl);
                this.responseResult(response, this.getResultJSONStr());
            }

        } catch (Exception e) {
            e.printStackTrace();
            this.addResultInfo(FAILURE, "", resultUrl);
            this.responseResult(response, this.getResultJSONStr());
        }

    }

    @RequestMapping(value = "saveOrder")
    public void saveOrder(HttpServletRequest request, HttpServletResponse response) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String linkId = request.getParameter("linkId");
            String ip = this.getIpAddr(request);
            RewardInfo rewardInfo = new RewardInfo();
            LinkInfo linkInfo = linkMngService.getLinkInfo(linkId);
            rewardInfo.setId(new RandomGUID().getUUID32());
            rewardInfo.setName(linkInfo.getUserName());
            rewardInfo.setAmount(linkInfo.getVideoPrice());
            rewardInfo.setIpAddress(ip);
            rewardInfo.setLinkId(linkId);
            rewardInfo.setRewardTime(sdf.format(new Date()));
            orderService.add(rewardInfo);
        } catch (Exception e) {
            e.printStackTrace();
            this.addResultInfo(FAILURE, "", null);
            this.responseResult(response, this.getResultJSONStr());
        }

    }

    @RequestMapping(value = "getVideoInfo")
    public void getVideoInfo(HttpServletRequest request, HttpServletResponse response) {
        String linkId = request.getParameter("linkId");
        try {
            Map<String,String> result = new HashMap<>();
            List<VideoInfo> video = orderService.getVideoByLinkId(linkId);
            if(CollectionUtils.isNotEmpty(video)){
                log.info("关联到视频："+video.get(0).getId());
                result.put("filesize",video.get(0).getFileSize());
                result.put("timelength",video.get(0).getTimeLength());
                result.put("fileSize",video.get(0).getFileSize());
                result.put("timeLength",video.get(0).getTimeLength());
                result.put("id",video.get(0).getId());
            }
            LinkInfo linkInfo = linkMngService.getLinkInfo(linkId);
            if(linkInfo!=null){
                log.info("该视频链接价格为："+linkInfo.getVideoPrice()+"（"+linkId+"）");
                result.put("price",linkInfo.getVideoPrice()+"");
            }
            if (CollectionUtils.isNotEmpty(video)) {
                this.addResultInfo(SUCCESS, "", result);
                this.responseResult(response, this.getResultJSONStr());
            } else {
                this.addResultInfo(FAILURE, "未获取到视频信息！", null);
                this.responseResult(response, this.getResultJSONStr());
            }
        } catch (Exception e) {
            e.printStackTrace();
            this.addResultInfo(FAILURE, "", null);
            this.responseResult(response, this.getResultJSONStr());
        }
    }

    @RequestMapping(value = "/topay")
    public void pay(HttpServletRequest request, HttpServletResponse response) {
        //获取信息
        String orderId = String.valueOf(WXPayUtil.generateUUID());
        String code = request.getParameter("code");
        String safeDomain = request.getParameter("safeDomain");
        String linkId = request.getParameter("linkId");
        String appId = request.getParameter("appId");

        LinkInfo linkInfo = linkMngService.getLinkInfo(linkId);
        String notifyUrl = safeDomain + "/reward/wxpay/payNotify";

        WechatpayInfo wxPayInfo = payWechatService.getPayConf(appId);

        SortedMap<String, String> requestMap = new TreeMap<String, String>();
        requestMap.put("out_trade_no", orderId);
        requestMap.put("total_fee", linkInfo.getVideoPrice().multiply(new BigDecimal("100")).intValue() + "");
        requestMap.put("spbill_create_ip", getIpAddr(request));
//        requestMap.put("body","好兄弟建材店-建材");
        requestMap.put("body", wxPayInfo.getComment());
//        requestMap.put("detail","test");
        requestMap.put("device_info", "WEB");//设备号
//        requestMap.put("attach","test");//附加数据 通知中原样返回
        requestMap.put("trade_type", "JSAPI");//JSAPI 公众号支付 NATIVE 扫码支付 APP APP支付
        requestMap.put("openid", this.getOpenId(code, wxPayInfo.getAppid(), wxPayInfo.getAppsecret()));
//        requestMap.put("nonce_str",WXPayUtil.generateNonceStr());
        requestMap.put("notify_url", notifyUrl);
        requestMap.put("attach", linkId);

        log.info("请求Map1");
        this.printMap(requestMap);

        try {
            WXPayConfig conf = WXPayConfigImpl.getInstance();
            conf.setAppId(appId);
            conf.setMchID(wxPayInfo.getBizAccount());
            conf.setKey(wxPayInfo.getKey());
            WXPay wxPay = new WXPay(conf, notifyUrl, false, false);
            Map<String, String> resp = wxPay.unifiedOrder(requestMap);

            log.info("请求Map2");
            this.printMap(resp);

            SortedMap<String, String> result = new TreeMap<String, String>();
            result.put("appId", resp.get("appid"));
            result.put("timeStamp", System.currentTimeMillis() / 1000 + "");
            result.put("nonceStr", WXPayUtil.generateNonceStr());
            result.put("package", "prepay_id=" + resp.get("prepay_id"));
            result.put("signType", "MD5");
            result.put("paySign", WXPayUtil.generateSignature(result, wxPayInfo.getKey()));
            result.put("orderId", orderId);


            log.info("返回Map");
            this.printMap(result);

            this.addResultInfo(SUCCESS, "下单成功！", result);
//            this.responseResult(response,callback + "(" + getResultJSONStr() + ")");
            this.responseResult(response, getResultJSONStr());
        } catch (Exception e) {
            e.printStackTrace();
//            this.addResultInfo(FAILURE,"下单失败！",callback + "()");
            this.addResultInfo(FAILURE, "下单失败！", null);
            this.responseResult(response, this.getResultJSONStr());
        }
    }


    public String getOpenId(String code, String appId, String secret) {
//        String appid = "wx20d21ee2da2cce0d";
//        String secret = "f6acbf3acfdccf60ddca71f0f7a97795";
        String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=" + appId + "&secret=" + secret + "&code=" + code + "&grant_type=authorization_code";
        String rst = HttpClientUtil.doGet(url);
        log.info(url);
        log.info(rst);
        Gson gson = new Gson();
        Map<String, String> map = gson.fromJson(rst, HashMap.class);
        return map.get("openid");
    }

    public void printMap(Map<String, String> map) {
        Iterator ritt = map.entrySet().iterator();
//        log.info(getOpenId(code));
        while (ritt.hasNext()) {
            Map.Entry entry = (Map.Entry) ritt.next();
            Object key = entry.getKey();
            Object value = entry.getValue();
            log.info("key=" + key + " value=" + value);
        }
    }

    @RequestMapping(value = "payNotify")
    @ResponseBody
    public String payNotify(HttpServletRequest request, HttpServletResponse response) {
        log.info(">>>>>>>>>>>>>进入微信接口通知 ");
        InputStream instream = null;
        StringBuffer sb = new StringBuffer();
        Map<String, String> return_data = new HashMap<String, String>();
        try {
            instream = request.getInputStream();

            int len = -1;
            byte[] buffer = new byte[1024];
            while ((len = instream.read(buffer)) != -1) {
                sb.append(new String(buffer, 0, len));
            }
            instream.close();

            Map<String, String> notifyMap = WXPayUtil.xmlToMap(sb.toString());//将微信发的xml转map
            this.printMap(notifyMap);
            String appid = notifyMap.get("appid");//应用ID
            String attach = notifyMap.get("attach");//商家数据包
            String bank_type = notifyMap.get("bank_type");//付款银行
            String cash_fee = notifyMap.get("cash_fee");//现金支付金额
            String fee_type = notifyMap.get("fee_type");//货币种类
            String is_subscribe = notifyMap.get("is_subscribe");//是否关注公众账号
            String mch_id = notifyMap.get("mch_id");//商户号
            String nonce_str = notifyMap.get("nonce_str");//随机字符串
            String openid = notifyMap.get("openid");//用户标识
            String out_trade_no = notifyMap.get("out_trade_no");// 获取商户订单号
            String result_code = notifyMap.get("result_code");// 业务结果
            String return_code = notifyMap.get("return_code");// SUCCESS/FAIL
            String sign = notifyMap.get("sign");// 获取签名
            String time_end = notifyMap.get("time_end");//支付完成时间
            String total_fee = notifyMap.get("total_fee");// 获取订单金额
            String trade_type = notifyMap.get("trade_type");//交易类型
            String device_info = notifyMap.get("device_info");//交易类型
            String transaction_id = notifyMap.get("transaction_id");//微信支付订单号

            //校验密钥
            SortedMap<String, String> checkMap = new TreeMap<>();
            checkMap.put("appid", appid);//应用ID
            checkMap.put("attach", attach);//商家数据包
            checkMap.put("bank_type", bank_type);//付款银行
            checkMap.put("cash_fee", cash_fee);//现金支付金额
            checkMap.put("fee_type", fee_type);//货币种类
            checkMap.put("is_subscribe", is_subscribe);//是否关注公众账号
            checkMap.put("mch_id", mch_id);//商户号
            checkMap.put("nonce_str", nonce_str);//随机字符串
            checkMap.put("openid", openid);//用户标识
            checkMap.put("out_trade_no", out_trade_no);// 商户订单号
            checkMap.put("result_code", result_code);// 业务结果
            checkMap.put("return_code", return_code);// SUCCESS/FAIL
            checkMap.put("time_end", time_end);// 支付完成时间
            checkMap.put("total_fee", total_fee);// 获取订单金额
            checkMap.put("trade_type", trade_type);//交易类型
            checkMap.put("device_info", device_info);//交易类型
            checkMap.put("transaction_id", transaction_id);//微信支付订单号

            WechatpayInfo wxPayInfo = payWechatService.getPayConf(appid);

            String endSign = WXPayUtil.generateSignature(checkMap, wxPayInfo.getKey());
            log.info(sign + "," + endSign + "," + wxPayInfo.getKey());
            if (sign.equals(endSign)) {
                //查看通知是否成功
                if (notifyMap.get("return_code").toString().equals("FAIL")) {
                    return_data.put("return_code", "FAIL");
                    return_data.put("return_msg", notifyMap.get("return_msg"));
                } else if (notifyMap.get("return_code").toString().equals("SUCCESS")) {
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    this.saveOrder(out_trade_no, attach, sdf.format(new Date()), total_fee, this.getIpAddr(request));
                    return_data.put("return_code", "SUCCESS");
                    return_data.put("return_msg", "OK");
                }
            } else {
                return_data.put("return_code", "FAIL");
                return_data.put("return_msg", notifyMap.get("参数格式校验错误"));
            }


        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        String rs = "";
        try {
            rs = WXPayUtil.mapToXml(return_data);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    public void saveOrder(String orderNo, String linkId, String payTime, String amount, String ip) {

        if (StringUtils.isNotBlank(linkId) && linkId.length() > 32) {
            linkId = linkId.substring(linkId.length() - 32, linkId.length());
        }
        log.info("保存订单记录......linkId " + linkId);
        if (linkId.length() < 32) {
            log.info("linkId:" + linkId + ",保存失败，linkId错误！");
            return;
        }
        LinkInfo linkInfo = linkMngService.getLinkInfo(linkId);
        if (linkInfo == null) {
            log.info("保存失败，未在库中查找到相关链接！");
            return;
        }
        RewardInfo reward = orderService.getOrderById(linkId);
        if (reward != null) {
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
//        r.set

    }

    @RequestMapping(value = "sendVideo")
    private void sendVideo(HttpServletRequest request, HttpServletResponse response) throws FileNotFoundException, IOException {
        String fileName = request.getParameter("fileName");
        ConfInfo videoPath = sysConfService.getConfByName("videoPath");
        File file = new File(videoPath.getValue() + fileName);
        RandomAccessFile randomFile = new RandomAccessFile(file, "r");//只读模式
        long contentLength = randomFile.length();
        String range = request.getHeader("Range");
        int start = 0, end = 0;
        if (range != null && range.startsWith("bytes=")) {
            String[] values = range.split("=")[1].split("-");
            start = Integer.parseInt(values[0]);
            if (values.length > 1) {
                end = Integer.parseInt(values[1]);
            }
        }
        int requestSize = 0;
        if (end != 0 && end > start) {
            requestSize = end - start + 1;
        } else {
            requestSize = Integer.MAX_VALUE;
        }

        byte[] buffer = new byte[4096];
        response.setContentType("video/mp4");
        response.setHeader("Accept-Ranges", "bytes");
        response.setHeader("ETag", fileName);
        response.setHeader("Last-Modified", new Date().toString());
        //第一次请求只返回content length来让客户端请求多次实际数据
        if (range == null) {
            response.setHeader("Content-length", contentLength + "");
        } else {
            //以后的多次以断点续传的方式来返回视频数据
            response.setStatus(HttpServletResponse.SC_PARTIAL_CONTENT);//206
            long requestStart = 0, requestEnd = 0;
            String[] ranges = range.split("=");
            if (ranges.length > 1) {
                String[] rangeDatas = ranges[1].split("-");
                requestStart = Integer.parseInt(rangeDatas[0]);
                if (rangeDatas.length > 1) {
                    requestEnd = Integer.parseInt(rangeDatas[1]);
                }
            }
            long length = 0;
            if (requestEnd > 0) {
                length = requestEnd - requestStart + 1;
                response.setHeader("Content-length", "" + length);
                response.setHeader("Content-Range", "bytes " + requestStart + "-" + requestEnd + "/" + contentLength);
            } else {
                length = contentLength - requestStart;
                response.setHeader("Content-length", "" + length);
                response.setHeader("Content-Range", "bytes " + requestStart + "-" + (contentLength - 1) + "/" + contentLength);
            }
        }
        ServletOutputStream out = response.getOutputStream();
        int needSize = requestSize;
        randomFile.seek(start);
        while (needSize > 0) {
            int len = randomFile.read(buffer);
            if (needSize < buffer.length) {
                out.write(buffer, 0, needSize);
            } else {
                out.write(buffer, 0, len);
                if (len < buffer.length) {
                    break;
                }
            }
            needSize -= buffer.length;
        }
        randomFile.close();
        out.close();

    }
}
