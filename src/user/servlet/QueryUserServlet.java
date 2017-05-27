package user.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class QueryUserServlet extends HttpServlet
{
    @Override
    public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
    	response.setCharacterEncoding("utf-8");
    	PrintWriter out = response.getWriter();
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
    				        .toString();
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(url,userName,password);
			con.setAutoCommit(false);
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			out.println("<!DOCTYPE html>");
			out.println("<html>");
			out.println(  "<head>");
			out.println(    "<title>QueryAllUser.html</title>");
			out.println(    "<meta name=\"content-type\" content=\"text/html; charset=UTF-8\">");
			out.println(  "</head>");
			out.println(  "<body>");
			out.println(    "<table border=\"1px\">");
			out.println(       "<thead>");
			out.println(         "<tr>");
			out.println(           "<th>用户ID</th>");
			out.println(           "<th>用户名</th>");
			out.println(           "<th>用户邮箱</th>");
			out.println(           "<th>性别</th>");
			out.println(           "<th>薪水</th>");
			out.println(           "<th>教育水平</th>");
			out.println(           "<th>兴趣爱好</th>");
			out.println(           "<th>操作</th>");
			out.println(         "</tr>");
			out.println(       "</thead>");
			out.println(      "<tbody>");
			while(rs.next())
			{
				String userId = rs.getString("USERID");
				String username = rs.getString("USERNAME");
				double salary = rs.getDouble("SALARY");
				String userEmail = rs.getString("USEREMAIL");
				String sex = rs.getString("SEX");
				String education = rs.getString("EDUCATION");
				String hobbies = rs.getString("HOBBIES");
				
				out.println(          "<tr>");
				out.println(           "<td>"+userId+"</td>");
				out.println(           "<td>"+username+"</td>");
				out.println(           "<td>"+userEmail+"</td>");
				out.println(           "<td>"+sex+"</td>");
				out.println(           "<td>"+salary+"</td>");
				out.println(           "<td>"+education+"</td>");
				out.println(           "<td>"+hobbies+"</td>");
				out.println(           "<td><a href='DeleteUser?userId="+userId+"'>删除</a><a href=\"javascript:void(0)\" >修改</a></td>");
				out.println(          "</tr>");
			}
			out.println(       "</tbody>");
			out.println(    "</table>");
			out.println(  "</body>");
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
		}
    	finally
    	{
    		if(rs!=null)
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
    		if(ps!=null)
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
    		if(con!=null)
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
}
