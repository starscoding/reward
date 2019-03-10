package com.smile.azxx.util;

import com.google.gson.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by smile on 2018/5/5.
 */
public class ShortUrlUtils {

    public static String getShortUrl(String longUrl){
        String key = "2849184197";
        String url = "http://api.weibo.com/2/short_url/shorten.json?source="+key+"&url_long="+longUrl;
        String urlJson = HttpClientUtil.doGet(url);
        System.out.println(urlJson);
        System.out.println(url);
        Gson gson = new Gson();
        JsonParser parser = new JsonParser();

        JsonObject jsonObj = parser.parse(urlJson).getAsJsonObject();
        JsonArray jsonArray = jsonObj.getAsJsonArray("urls");

        Map<String,String> result = new HashMap<>();
        for (JsonElement temp:jsonArray) {
            result = gson.fromJson(temp,HashMap.class);
//            System.out.println("url_short>>>"+result.get("url_short"));
//            System.out.println("url_long>>>"+result.get("url_long"));
        }
        return result.get("url_short");
    }




    public static void main(String[] args) {
        Gson gson = new Gson();

//        String longUrl = "http://localhost:7090/rewardweb/pages/main.jsp";
//        String longUrl = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx20d21ee2da2cce0d&redirect_uri=http%3A%2F%2Fwww.isspark.com%2Freward%2Fwxpay%2Forder.jsp%3Fid%3Dabcdef%26order%3Dcdefg&response_type=code&scope=snsapi_base&state=STATE&connect_redirect=1#wechat_redirect";
//        URLEncoder.encode(longUrl);
//        String key = "2849184197";
//        String url = "http://api.weibo.com/2/short_url/shorten.json?source="+key+"&url_long="+URLEncoder.encode(longUrl)+"&url_long="+URLEncoder.encode(longUrl+"a");
//        System.out.println(url);
//        System.out.println(getShortUrl(url));
//        String urlJson = getShortUrl(url);
//        Map<String,Object> map = gson.fromJson(urlJson, HashMap.class);
//        System.out.println(map.get("urls"));
//        JsonParser parser = new JsonParser();
//        JsonObject object = parser.parse(urlJson).getAsJsonObject();
//        JsonArray array = object.getAsJsonArray("urls");
//
//        for (JsonElement temp:array) {
//            Map<String,String> hahah = gson.fromJson(temp,HashMap.class);
//            System.out.println("url_short>>>"+hahah.get("url_short"));
//            System.out.println("url_long>>>"+hahah.get("url_long"));
//        }
    }
}
