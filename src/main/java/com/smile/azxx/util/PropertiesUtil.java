package com.smile.azxx.util;

import com.smile.azxx.web.filter.OrderProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * 配置读取工具
 * 
 * @author tinyjiang
 *
 */
public class PropertiesUtil {
	private static Logger log = LoggerFactory.getLogger(PropertiesUtil.class);

	public static Properties getProperties(String path) {
		Properties p = new Properties();
		try {
			InputStream is = PropertiesUtil.class.getClassLoader()
					.getResourceAsStream(path);
			if (is != null) {
				p.load(is);
			} else {
				log.error("file not found:" + path);
			}
			is.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return p;
	}

	public static OrderProperties loadOrderdProperties(String path) {
		OrderProperties p = new OrderProperties();
		try {
			InputStream is = PropertiesUtil.class.getClassLoader()
					.getResourceAsStream(path);
			if (is != null) {
				p.load(is);
			} else {
				log.error("file not found:" + path);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return p;
	}

	public static String getPropertie(String path, String key) {
		Properties p = getProperties(path);
		return p.getProperty(key);
	}
}
