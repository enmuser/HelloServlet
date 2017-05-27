/*
 * 文件名：HelloServlet.java
 * 版权：Enmuser Technologies Co.,Ltd. Copyright 2016-2017
 * 描述：<描述>
 * 修改人：Administrator
 * 修改时间：2017年5月14日
 * 修改单号：<修改单号>
 * 修改内容：<修改内容>
 *
 */
package user.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <一句话功能描述>
 * <功能详细描述>
 * @author 朱洪昌
 * @date 2017年5月14日
 * @version 1.0
 */
public class HelloServlet extends HttpServlet
{

	@Override
	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		String name = request.getParameter("name");
		String age = request.getParameter("age");

    	PrintWriter writer = response.getWriter(); 
    	
    	writer.write("<h1>Hello My name is "+name+",I am "+age+" years old</h1>");
	}

}
