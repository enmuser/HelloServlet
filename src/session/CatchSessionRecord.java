package session;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class CatchSessionRecord extends HttpServlet
{
	@Override
	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		HttpSession session = request.getSession();
		
		Integer count = (Integer) session.getAttribute("count");
		
		if(null == count)
		{
			count=1;
		}
		else
		{
			count++;
		}
		
		session.setAttribute("count", count);
		
		request.getRequestDispatcher("/jsp/user/Login.jsp").forward(request, response);
	}
}
