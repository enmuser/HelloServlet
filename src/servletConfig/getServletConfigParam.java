package servletConfig;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class getServletConfigParam extends HttpServlet
{
     @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
    	 String str = getServletConfig().getInitParameter("key");
    	 System.out.println(str);
    }
}
