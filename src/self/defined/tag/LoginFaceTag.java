/*
 * 文件名：LoginFaceTag.java 版权：Enmuser Technologies Co.,Ltd. Copyright 2016-2017
 * 描述：<描述> 修改人：Administrator 修改时间：2017年6月11日 修改单号：<修改单号> 修改内容：<修改内容>
 *
 */
package self.defined.tag;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

/**
 * <一句话功能描述> <功能详细描述>
 * 
 * @author 朱洪昌
 * @date 2017年6月11日
 * @version 1.0
 */
public class LoginFaceTag extends SimpleTagSupport
{
	private String action;

	private String method;

	private String enctype;

	/**
	 * 获取 action
	 *
	 * @return 返回 action
	 */
	public String getAction()
	{
		return action;
	}

	/**
	 * 设置 action
	 *
	 * @param action 对 action 进行赋值
	 */
	public void setAction(String action)
	{
		this.action = action;
	}

	/**
	 * 获取 method
	 *
	 * @return 返回 method
	 */
	public String getMethod()
	{
		return method;
	}

	/**
	 * 设置 method
	 *
	 * @param method 对 method 进行赋值
	 */
	public void setMethod(String method)
	{
		this.method = method;
	}

	/**
	 * 获取 enctype
	 *
	 * @return 返回 enctype
	 */
	public String getEnctype()
	{
		return enctype;
	}

	/**
	 * 设置 enctype
	 *
	 * @param enctype 对 enctype 进行赋值
	 */
	public void setEnctype(String enctype)
	{
		this.enctype = enctype;
	}

	@Override
	public void doTag() throws JspException, IOException
	{
		PageContext pageContext = (PageContext) getJspContext();

		JspWriter out = pageContext.getOut();
		
		out.println("<form action='"+action+"' method='"+method+"'>");
		out.println("用户名:<input type='text' name='username'><br/>");
		out.println("密&nbsp;码:<input type='password' name='password'><br/>");
		out.println("<input type='submit' value='登录'><br/>");
		out.println("</form>");
		
		HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();
		String message = (String) request.getAttribute("message");
		if (message != null)
		{
			out.println(message);
		}

	}

}
