/**
 * 
 */
package com.smile.azxx.util;

import com.smile.azxx.shiro.Constants;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.security.MessageDigest;

/**
 * @author Cason
 * 
 * @Date Apr 20, 2012
 */
public class CommonUtil {
	private static Logger log = LoggerFactory.getLogger(CommonUtil.class);



	/**
	 * 使用加密规则对字符串进行加密，如果成功，返回加密后的字符串，否则返回原字符串
	 *
	 * @param password
	 *
	 * @return
	 */
	public static String encodePassword(String password) {
		return encodePassword(password, Constants.SYS_PASSWORD_ENCODE_MODE);
	}

	/**
	 * 使用加密规则对字符串进行加密，如果成功，返回加密后的字符串，否则返回原字符串
	 * 
	 * @param password
	 * @param
	 * 
	 * @return
	 */
	public static String encodePassword(String password, SecureType t) {
		byte[] unencodedPassword = password.getBytes();

		MessageDigest md = null;

		try {
			md = MessageDigest.getInstance(t.toString());
		} catch (Exception e) {
			e.printStackTrace();
			return password;
		}

		md.reset();
		md.update(unencodedPassword);

		byte[] encodedPassword = md.digest();

		StringBuffer buf = new StringBuffer();

		for (int i = 0; i < encodedPassword.length; i++) {
			if ((encodedPassword[i] & 0xff) < 0x10) {
				buf.append("0");
			}

			buf.append(Long.toString(encodedPassword[i] & 0xff, 16));
		}

		return buf.toString();
	}

	public static String getFileSuffix(String filename) {
		int pos = filename.lastIndexOf(".");
		if (pos != -1)
			return filename.substring(pos);
		return null;
	}

	public static String getCurrentDomain(HttpServletRequest req) {
		String domain = req.getServerName();
		String port = req.getServerPort() + "";
		if (!"80".equals(port)) {
			domain = domain + ":" + port;
		}
		return domain;
	}

	public static String getIPFromResuest(HttpServletRequest request) {
		// 取用户IP
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}

	public static String getRequestURL(HttpServletRequest request) {
		String servletPath = request.getServletPath();
		if (StringUtils.isBlank(servletPath)) {// was服务器下无法正确获取路径采取以下方法获取
			servletPath = request.getRequestURI().substring(
					request.getContextPath().length());
		}
		if (servletPath != null && servletPath.trim().length() > 0
				&& servletPath.indexOf("?") > 0) {
			servletPath = servletPath.substring(0, servletPath.indexOf("?"));
		}
		String qstr = request.getQueryString();
		if (qstr != null) {
			if (qstr.contains("_dc")) {
				qstr = qstr.substring(0, qstr.indexOf("_dc"));
				int i = 0;
				if (qstr.endsWith("&&")) {
					i = 2;
				} else if (qstr.endsWith("&")) {
					i = 1;
				}
				qstr = qstr.substring(0, qstr.length() - i);
			}
			if (qstr.length() > 0) {
				servletPath += "?" + qstr;
			}
		}
		return servletPath;
	}

	public static String getContextParam(ServletContext ctx, String name) {
		String value = "";
		try {
			value = ctx.getInitParameter(name);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return value;
	}

	/**
	 * 是否需要连接ECIP
	 * 
	 * @param
	 * @return
	 */
	public static boolean needLinkECIP(ServletContext ctx) {
		return getContextBooleanParam(ctx, "LinkECIP");
	}

	public static boolean getContextBooleanParam(ServletContext ctx, String name) {
		boolean flag = false;
		try {
			String _flag = CommonUtil.getContextParam(ctx, name);
			if (StringUtils.isNotBlank(_flag)) {
				flag = Boolean.parseBoolean(_flag);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	/**
	 * 获取当前加密方式
	 * 
	 * @param
	 * @return
	 */
	public static SecureType getCurrentSecureType(ServletContext ctx) {
		SecureType t = SecureType.SHA;
		try {
			String _flag = CommonUtil.getContextParam(ctx, "SecureType");
			if ("MD5".equals(_flag)) {
				t = SecureType.MD5;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return t;
	}

	/**
	 * 是否包含中文
	 * 
	 * @param
	 */
	public static boolean isContainChinese(String str) {
		for (int i = 0; i < str.length(); i++) {
			if (str.substring(i, i + 1).matches("[\\u4e00-\\u9fa5]+"))
				return true;
		}
		return false;
	}

	/**
	 * 获取当前主题
	 * 
	 * @param request
	 * @return
	 */
	public static String getTheme(HttpServletRequest request) {
		// 从session中获取当前主题
		HttpSession session = request.getSession();
		Object theme_o = session.getAttribute("theme");
		String theme = "";
		if (theme != null) {
			theme = (String) theme_o;
		}

		// 从cookie中获取主题，以cookie为主，若不存在，则使用session中的主题
		Cookie[] cookies = request.getCookies();
		if (cookies != null && cookies.length > 0) {
			for (Cookie c : cookies) {
				if (c.getName().equals("theme")) {
					theme = c.getValue();
				}
			}
		}
		// session和cookie中都去不到theme则使用默认蓝色主题
		if (StringUtils.isBlank(theme)) {
			theme = "blue";
		}
		return theme;
	}

//	public static String getJslib(HttpServletRequest request) {
//		String jslib = "";
//		if (com.eastcom_sw.sysmng.utils.Constants.USING_CDN) {
//			jslib = com.eastcom_sw.sysmng.utils.PropertiesUtil.getPropertie(
//					"cdn/cdn.properties", "cdn_server");
//		} else {
//			String ctx = getFullWebContext(request);
//			jslib = ctx + "/static/jslib";
//		}
//		return jslib;
//	}
	
//	public static String getRelativeJslib(HttpServletRequest request) {
//		String jslib = "";
//		if (com.eastcom_sw.sysmng.utils.Constants.USING_CDN) {
//			jslib = com.eastcom_sw.sysmng.utils.PropertiesUtil.getPropertie(
//					"cdn/cdn.properties", "cdn_server");
//		} else {
//			String ctx = getRelativeWebContext(request);
//			jslib = ctx + "/static/jslib";
//		}
//		return jslib;
//	}

//	public static ShiroUser getUser() {
//		Subject subject = null;
//		ShiroUser user = null;
//		try {
//			subject = SecurityUtils.getSubject();
//			if (subject != null) {
//				try {
//					user = (ShiroUser) subject.getPrincipal();
//				} catch (Exception e) {// subject中获取不成功则从session中获取
//					try {
//						user = (ShiroUser) subject.getSession().getAttribute(
//								"c_user");
//					} catch (Exception e1) {
//						e1.printStackTrace();
//					}
//				}
//			}
//		} catch (Exception e) {
//			log.error("获取当前登录用户失败，请检查是否已经正确登录，当前返回unkonwn用户。");
//		}
//		return user == null ? new ShiroUser("unknown", "unknown") : user;
//
//	}

	public static String getFullWebContext(HttpServletRequest request) {
		String basePath = "";
		String servletPath = request.getServletPath();
		String fullUrl = request.getRequestURL().toString();
		if (fullUrl.indexOf(servletPath) != -1) {
			basePath = fullUrl.substring(0, fullUrl.indexOf(servletPath));
		} else {
			basePath = request.getContextPath();
		}
		return basePath;
	}
	
	public static String getRelativeWebContext(HttpServletRequest request) {
		return request.getContextPath();
	}
	
	/**
	 * csrftoken 
	 * @return
	 */
	public static String getLoginToken(HttpServletRequest request){
		HttpSession session = request.getSession(false);
		if(session == null){
			return null;
		}else{
			if(session.getAttribute("CSRFToken")!=null){
				session.removeAttribute("CSRFToken");
			}
		}
		RandomGUID myGUID = new RandomGUID();
		String token = myGUID.getUUID32();
		
		session.setAttribute("CSRFToken", token);
		
		return token;
	}
	

	public static void main(String[] args) {
	}
}
