package com.smile.azxx.web.filter;

import com.smile.azxx.util.PropertiesUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Enumeration;

@SuppressWarnings("serial")
public class XssFilterSafelessList extends ArrayList<String> {
	Logger log = LoggerFactory.getLogger(getClass());

	public XssFilterSafelessList() {
		initSafelessList();
	}

	@SuppressWarnings("rawtypes")
	private void initSafelessList() {
		OrderProperties p = PropertiesUtil.loadOrderdProperties("shiro/xssFilter.properties");
		if (!p.isEmpty()) {
			Enumeration e = p.propertyNames();
			while (e.hasMoreElements()) {
				String key = e.nextElement().toString();
				if (key != null && key.trim().length() > 0) {
					String value = p.getProperty(key);
					this.add(key + (value == null ? "" : value));
				}
			}
		}
	}
}