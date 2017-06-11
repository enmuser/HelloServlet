/*
 * 文件名：CharSetFilter.java
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
 * <一句话功能描述>
 * 统一设置字符编码
 * @author 朱洪昌
 * @date 2017年6月10日
 * @version 1.0
 */
public class CharSetFilter implements Filter
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
		
		//从配置的初始化参数中获取字符编码
		String encoding = filterConfig.getInitParameter("encoding");
		
		req.setCharacterEncoding(encoding);
		rsp.setContentType("text/html;charset="+encoding);
		//执行下一个组件
		chain.doFilter(request, response);
	}

}
