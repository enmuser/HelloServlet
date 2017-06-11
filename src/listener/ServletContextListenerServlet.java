/*
 * 文件名：ServletContextListenerServlet.java
 * 版权：Enmuser Technologies Co.,Ltd. Copyright 2016-2017
 * 描述：<描述>
 * 修改人：Administrator
 * 修改时间：2017年6月10日
 * 修改单号：<修改单号>
 * 修改内容：<修改内容>
 *
 */
package listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * <一句话功能描述>
 * <功能详细描述>
 * @author 朱洪昌
 * @date 2017年6月10日
 * @version 1.0
 */
public class ServletContextListenerServlet implements ServletContextListener
{

	@Override
	public void contextInitialized(ServletContextEvent event)
	{
		System.out.println("&&&&&&&&&##############&&&&&&&&&&&&");
		System.out.println("-------Server START----------------");
		System.out.println("&&&&&&&&&##############&&&&&&&&&&&&");
	}

	@Override
	public void contextDestroyed(ServletContextEvent event)
	{
		
	}
    
}
