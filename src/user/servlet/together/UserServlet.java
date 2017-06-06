package user.servlet.together;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cookie.util.CookieUtils;

public class UserServlet extends HttpServlet
{
	@Override
	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		String urlName = request.getServletPath().substring(1, request.getServletPath().lastIndexOf("."));
		System.out.println(urlName);
		if ("addUser".equals(urlName))
		{
			addUser(request, response);
		}
		if ("modifyUser".equals(urlName))
		{
			modifyUser(request, response);
		}
		if ("deleteUser".equals(urlName))
		{
			deleteUser(request, response);
		}
		if ("queryUser".equals(urlName))
		{
			queryUser(request, response);
		}
		if ("modifyUserById".equals(urlName))
		{
			queryUserId(request, response);
		}
		if ("login".equals(urlName))
		{
			login(request, response);
		}
	}

	public void login(HttpServletRequest request, HttpServletResponse response)
	{
		String verifyCode = (String) request.getSession().getAttribute("verifyCode");
		String inputCode = request.getParameter("verifyCode");
		System.out.println(verifyCode + "----" + inputCode);
		if (null == inputCode || null == verifyCode || !inputCode.toLowerCase().equals(verifyCode.toLowerCase()))
		{
			request.setAttribute("message", "验证码错误！");
			try
			{
				request.getRequestDispatcher("/jsp/user/Login.jsp").forward(request, response);
			}
			catch (ServletException e)
			{
				e.printStackTrace();
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
			return;
		}
		String loginName = request.getParameter("loginName");
		String passwd = request.getParameter("loginpwd");
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try
		{
			String url = "jdbc:mysql://127.0.0.1:3306/webapp?useUnicode=true&characterEncoding=utf8";
			String userName = "root";
			String password = "";
			String sql = new StringBuffer().append("select * ").append(" from ").append(" t_ecp_user ")
					.append(" where USERNAME = ? ").append(" and PASSWORD = ? ").toString();
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(url, userName, password);
			con.setAutoCommit(false);
			ps = con.prepareStatement(sql);
			ps.setString(1, loginName);
			ps.setString(2, passwd);
			rs = ps.executeQuery();
			if (rs.next())
			{
				con.commit();
				CookieUtils.addCookie(response, "username", loginName);
				CookieUtils.addCookie(response, "password", passwd);
				request.getSession().removeAttribute("verifyCode");
				response.sendRedirect(request.getContextPath() + "/queryUser.do");
				return;
			}
			request.setAttribute("message", "用户名或密码错误！");
			request.getRequestDispatcher("/jsp/user/Login.jsp").forward(request, response);

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
			closeConnection(rs, ps, con);
		}

	}

	public void queryUserId(HttpServletRequest request, HttpServletResponse response)
	{
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try
		{
			String url = "jdbc:mysql://127.0.0.1:3306/webapp?useUnicode=true&characterEncoding=utf8";
			String userName = "root";
			String password = "";
			String sql = new StringBuffer().append("select * ").append(" from ").append(" t_ecp_user ")
					.append(" where USERID = ? ").toString();
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(url, userName, password);
			con.setAutoCommit(false);
			ps = con.prepareStatement(sql);
			ps.setString(1, request.getParameter("userId"));
			rs = ps.executeQuery();
			UserInfo info = new UserInfo();
			if (rs.next())
			{
				String userId = rs.getString("USERID");
				String username = rs.getString("USERNAME");
				double salary = rs.getDouble("SALARY");
				String userEmail = rs.getString("USEREMAIL");
				String sex = rs.getString("SEX");
				String education = rs.getString("EDUCATION");
				String hobbies = rs.getString("HOBBIES");
				String passwd = rs.getString("PASSWORD");
				info.setUserId(userId);
				info.setUsername(username);
				info.setUserEmail(userEmail);
				info.setEducation(education);
				info.setSalary(salary);
				info.setSex(sex);
				info.setHobbies(hobbies);
				info.setPassword(passwd);

			}
			request.setAttribute("userInfo", info);
			request.getRequestDispatcher("/jsp/user/ModifyUser.jsp").forward(request, response);
			con.commit();
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
			closeConnection(rs, ps, con);
		}
	}

	public void addUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");
		String userEmail = request.getParameter("userEmail");
		String sex = request.getParameter("sex");
		double salary = Double.parseDouble(request.getParameter("salary"));
		String education = request.getParameter("education");
		String[] hobbies = request.getParameterValues("hobby");
		String hobbiesStr = Arrays.toString(hobbies);
		String url = "jdbc:mysql://127.0.0.1:3306/webapp?useUnicode=true&characterEncoding=utf8";
		String username = "root";
		String dataPassword = "";
		System.out.println("username: " + userName);
		System.out.println("password: " + password);
		System.out.println("userEmail: " + userEmail);
		System.out.println("sex: " + sex);
		System.out.println("salay: " + salary);
		System.out.println("education: " + education);
		System.out.println("hobbies: " + hobbiesStr);
		Connection con = null;
		PreparedStatement ps = null;
		try
		{
			String sql = new StringBuilder().append(" insert into t_ecp_user ")
					.append("(USERNAME,PASSWORD,USEREMAIL,SEX,SALARY,EDUCATION,HOBBIES) ")
					.append("values (?,?,?,?,?,?,?)").toString();
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(url, username, dataPassword);
			con.setAutoCommit(false);
			ps = con.prepareStatement(sql);
			ps.setString(1, userName);
			ps.setString(2, password);
			ps.setString(3, userEmail);
			ps.setString(4, sex);
			ps.setDouble(5, salary);
			ps.setString(6, education);
			ps.setString(7, hobbiesStr);
			ps.executeUpdate();
			PrintWriter writer = response.getWriter();
			writer.print("<h1 font-color='red'>恭喜" + userName + "注册成功</h1>");
			con.commit();

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
			closeConnection(null, ps, con);
		}

		response.sendRedirect(request.getContextPath() + "/queryUser.do");
	}

	public void modifyUser(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		String userId = request.getParameter("userId");
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");
		String userEmail = request.getParameter("userEmail");
		String sex = request.getParameter("sex");
		double salary = Double.parseDouble(request.getParameter("salary"));
		String education = request.getParameter("education");
		String[] hobbies = request.getParameterValues("hobby");
		String hobbiesStr = Arrays.toString(hobbies);
		String url = "jdbc:mysql://127.0.0.1:3306/webapp?useUnicode=true&characterEncoding=utf8";
		String username = "root";
		String dataPassword = "";
		System.out.println("userid: " + userId);
		System.out.println("username: " + userName);
		System.out.println("password: " + password);
		System.out.println("userEmail: " + userEmail);
		System.out.println("sex: " + sex);
		System.out.println("salay: " + salary);
		System.out.println("education: " + education);
		System.out.println("hobbies: " + hobbiesStr);
		Connection con = null;
		PreparedStatement ps = null;
		try
		{
			String sql = new StringBuilder().append(" update t_ecp_user ").append(" set USERNAME = ? ,")
					.append(" PASSWORD = ? ,").append(" USEREMAIL = ? ,").append(" SEX = ? ,").append(" SALARY = ?,")
					.append(" EDUCATION = ?,").append(" HOBBIES = ? ").append(" where USERID = ? ").toString();
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(url, username, dataPassword);
			con.setAutoCommit(false);
			ps = con.prepareStatement(sql);
			ps.setString(1, userName);
			ps.setString(2, password);
			ps.setString(3, userEmail);
			ps.setString(4, sex);
			ps.setDouble(5, salary);
			ps.setString(6, education);
			ps.setString(7, hobbiesStr);
			ps.setString(8, userId);
			ps.executeUpdate();
			con.commit();

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

		}
		PrintWriter writer = response.getWriter();
		writer.print("<h1 font-color='red'>恭喜" + userName + "修改成功</h1>");

		response.sendRedirect(request.getContextPath() + "/queryUser.do");
	}

	public void deleteUser(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		String userId = request.getParameter("userId");
		System.out.println(userId);
		Connection con = null;
		PreparedStatement ps = null;

		try
		{
			String url = "jdbc:mysql://127.0.0.1:3306/webapp?useUnicode=true&characterEncoding=utf8";
			String userName = "root";
			String password = "";
			String sql = new StringBuffer().append("delete ").append(" from ").append(" t_ecp_user ")
					.append(" where USERID = ").append(" ? ").toString();
			System.out.println(sql);
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(url, userName, password);
			con.setAutoCommit(false);
			ps = con.prepareStatement(sql);
			ps.setString(1, userId);
			ps.executeUpdate();
			con.commit();
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
			closeConnection(null, ps, con);
		}

		response.sendRedirect(request.getContextPath() + "/queryUser.do");

	}

	public void queryUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		PrintWriter out = response.getWriter();
		List<UserInfo> userInfos = new ArrayList<UserInfo>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try
		{
			String url = "jdbc:mysql://127.0.0.1:3306/webapp?useUnicode=true&characterEncoding=utf8";
			String userName = "root";
			String password = "";
			String sql = new StringBuffer().append("select * ").append(" from ").append(" t_ecp_user ").toString();
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(url, userName, password);
			con.setAutoCommit(false);
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next())
			{
				UserInfo info = new UserInfo();
				String userId = rs.getString("USERID");
				String username = rs.getString("USERNAME");
				double salary = rs.getDouble("SALARY");
				String userEmail = rs.getString("USEREMAIL");
				String sex = rs.getString("SEX");
				String education = rs.getString("EDUCATION");
				String hobbies = rs.getString("HOBBIES");
				info.setUserId(userId);
				info.setUsername(username);
				info.setUserEmail(userEmail);
				info.setEducation(education);
				info.setSalary(salary);
				info.setSex(sex);
				info.setHobbies(hobbies);
				userInfos.add(info);

			}
			request.setAttribute("userList", userInfos);
			request.getRequestDispatcher("/jsp/user/QueryUser.jsp").forward(request, response);
			con.commit();
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
			closeConnection(rs, ps, con);
		}
	}

	private void closeConnection(ResultSet rs, PreparedStatement ps, Connection con)
	{
		if (rs != null)
		{
			try
			{
				rs.close();
			}
			catch (SQLException e)
			{
				e.printStackTrace();
			}
		}
		if (ps != null)
		{
			try
			{
				ps.close();
			}
			catch (SQLException e)
			{
				e.printStackTrace();
			}
		}
		if (con != null)
		{
			try
			{
				con.close();
			}
			catch (SQLException e)
			{
				e.printStackTrace();
			}
		}
	}
}
