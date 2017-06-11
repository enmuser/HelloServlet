/*
 * 文件名：LoggerFilter.java
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
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
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
public class LoggerFilter implements Filter
{

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException
	{
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse rsp = (HttpServletResponse) response;
        //对请求做拦截处理
		System.out.println(
				req.getRemoteAddr() + "在" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())
						+ "发送了" + req.getRequestURI() + "请求");

		chain.doFilter(request, response);

		//对响应做拦截处理
		System.out.println(
				req.getRemoteAddr() + "在" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())
						+ "结束了" + req.getRequestURI() + "请求");

	}

}
