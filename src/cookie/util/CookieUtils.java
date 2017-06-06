/*
 * 文件名：CookieUtils.java
 * 版权：Enmuser Technologies Co.,Ltd. Copyright 2016-2017
 * 描述：<描述>
 * 修改人：Administrator
 * 修改时间：2017年5月30日
 * 修改单号：<修改单号>
 * 修改内容：<修改内容>
 *
 */
package cookie.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <一句话功能描述> <功能详细描述>
 * 
 * @author 朱洪昌
 * @date 2017年5月30日
 * @version 1.0
 */
public class CookieUtils
{
	class CookieExpireTime
	{
		private static final int ONE_YEAR = 365 * 24 * 60 * 60;
		private static final int ONE_MONTH = 30 * 24 * 60 * 60;
		private static final int ONE_DAY = 1 * 24 * 60 * 60;
		private static final int ONE_HOUR = 1 * 60 * 60;
	}

	/**
	 * <一句话描述方法> <功能详细描述>
	 * 
	 * @param response
	 * @param cookieName
	 * @param cookieValue
	 * @return void
	 * @author: 朱洪昌
	 * @date: 2017年5月30日 上午1:17:54
	 */
	public static void addCookie(HttpServletResponse response, String cookieName, String cookieValue,
			int cookieExpireTime, String workPath)
	{
		try
		{
			Cookie cookie = new Cookie(cookieName, URLEncoder.encode(cookieValue, "utf-8"));
			cookie.setMaxAge(cookieExpireTime);
			if(null != workPath)
			cookie.setPath(workPath);
			response.addCookie(cookie);
		}
		catch (UnsupportedEncodingException e)
		{
			e.printStackTrace();
		}
	}
	
	public static void addCookie(HttpServletResponse response, String cookieName, String cookieValue)
	{
		try
		{
			Cookie cookie = new Cookie(cookieName, URLEncoder.encode(cookieValue, "utf-8"));
			cookie.setMaxAge(CookieExpireTime.ONE_DAY);
			response.addCookie(cookie);
		}
		catch (UnsupportedEncodingException e)
		{
			e.printStackTrace();
		}
	}

	public static Map<String, String> getCookiesMap(HttpServletRequest request)
	{
		Map<String, String> map = new HashMap<String, String>();
		if (null == request.getCookies() || 0 == request.getCookies().length)
		{
			return null;
		}

		for (Cookie cookie : request.getCookies())
		{
			try
			{
				map.put(cookie.getName(), URLDecoder.decode(cookie.getValue(),"utf-8"));
			}
			catch (UnsupportedEncodingException e)
			{
				e.printStackTrace();
			}
		}

		return map;
	}

	public static void modifyCookie(HttpServletResponse response, String cookieName, String cookieNewValue,int cookieExpireTime, String workPath)
	{
		addCookie(response, cookieName, cookieNewValue,cookieExpireTime,workPath);
	}

	public static void removeCookie(HttpServletResponse response, String cookieName)
	{
		Cookie cookie = new Cookie(cookieName, "");
		cookie.setMaxAge(0);
		response.addCookie(cookie);
	}
}
