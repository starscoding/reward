package com.smile.azxx.web.common;

import com.smile.azxx.entity.sysmng.User;
import com.smile.azxx.service.common.BaseService;
import com.smile.azxx.wxpay.WXPayConstants;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.security.MessageDigest;
import java.util.*;
import java.util.regex.Pattern;

/**
 * Created by smile on 2018/4/6.
 */
public class BaseController {

    protected Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    @Qualifier("baseService")
    private BaseService baseService;

    private static ResourceBundle rb;
    public static final String SUCCESS = "success";
    public static final String FAILURE = "failure";
    // 定义一个用来包装返回消息的map变量
    private Map<String, Object> result;

    /**
     * 获得当前用户IP
     *
     * @return
     */
    protected String getCurrentUserIP(HttpServletRequest request) {

        // 取用户IP
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        if ("127.0.0.1".equals(ip) || "0:0:0:0:0:0:0:1".equals(ip)) {
            try {
                ip = InetAddress.getLocalHost().getHostAddress();
            } catch (UnknownHostException unknownhostexception) {
            }
        }
        return ip;
    }

    /**
     * 获得服务IP
     *
     * @return
     */
    protected String getApp_IP() {
        // 取服务IP
        try {
            return InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            return null;
        }

    }

    public User getUser() {
        Subject subject = null;
        User user = null;
        try {
            subject = SecurityUtils.getSubject();
            if (subject != null) {
                try {
                    user = (User) subject.getPrincipal();
                } catch (Exception e) {// subject中获取不成功则从session中获取
                    try {
                        user = (User) subject.getSession().getAttribute(
                                "c_user");
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                }
            }
        } catch (Exception e) {
            log.info("获取当前登录用户失败，请检查是否已经正确登录，当前返回unkonwn用户。");
        }
        return user == null ? new User() : user;

    }

    /**
     * 获取当前系统语言
     *
     * @return
     */
    public String getI18NLocale() {
//        String locale = baseService.getCommonFieldValueByName("current_local", "value");
        String locale = null;
        if (StringUtils.isBlank(locale)) {
            locale = "zh_CN";// 默认简体中文
        }
        return locale;
    }

    /**
     * 获取当前国际化配置文件
     *
     * @return
     */
    protected ResourceBundle getCurrentRb() {
        String[] l = getI18NLocale().split("_");
        Locale locale = new Locale(l[0], l[1]);
        if (rb == null || !locale.equals(rb.getLocale())) {
            rb = ResourceBundle.getBundle("i18n.ApplicationBundle", locale);
        }
        return rb;
    }

    /**
     * 增加返回信息数据
     *
     * @param type error-失败，success-成功
     * @param msg  结果返回消息
     * @param data 返回数据
     */
    protected void addResultInfo(String type, String msg, Object data) {
        String rstType = BaseController.SUCCESS.equals(type) ? "true" : "false";

        this.getResult().put("success", rstType);
        this.getResult().put("msg", msg);
        this.getResult().put("data", data);
    }

    protected Map<String, Object> getResult() {
        if (this.result == null) {
            this.result = new HashMap<String, Object>();
        }
        return result;
    }

    protected String getResultJSONStr() {
        return JSONObject.fromObject(getResult()).toString();
    }

    protected String getResultJSONStr(Object data) {
        return JSONObject.fromObject(data).toString();
    }

    protected void setResult(Map<String, Object> result) {
        this.result = result;
    }

    protected void responseResult(HttpServletResponse response, String rslt) {
        try {
            PrintWriter out = response.getWriter();
            out.print(rslt);
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected void saveFile(InputStream inputStream, String path, String fileName) {
        try {
            FileOutputStream outputStream = new FileOutputStream(path + fileName);
            byte[] b = new byte[1024 * 1024];
            while (inputStream.read(b) != -1) {
                outputStream.write(b);
            }
            inputStream.close();
            outputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    protected boolean isInt(String str) {
        String pattern = "^[0-9]*[1-9][0-9]*$";
        boolean isMatch = Pattern.matches(pattern, str);
        return isMatch;
    }

    protected boolean isNotInt(String str) {
        String pattern = "^[0-9]*[1-9][0-9]*$";
        boolean isMatch = Pattern.matches(pattern, str);
        return !isMatch;
    }

    protected String getIpAddr(HttpServletRequest request) {
//        String ip = request.getHeader(" x-forwarded-for ");
//        log.info("All the IP address string is: " + ip);
//        if (ip == null || ip.length() == 0 || " unknown ".equalsIgnoreCase(ip)) {
//            ip = request.getHeader(" Proxy-Client-IP ");
//        }
//        if (ip == null || ip.length() == 0 || " unknown ".equalsIgnoreCase(ip)) {
//            ip = request.getHeader(" WL-Proxy-Client-IP ");
//        }
//        if (ip == null || ip.length() == 0 || " unknown ".equalsIgnoreCase(ip)) {
//            ip = request.getRemoteAddr();
//        }
//        return ip;
        String strClientIp = request.getHeader("x-forwarded-for");
        log.info("All the IP address string is: " + strClientIp);
        if (strClientIp == null || strClientIp.length() == 0 || "unknown".equalsIgnoreCase(strClientIp)) {
            strClientIp = request.getRemoteAddr();
        } else {
            String[] ipList = strClientIp.split(",");
            String strIp = new String();
            if (ArrayUtils.isNotEmpty(ipList)) {
                for (int index = 0; index < ipList.length; index++) {
                    strIp = ipList[index];
                    if (!("unknown".equalsIgnoreCase(strIp))) {
                        strClientIp = strIp;
                        break;
                    }
                }
            } else {
                strClientIp = "0:0:0:0";
            }
        }
        log.info(strClientIp);
        return strClientIp;
    }

    public String generateSignature(Map<String, String> data, String key, String split) throws Exception {
        Set<String> keySet = data.keySet();
        String[] keyArray = keySet.toArray(new String[keySet.size()]);
        Arrays.sort(keyArray);
        StringBuilder sb = new StringBuilder();
        StringBuffer keySort = new StringBuffer();
        for (String k : keyArray) {
            if (k.equals(WXPayConstants.FIELD_SIGN)) {
                continue;
            }
            keySort.append(k + split);
            if (StringUtils.isNotBlank(data.get(k)) && data.get(k).trim().length() > 0) // 参数值为空，则不参与签名
                sb.append(data.get(k).trim()).append("|");

//                sb.append(k).append("=").append(data.get(k).trim()).append("&");
        }
//        sb.append("key=").append(key);
        sb.append(key);
        log.info("asci 排序:" + keySort.toString() + "appkey");
        log.info("加密字符串：" + sb.toString());
        log.info("sign : " + this.MD5(sb.toString()));
        return this.MD5(sb.toString());
    }

    public String generateSignatureKeyValue(Map<String, String> data, String key, String split) throws Exception {
        Set<String> keySet = data.keySet();
        String[] keyArray = keySet.toArray(new String[keySet.size()]);
        Arrays.sort(keyArray);
        StringBuilder sb = new StringBuilder();
        StringBuffer keySort = new StringBuffer();
        for (String k : keyArray) {
            if (k.equals(WXPayConstants.FIELD_SIGN)) {
                continue;
            }
//            keySort.append(k + split);
            if (StringUtils.isNotBlank(data.get(k)) && data.get(k).trim().length() > 0) // 参数值为空，则不参与签名
//                sb.append(data.get(k).trim()).append("|");
                sb.append(k).append(split).append(data.get(k).trim()).append("&");
        }
//        sb.append("key=").append(key);
        sb.append(key);
//        log.info("asci 排序:" + keySort.toString() + "appkey");
        log.info("加密字符串：" + sb.toString());
        log.info("sign : " + this.MD5(sb.toString()));
        return this.MD5(sb.toString());
    }
    public String generateSignatureNOSplit(Map<String, String> data, String key) throws Exception {
        Set<String> keySet = data.keySet();
        String[] keyArray = keySet.toArray(new String[keySet.size()]);
        Arrays.sort(keyArray);
        StringBuilder sb = new StringBuilder();
        StringBuffer keySort = new StringBuffer();
        for (String k : keyArray) {
            if (k.equals(WXPayConstants.FIELD_SIGN)) {
                continue;
            }
//            keySort.append(k + split);
            if (StringUtils.isNotBlank(data.get(k)) && data.get(k).trim().length() > 0) // 参数值为空，则不参与签名
//                sb.append(data.get(k).trim()).append("|");
                sb.append(k).append(data.get(k).trim());
        }
//        sb.append("key=").append(key);
        sb.append(key);
//        log.info("asci 排序:" + keySort.toString() + "appkey");
        log.info("加密字符串：" + sb.toString());
        log.info("sign : " + this.MD5(sb.toString()));
        return this.MD5(sb.toString());
    }

    public String MD5(String data) throws Exception {
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] array = md.digest(data.getBytes("UTF-8"));
        StringBuilder sb = new StringBuilder();
        for (byte item : array) {
            sb.append(Integer.toHexString((item & 0xFF) | 0x100).substring(1, 3));
        }
        return sb.toString();
    }
}
