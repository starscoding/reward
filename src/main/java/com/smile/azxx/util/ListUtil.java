package com.smile.azxx.util;

import java.util.List;

/**
 * Created by smile on 2018/4/7.
 */
public class ListUtil {
    public static boolean isBlank(List list){
        boolean result = true;
        if(list!=null && list.size()>0){
            result = false;
        }
        return result;
    }
    public static boolean isNotBlank(List list){
        boolean result = false;
        if(list!=null && list.size()>0){
            result = true;
        }
        return result;
    }
}
