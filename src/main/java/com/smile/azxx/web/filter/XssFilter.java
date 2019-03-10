package com.smile.azxx.web.filter;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.web.filter.authz.AuthorizationFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

/**
 * @author wangzf
 * 
 */
public class XssFilter extends AuthorizationFilter {
	private Logger logger = LoggerFactory.getLogger(XssFilter.class);
	// 需要拦截的JS字符关键字, applicationContext-shiro.xml配置获取
	private static String[] safeless = {};
	public void setSafeless(String[] safeless) {
		XssFilter.safeless = safeless;
	}

	// 是否校验req.getQueryString
	private static boolean isCheckQueryString = false;
	public void setIsCheckQueryString(boolean isCheckQueryString) {
		XssFilter.isCheckQueryString = isCheckQueryString;
	}

	// 是否校验参数的name值
	private static boolean isCheckParamName = false;
	public void setIsCheckParamName(boolean isCheckParamName) {
		XssFilter.isCheckParamName = isCheckParamName;
	}

	@SuppressWarnings("rawtypes")
	@Override
	protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
		logger.debug("......CelsURLFilter");
		Enumeration params = request.getParameterNames();
		HttpServletRequest req = (HttpServletRequest) request;
		boolean isSafe = true;
		String requestUrl = req.getRequestURI();
		String queryString = req.getQueryString();
		if (!isCheckQueryString || isSafe(queryString)) {
			while (params.hasMoreElements()) {
				String name = params.nextElement().toString();
				if (StringUtils.isNotBlank(name)) {
					if (isCheckParamName && !isSafe(name)) {
						isSafe = false;
						logger.warn("param name NOT safe::::::::::::" + name);
						break;
					}
				}
				String value = req.getParameter(name);
				if (StringUtils.isNotBlank(value)) {
					if (!isSafe(value)) {
						isSafe = false;
						logger.warn("param value NOT safe::::::::::::" + value);
						break;
					}
				}
			}
		} else {
			logger.warn("Url NOT safe::::::::::::" + requestUrl);
			isSafe = false;
		}
		if (!isSafe) {
			// 访问参数中存在HTML特殊字符,访问被终止
			// 异常方式
			// throw new Exception("xss param name NOT safe");
			// 设置鉴权失败路径方式
			// this.setUnauthorizedUrl("/pages/xsscheckerror.jsp");

			return false;
		}
		return true;
	}

	private static boolean isSafe(String str) {
		if (StringUtils.isNotBlank(str)) {
			for (String s : safeless) {
				if (str.toLowerCase().contains(s)) {
					return false;
				}
			}
		}
		return true;
	}
}