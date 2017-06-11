/*
 * 文件名：BlackListFilter.java
 * 版权：Enmuser Technologies Co.,Ltd. Copyright 2016-2017
 * 描述：<描述>
 * 修改人：Administrator
 * 修改时间：2017年6月10日
 * 修改单号：<修改单号>
 * 修改内容：<修改内容>
 *
 */
package filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <一句话功能描述> <功能详细描述>
 * 
 * @author 朱洪昌
 * @date 2017年6月10日
 * @version 1.0
 */
public class BlackListFilter implements Filter
{
	private FilterConfig filterConfig;

	@Override
	public void init(FilterConfig filterConfig) throws ServletException
	{
		this.filterConfig = filterConfig;
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException
	{
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse rsp = (HttpServletResponse) response;

		String clientIp = req.getRemoteAddr();

		// 从配置的初始化参数中获取黑名单
		String blacklist = filterConfig.getInitParameter("blacklist");
		System.out.println(
				req.getRequestURL() + "-----" + req.getContextPath() + "/jsp/filter/blacklist.jsp");

		System.out.println("------------------------------------------------");

		System.out.println("req.getRemoteAddr()" + req.getRemoteAddr());
		System.out.println("req.getRemoteHost()" + req.getRemoteHost());
		System.out.println("req.getRemotePort()" + req.getRemotePort());
		System.out.println("req.getRemoteUser()" + req.getRemoteUser());
		System.out.println("req.getRequestedSessionId()" + req.getRequestedSessionId());
		System.out.println("req.getRequestURI()" + req.getRequestURI());
		System.out.println("req.getRequestURL()" + req.getRequestURL());
		System.out.println("req.getContextPath()" + req.getContextPath());
		System.out.println("req.getServerName()" + req.getServerName());
		System.out.println("req.getServerPort()" + req.getServerPort());
		System.out.println("req.getServletPath()" + req.getServletPath());
		System.out.println("req.getPathInfo()" + req.getPathInfo());
		System.out.println("req.getProtocol()" + req.getProtocol());
		System.out.println("req.getScheme()" + req.getScheme());
		System.out.println("req.getServerPort()" + req.getServerPort());

		System.out.println("------------------------------------------------");

		if (blacklist.indexOf(clientIp) != -1
				&& !req.getRequestURL().toString().equals(req.getScheme() + ":" + "//" + req.getServerName() + ":"
						+ req.getServerPort() + req.getContextPath() +"/jsp/filter/blacklist.jsp"))
		{
			System.out.println(req.getScheme() + ":" + "//" + req.getServerName() + ":"
					+ req.getServerPort() +req.getContextPath() +"/jsp/filter/blacklist.jsp");
			rsp.sendRedirect(req.getContextPath() + "/jsp/filter/blacklist.jsp");
			return;
		}
		// 执行下一个组件
		chain.doFilter(request, response);
	}
}
