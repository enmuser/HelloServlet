/*
 * 文件名：UserShoppingServlet.java
 * 版权：Enmuser Technologies Co.,Ltd. Copyright 2016-2017
 * 描述：<描述>
 * 修改人：Administrator
 * 修改时间：2017年6月3日
 * 修改单号：<修改单号>
 * 修改内容：<修改内容>
 *
 */
package shopping;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cookie.util.CookieUtils;
import shopping.utils.ShopUtils;

/**
 * <一句话功能描述>
 * <功能详细描述>
 * @author 朱洪昌
 * @date 2017年6月3日
 * @version 1.0
 */
public class UserShoppingServlet extends HttpServlet
{
  
	@Override
	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		String urlName = request.getServletPath().substring(1,request.getServletPath().lastIndexOf("."));
		System.out.println(urlName);
		if("register".equals(urlName))
		{
			register(request,response);
		}
		if("login".equals(urlName))
		{
			login(request,response);
		}
		if("logOut".equals(urlName))
		{
			logOut(request,response);
		}
	}

	/** 
	  * <一句话描述方法>
	  * <功能详细描述>
	  * @param request
	  * @param response 
	  * @return void 
	  * @author: 朱洪昌
	  * @date: 2017年6月3日 下午9:15:50
	  */ 
	private void logOut(HttpServletRequest request, HttpServletResponse response)
	{
		request.getSession().removeAttribute("userName");
		try
		{
			request.getRequestDispatcher("/OfferingList.product").forward(request, response);
		}
		catch (ServletException e)
		{
			e.printStackTrace();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		
	}

	/** 
	  * <一句话描述方法>
	  * <功能详细描述>
	  * @param request
	  * @param response 
	  * @return void 
	  * @author: 朱洪昌
	  * @date: 2017年6月3日 上午10:59:51
	  */ 
	private void login(HttpServletRequest request, HttpServletResponse response)
	{
		User user = new User();
		String loginName = request.getParameter("username");
		String passwd = request.getParameter("password");
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try
		{
			String url = "jdbc:mysql://127.0.0.1:3306/webapp?useUnicode=true&characterEncoding=utf8";
			String userName = "root";
			String password = "";
			String sql = new StringBuffer().append("select * ").append(" from ").append(" t_user ")
					.append(" where username = ? ").append(" and password = ? ").toString();
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(url, userName, password);
			con.setAutoCommit(false);
			ps = con.prepareStatement(sql);
			ps.setString(1, loginName);
			ps.setString(2, passwd);
			rs = ps.executeQuery();
			if (rs.next())
			{
				user.setId(rs.getInt("id"));
				user.setUsername(rs.getString("username"));
				user.setPassword(rs.getString("password"));
				user.setUseremail(rs.getString("useremail"));
				user.setPhone(rs.getString("phone"));
				user.setAddress(rs.getString("address"));
				con.commit();
				request.getSession().setAttribute("user", user);
//				request.setAttribute("userName", loginName);
				request.getSession().setAttribute("userName", loginName);
				request.getSession().setAttribute("password", passwd);
				request.getRequestDispatcher("/OfferingList.product").forward(request, response);
				return;
			}
			request.setAttribute("message", "用户名或密码错误！");
			request.getRequestDispatcher("/jsp/shop/UserLogin.jsp").forward(request, response);

		}
		catch (Exception e)
		{
			try
			{
				con.rollback();
			}
			catch (SQLException e1)
			{
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally
		{
			ShopUtils.closeConnection(rs, ps, con);
		}
	}

	/** 
	  * <一句话描述方法>
	  * <功能详细描述>
	  * @param request
	  * @param response 
	  * @return void 
	  * @author: 朱洪昌
	  * @date: 2017年6月3日 上午10:42:08
	  */ 
	private void register(HttpServletRequest request, HttpServletResponse response)
	{
		String userName = request.getParameter("username");
		String password = request.getParameter("password");
		String userEmail = request.getParameter("userEmail");
		String userphoneNumber = request.getParameter("userphoneNumber");
		String userAddress = request.getParameter("userAddress");
		String url = "jdbc:mysql://127.0.0.1:3306/webapp?useUnicode=true&characterEncoding=utf8";
		String username = "root";
		String dataPassword = "";
		System.out.println("username: " + userName);
		System.out.println("password: " + password);
		System.out.println("userEmail: " + userEmail);
		System.out.println("userphoneNumber: " + userphoneNumber);
		System.out.println("userAddress: " + userAddress);
		Connection con = null;
		PreparedStatement ps = null;
		try
		{
			String sql = new StringBuilder().append(" insert into t_user ")
					.append("(username,password,useremail,phone,address) ")
					.append("values (?,?,?,?,?)").toString();
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(url, username, dataPassword);
			con.setAutoCommit(false);
			ps = con.prepareStatement(sql);
			ps.setString(1, userName);
			ps.setString(2, password);
			ps.setString(3, userEmail);
			ps.setString(4, userphoneNumber);
			ps.setString(5, userAddress);
			ps.executeUpdate();
			con.commit();
			request.getRequestDispatcher("/OfferingList.product").forward(request, response);
		}
		catch (Exception e)
		{
			try
			{
				con.rollback();
			}
			catch (SQLException e1)
			{
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally
		{
			ShopUtils.closeConnection(null, ps, con);
		}

	}
	
	
}
