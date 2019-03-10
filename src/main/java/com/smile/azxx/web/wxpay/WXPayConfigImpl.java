package com.smile.azxx.web.wxpay;

import com.smile.azxx.wxpay.IWXPayDomain;
import com.smile.azxx.wxpay.WXPayConfig;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

public class WXPayConfigImpl extends WXPayConfig {

    private byte[] certData;
    private static WXPayConfigImpl INSTANCE;

    private String appId;
    private String mchId;
    private String key;

    private WXPayConfigImpl() throws Exception {
        //涉及资金回滚的接口会使用到商户证书123
//        String certPath = "D://CERT/common/apiclient_cert.p12";
//        File file = new File(certPath);
//        InputStream certStream = new FileInputStream(file);
//        this.certData = new byte[(int) file.length()];
//        certStream.read(this.certData);
//        certStream.close();
    }

    public static WXPayConfigImpl getInstance() throws Exception {
        if (INSTANCE == null) {
            synchronized (WXPayConfigImpl.class) {
                if (INSTANCE == null) {
                    INSTANCE = new WXPayConfigImpl();
                }
            }
        }
        return INSTANCE;
    }

//    public String getAppID() {
//        return "wx20d21ee2da2cce0d";
//    }
    public String getAppID() {
        return this.appId;
    }

    @Override
    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getMchID() {
        //1501830451
        return this.mchId;
    }

    @Override
    public void setMchID(String mchId) {
        this.mchId = mchId;
    }

    public String getKey() {
        //"2f43847b62764e19bd462249faa97cfa"
        return this.key;
    }

    @Override
    public void setKey(String key) {
        this.key = key;
    }


    public InputStream getCertStream() {
        ByteArrayInputStream certBis;
        certBis = new ByteArrayInputStream(this.certData);
        return certBis;
    }


    public int getHttpConnectTimeoutMs() {
        return 2000;
    }

    public int getHttpReadTimeoutMs() {
        return 10000;
    }

    public IWXPayDomain getWXPayDomain() {
        return WXPayDomainSimpleImpl.instance();
    }

    public String getPrimaryDomain() {
        return "api.mch.weixin.qq.com";
    }

    public String getAlternateDomain() {
        return "api2.mch.weixin.qq.com";
    }

    @Override
    public int getReportWorkerNum() {
        return 1;
    }

    @Override
    public int getReportBatchSize() {
        return 2;
    }
}
