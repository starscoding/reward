package com.smile.azxx.shiro;

import java.util.Enumeration;
import java.util.Properties;
import java.util.Vector;

public class OrderProperties extends Properties {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@SuppressWarnings("rawtypes")
	private Vector _names;

	@SuppressWarnings("rawtypes")
	public OrderProperties() {
		super();
		_names = new Vector();
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Enumeration propertyNames() {
		return _names.elements();
	}

	@SuppressWarnings("unchecked")
	public Object put(Object key, Object value) {
		if (_names.contains(key)) {
			_names.remove(key);
		}

		_names.add(key);

		return super.put(key, value);
	}

	public Object remove(Object key) {
		_names.remove(key);

		return super.remove(key);
	}
}