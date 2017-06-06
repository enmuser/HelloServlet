package servlet.interfaceImpl;

import java.io.IOException;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class HelloServletInterface implements Servlet
{

	private ServletConfig config;
	
	@Override
	public void init(ServletConfig config) throws ServletException
	{
		this.config = config;
	}

	@Override
	public ServletConfig getServletConfig()
	{
		return config;
	}

	@Override
	public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException
	{
		
	}

	@Override
	public String getServletInfo()
	{
		return null;
	}

	@Override
	public void destroy()
	{
		
	}

}
