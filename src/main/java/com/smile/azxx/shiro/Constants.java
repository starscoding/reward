package com.smile.azxx.shiro;

import com.smile.azxx.util.SecureType;

public class Constants {
    public static final String CURRENT_USER = "user";

    /** 存放图片验证码字符串 的session名字 */
    public static final String SIMPLE_CAPCHA_SESSION_KEY = "LOGIN_VERIFY_CODE";

    /**
     * 密码在系统中的加密方式 默认SHA，web.xml设置SecureType初始化时会更改此项设置
     */
    public static SecureType SYS_PASSWORD_ENCODE_MODE = SecureType.SHA;
}
