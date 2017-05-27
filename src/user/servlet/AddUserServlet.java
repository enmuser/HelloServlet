package user.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



public class AddUserServlet extends HttpServlet
{
	
    @Override
    public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
    	request.setCharacterEncoding("utf-8");
    	response.setCharacterEncoding("utf-8");
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
    	System.out.println("username: "+userName);
    	System.out.println("password: "+password);
    	System.out.println("userEmail: "+userEmail);
    	System.out.println("sex: "+sex);
    	System.out.println("salay: "+salary);
    	System.out.println("education: "+education);
    	System.out.println("hobbies: "+ hobbiesStr);
    	Connection con = null;
    	PreparedStatement ps = null;
    	try
		{
    		String sql = new StringBuilder()
    				          .append(" insert into t_ecp_user ")
    				          .append("(USERNAME,PASSWORD,USEREMAIL,SEX,SALARY,EDUCATION,HOBBIES) ")
    				          .append("values (?,?,?,?,?,?,?)")
    				          .toString();
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(url, username, dataPassword);
			con.setAutoCommit(false);
			ps = con.prepareStatement(sql);
			ps.setString(1, userName);
			ps.setString(2, password);
			ps.setString(3, userEmail);
			ps.setString(4,sex);
			ps.setDouble(5, salary);
			ps.setString(6, education);
			ps.setString(7,hobbiesStr);
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
		}
    	finally
    	{
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
    	PrintWriter writer = response.getWriter();
    	writer.print("<h1 font-color='red'>恭喜"+userName+"注册成功</h1>");
    }
}
