package com.smile.azxx.util;

import com.smile.azxx.shiro.Constants;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.font.TextLayout;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Calendar;
import java.util.Random;

/**
 * Created by smile on 2018/4/9.
 */
public class ValidateCodeUtil {


    private static final long serialVersionUID = 1L;

    private static Random random = new Random();

    private static char[] captchars = new char[] { '2', '3', '4', '5', '6',
            '7', '8', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'k', 'm',
            'n', 'p', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'A', 'B', 'C',
            'D', 'E', 'F', 'G', 'H', 'J', 'K', 'M', 'N', 'P', 'Q', 'R', 'S',
            'T', 'U', 'V', 'W', 'X', 'Y' };//0,1,9,o,i,q等容易混淆的数字字符不生成



    public static void getRandcode(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        int num = 4;//设置验证码字符数
        int imageWidth = num * 29;//设置图片宽度
        int imageHeight = 42;//设置图片高度
        int line_left = 4;//从左上到右下的线条个数
        int line_right = 5;//从右上到左下的线条个数

        int car = captchars.length - 1;

        //随机写字母或数字
        String text = "";
        Font font = getFont();// 获得写的时候的字体
        for (int i = 0; i < num; i++) {
            text += captchars[random.nextInt(car) + 1];
        }

        //设置验证码有效时间  5分钟
        String afterTime = String.valueOf(Calendar.getInstance().getTimeInMillis()+300000);


        if(req.getSession(true).getAttribute(Constants.SIMPLE_CAPCHA_SESSION_KEY) != null){
            req.getSession(true).removeAttribute(Constants.SIMPLE_CAPCHA_SESSION_KEY);
        }

        req.getSession(true).setAttribute(
                Constants.SIMPLE_CAPCHA_SESSION_KEY, text+"&&"+afterTime);// 将字符串写入session

//		JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(resp
//				.getOutputStream());// 得到输出流

        BufferedImage bi = new BufferedImage(imageWidth, imageHeight,
                BufferedImage.TYPE_BYTE_INDEXED);

        Graphics2D graphics = bi.createGraphics();

        Color c = getRandColor(220, 250);
        graphics.setColor(c);// 设置背景色

        graphics.fillRect(0, 0, bi.getWidth(), bi.getHeight());

        graphics.setColor(Color.black);

        graphics.setFont(font);
        graphics.setColor(getRandColor());
        graphics.drawString(text, 15, 30);// 将字写到图片上

        // 获得字体一样的字，20是字体的大小
        TextLayout textTl = new TextLayout(text, new Font("Fixedsys",
                Font.PLAIN, 30), new FontRenderContext(null, true, false));
        //对字体加投影，第二个是左右相距，越大越远，第三个参数是上下两层相距距离，越大越近
        textTl.draw(graphics, 10, 70);

        int w = bi.getWidth();
        int h = bi.getHeight();
        shear(graphics, w, h, c);// 使图片扭曲

        //加一道线
//		this.drawThickLine(graphics, 0, random.nextInt(imageHeight) + 1,
//				imageWidth, random.nextInt(imageHeight) + 1, 4, getRandColor(
//						100, 200));

        // 从左上到右下加上多道干扰线
        graphics.setColor(getRandColor(160, 200));// 设置线条的颜色
        for (int i = 0; i < line_left; i++) {
            int x = random.nextInt(imageWidth - 1);
            int y = random.nextInt(imageHeight - 1);
            int xl = random.nextInt(6) + 1;
            int yl = random.nextInt(12) + 1;
            graphics.drawLine(x, y, x + xl + 40, y + yl + 20);
        }

        // 从右上到左下加多道干扰线
        for (int i = 0; i < line_right; i++) {
            int x = random.nextInt(imageWidth - 1);
            int y = random.nextInt(imageHeight - 1);
            int xl = random.nextInt(12) + 1;
            int yl = random.nextInt(6) + 1;
            graphics.drawLine(x, y, x - xl + 40, y - yl);
        }

        // 添加噪点
        float yawpRate = 0.05f;// 噪声率
        int area = (int) (yawpRate * w * h);
        for (int i = 0; i < area; i++) {
            int x = random.nextInt(w);
            int y = random.nextInt(h);
            int rgb = getRandomIntColor();
            bi.setRGB(x, y, rgb);
        }

        // 设置图片类型
        resp.setContentType("image/jpg");
        // 写出
        //encoder.encode(bi);
        ImageIO.write(bi, "JPEG", resp.getOutputStream());
    }

    /**
     * 画一道粗线的方法
     * @param g
     * @param x1
     * @param y1
     * @param x2
     * @param y2
     * @param thickness
     * @param c
     */
    private void drawThickLine(Graphics g, int x1, int y1, int x2, int y2,
                               int thickness, Color c) {

        g.setColor(c);
        int dX = x2 - x1;
        int dY = y2 - y1;
        double lineLength = Math.sqrt(dX * dX + dY * dY);

        double scale = (double) (thickness) / (2 * lineLength);

        double ddx = -scale * (double) dY;
        double ddy = scale * (double) dX;
        ddx += (ddx > 0) ? 0.5 : -0.5;
        ddy += (ddy > 0) ? 0.5 : -0.5;
        int dx = (int) ddx;
        int dy = (int) ddy;

        int xPoints[] = new int[4];
        int yPoints[] = new int[4];

        xPoints[0] = x1 + dx;
        yPoints[0] = y1 + dy;
        xPoints[1] = x1 - dx;
        yPoints[1] = y1 - dy;
        xPoints[2] = x2 - dx;
        yPoints[2] = y2 - dy;
        xPoints[3] = x2 + dx;
        yPoints[3] = y2 + dy;

        g.fillPolygon(xPoints, yPoints, 4);
    }



    /**
     * 使图片扭曲
     * @param g
     * @param w1
     * @param h1
     * @param color
     */
    private static void shear(Graphics g, int w1, int h1, Color color) {
        shearX(g, w1, h1, color);
        shearY(g, w1, h1, color);
    }

    private static void shearX(Graphics g, int w1, int h1, Color color) {

        int period = random.nextInt(80);

        boolean borderGap = true;
        int frames = 1;
        int phase = random.nextInt(80);

        for (int i = 0; i < h1; i++) {
            double d = (double) (period >> 1)
                    * Math.sin((double) i / (double) period
                    + (6.2831853071795862D * (double) phase)
                    / (double) frames);
            g.copyArea(0, i, w1, 1, (int) d, 0);
            if (borderGap) {
                g.setColor(color);
                g.drawLine((int) d, i, 0, i);
                g.drawLine((int) d + w1, i, w1, i);
            }
        }
    }

    private static void shearY(Graphics g, int w1, int h1, Color color) {

        int period = random.nextInt(15) + 1; // 50;

        boolean borderGap = true;
        int frames = 20;
        int phase = 7;
        for (int i = 0; i < w1; i++) {
            double d = (double) (period >> 1)
                    * Math.sin((double) i / (double) period
                    + (6.2831853071795862D * (double) phase)
                    / (double) frames);
            g.copyArea(i, 0, 1, h1, 0, (int) d);
            if (borderGap) {
                g.setColor(color);
                g.drawLine(i, (int) d, i, 0);
                g.drawLine(i, (int) d + h1, i, h1);
            }
        }
    }

    /**
     * 产生随机字体
     */
    private static Font getFont() {
        Random random = new Random();
        Font font[] = new Font[5];
        font[0] = new Font("Ravie", Font.BOLD, 30);
        font[1] = new Font("Times New Roman", Font.BOLD, 30);
        font[2] = new Font("Fixedsys", Font.BOLD, 30);
        font[3] = new Font("SansSerif", Font.BOLD, 30);
        font[4] = new Font("Palatino Linotype", Font.BOLD, 30);
        return font[random.nextInt(5)];
    }

    /**
     * 产生随机色
     * @return
     */
    private static Color getRandColor() {
        Random random = new Random();
        Color color[] = new Color[10];
        color[0] = new Color(72, 72, 72);
        color[1] = new Color(147, 89, 26);
        color[2] = new Color(101, 61, 123);
        color[3] = new Color(54, 129, 96);
        color[4] = new Color(214, 164, 76);
        return color[random.nextInt(5)];
    }

    /**
     * 产生随机色
     * @param fc
     * @param bc
     * @return
     */
    private static Color getRandColor(int fc, int bc) {
        if (fc > 255)
            fc = 255;
        if (bc > 255)
            bc = 255;
        int r = fc + random.nextInt(bc - fc);
        int g = fc + random.nextInt(bc - fc);
        int b = fc + random.nextInt(bc - fc);
        return new Color(r, g, b);
    }

    // 添加噪点的方法
    private static int getRandomIntColor() {
        int[] rgb = getRandomRgb();
        int color = 0;
        for (int c : rgb) {
            color = color << 8;
            color = color | c;
        }
        return color;
    }

    private static int[] getRandomRgb() {
        int[] rgb = new int[3];
        for (int i = 0; i < 3; i++) {
            rgb[i] = random.nextInt(255);
        }
        return rgb;
    }

}
