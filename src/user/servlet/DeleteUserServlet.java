package user.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteUserServlet extends HttpServlet
{
	@Override
	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		String userId = request.getParameter("userId");
		
    	Connection con = null;
    	PreparedStatement ps = null;
    	
    	try
		{
    		String url = "jdbc:mysql://127.0.0.1:3306/webapp?useUnicode=true&characterEncoding=utf8";
    		String userName = "root";
    		String password = "";
    		String sql = new StringBuffer()
    				        .append("delete ")
    				        .append(" from ")
    				        .append(" t_ecp_user ")
    				        .append(" where USERID = ")
    				        .append(" ? ")
    				        .toString();
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(url,userName,password);
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
    	
    	response.setCharacterEncoding("utf-8");
    	response.sendRedirect("/WebApp/QueryUser");
    }
}
