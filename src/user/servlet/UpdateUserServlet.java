package user.servlet;

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

public class UpdateUserServlet extends HttpServlet
{
	@Override
	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		String userId = request.getParameter("userId");
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try
		{
			String url = "jdbc:mysql://127.0.0.1:3306/webapp?useUnicode=true&characterEncoding=utf8";
			String userName = "root";
			String password = "";
			String sql = new StringBuffer()
					         .append("select * ")
					         .append(" from ")
					         .append(" t_ecp_user ")
					         .append(" where USERID = ")
					         .append(" ? ")
					         .toString();
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(url, userName, password);
			con.setAutoCommit(false);
			ps = con.prepareStatement(sql);
			ps.setString(1, userId);
			rs = ps.executeQuery();
			out.println("<!DOCTYPE html>");
			out.println("<html>");
			out.println("  <head>");
			out.println("    <title>ModifyUser.html</title>");
			out.println("    <meta http-equiv='content-type' content='text/html; charset=UTF-8'>");
			out.println("  </head>");
			out.println("  <body>");
			
			
			while (rs.next())
			{
				String userID = rs.getString("USERID");
				out.println("    <form action='/HelloServlet/ModifyUser?userId="+userID+"' method='post'>");
				String passwords = rs.getString("PASSWORD");
				String username = rs.getString("USERNAME");
				double salary = rs.getDouble("SALARY");
				String userEmail = rs.getString("USEREMAIL");
				String sex = rs.getString("SEX");
				String education = rs.getString("EDUCATION");
				String hobbies = rs.getString("HOBBIES");
				String[] hB = hobbies.substring(1,hobbies.length()-1).split(",");
				out.println("       name:<input type='text' name='userName' value="+username+"><br/>");
				out.println("       password:<input type='text' name='password' value="+passwords+"><br/>");
				out.println("       email:<input type='email' name='userEmail' value="+userEmail+"><br/>");
				out.println("       salary:<input type='text' name='salary' value="+salary+"><br/>");
				out.println("       sex:<input type='radio' name='sex' value='男'"+getChecked("男",sex)+">男");
				out.println("           <input type='radio' name='sex' value='女'"+getChecked("女",sex)+">女<br/>");
				out.println("       education:<select name='education'>");
				out.println("                  <option value='0'>---请选择---</option>");
				out.println("                  <option value='高中'"+getSelected("高中",education)+">高中</option>");
				out.println("                  <option value='专科'"+getSelected("专科",education)+">专科</option>");
				out.println("                  <option value='本科'"+getSelected("本科",education)+">本科</option>");
				out.println("                  </select><br/>");
				out.println("       hobbies:<input type='checkbox' name='hobby' value='打篮球'"+getChecked("打篮球",hB)+"/>打篮球");
				out.println("               <input type='checkbox' name='hobby' value='看电影'"+getChecked("看电影",hB)+"/>看电影");
				out.println("               <input type='checkbox' name='hobby' value='旅行'"+getChecked("旅行",hB)+"/>旅行<br/>");
				out.println("       <input type='submit' value='ModifyUser'/>  ");
				out.println("    </form>");

			}
			
			out.println("  </body>");
			out.println("</html>");

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

	private String getChecked(String value, String[] hB)
	{
		if(Arrays.toString(hB).contains(value))
		{
			return "checked='checked'";
		}
		return "";
	}

	private String getSelected(String value, String education)
	{
		if(value.equals(education))
		{
			return "selected='selected'";
		}
		return "";
	}

	private String getChecked(String value, String sex)
	{
		if(value.equals(sex))
		{
			return "checked='checked'";
		}
		return "";
	}
	
}
