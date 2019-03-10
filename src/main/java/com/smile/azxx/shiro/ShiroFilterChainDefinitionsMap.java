package com.smile.azxx.shiro;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Enumeration;
import java.util.LinkedHashMap;

/**
 * Created by smile on 2018/4/6.
 */
public class ShiroFilterChainDefinitionsMap extends
        LinkedHashMap<String, String> {

    Logger log = LoggerFactory.getLogger(getClass());

    public ShiroFilterChainDefinitionsMap() {
        initFilters();
    }

    private void initFilters() {
        OrderProperties p = PropertiesUtil.loadOrderdProperties("shiro/shiro.properties");
        if (!p.isEmpty()) {
            Enumeration e = p.propertyNames();
            while (e.hasMoreElements()) {
                String key = e.nextElement().toString();
                if (key.startsWith("/")) {
                    this.put(key.toString(), p.getProperty(key.toString()));
                }
            }
        }
    }
}
