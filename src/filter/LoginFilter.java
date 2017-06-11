/*
 * 文件名：LoginFilter.java
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
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import shopping.User;

/**
 * <一句话功能描述>
 * <功能详细描述>
 * @author 朱洪昌
 * @date 2017年6月10日
 * @version 1.0
 */
public class LoginFilter implements Filter
{

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException
	{
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse rsp = (HttpServletResponse) response;
		
		User user = (User) req.getSession().getAttribute("user");
		
		if(null != user)
		{
			chain.doFilter(request, response);
			return;
		}
		
		rsp.sendRedirect(req.getContextPath()+"/jsp/shop/UserLogin.jsp");
	}

}
