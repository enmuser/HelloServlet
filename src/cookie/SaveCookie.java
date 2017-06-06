package cookie;

import java.io.IOException;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SaveCookie extends HttpServlet
{
     @Override
    public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
    	 Cookie[] cookies = request.getCookies();
    	 Cookie cookie = new Cookie("username", "enmuser");
    	 Cookie cookie2 = new Cookie("password", "123456");
    	 cookie.setMaxAge(7*24*60*60);
    	 response.addCookie(cookie);
    	 response.addCookie(cookie2);
    	 for (Cookie cookie1 : cookies)
		{
			System.out.println(cookie1.getName()+"="+cookie1.getValue());
		}
    	 
    }
}
