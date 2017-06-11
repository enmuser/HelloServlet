/*
 * 文件名：OnlineUserListener.java
 * 版权：Enmuser Technologies Co.,Ltd. Copyright 2016-2017
 * 描述：<描述>
 * 修改人：Administrator
 * 修改时间：2017年6月10日
 * 修改单号：<修改单号>
 * 修改内容：<修改内容>
 *
 */
package listener;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * <一句话功能描述> <功能详细描述>
 * 
 * @author 朱洪昌
 * @date 2017年6月10日
 * @version 1.0
 */
public class OnlineUserListener implements HttpSessionListener
{

	private Integer count = 0;

	@Override
	public void sessionCreated(HttpSessionEvent event)
	{
		ServletContext application = event.getSession().getServletContext();

		synchronized (this)
		{
			application.setAttribute("onlineUser", ++count);
		}
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent event)
	{
		ServletContext application = event.getSession().getServletContext();

		synchronized (this)
		{
			application.setAttribute("onlineUser", --count);
		}
	}

}
