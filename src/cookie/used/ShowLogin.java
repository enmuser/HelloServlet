package cookie.used;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cookie.util.CookieUtils;

public class ShowLogin extends HttpServlet
{
    @Override
    public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
    	Map<String, String> cookiesMap = CookieUtils.getCookiesMap(request);
    	
    	String username = null;
    	String passwd = null;
    	if(null != cookiesMap)
    	{
    		username = cookiesMap.get("username");
    		passwd = cookiesMap.get("password");
    	}
    	
    	if(null == username || null == passwd)
    	{
    		request.getRequestDispatcher("/jsp/user/Login.jsp").forward(request, response);
    		return;
    	}
    	
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
					.append(" where USERNAME = ? ")
					.append(" and PASSWORD = ? ")
					.toString();
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(url, userName, password);
			con.setAutoCommit(false);
			ps = con.prepareStatement(sql);
			ps.setString(1, username);
			ps.setString(2, passwd);
			rs = ps.executeQuery();
			if (rs.next())
			{
				con.commit();
				CookieUtils.addCookie(response, "username", username);
				CookieUtils.addCookie(response, "password", passwd);
				response.sendRedirect(request.getContextPath()+"/queryUser.do");
                return;
			}
			CookieUtils.removeCookie(response, "username");
			CookieUtils.removeCookie(response, "password");
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
		} 
		finally
		{
			closeConnection(rs, ps, con);
		}
    
    }

	private void closeConnection(ResultSet rs, PreparedStatement ps, Connection con)
	{
		if(rs != null)
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
		
		if(ps != null)
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
		
		if(con != null)
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
