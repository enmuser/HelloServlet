/*
 * 文件名：MySimpleTag.java
 * 版权：Enmuser Technologies Co.,Ltd. Copyright 2016-2017
 * 描述：<描述>
 * 修改人：Administrator
 * 修改时间：2017年6月11日
 * 修改单号：<修改单号>
 * 修改内容：<修改内容>
 *
 */
package self.defined.tag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

/**
 * <一句话功能描述> <功能详细描述>
 * 
 * @author 朱洪昌
 * @date 2017年6月11日
 * @version 1.0
 */
public class MySimpleTag extends SimpleTagSupport
{
	private String info;
	
	private int num;
	
	
	/**
	 *获取 info
	 *
	 * @return 返回  info
	 */
	public String getInfo()
	{
		return info;
	}


	/**
	 * 设置  info
	 *
	 * @param info 对 info 进行赋值
	 */
	public void setInfo(String info)
	{
		this.info = info;
	}


	/**
	 *获取 num
	 *
	 * @return 返回  num
	 */
	public int getNum()
	{
		return num;
	}


	/**
	 * 设置  num
	 *
	 * @param num 对 num 进行赋值
	 */
	public void setNum(int num)
	{
		this.num = num;
	}


	@Override
	public void doTag() throws JspException, IOException
	{
		PageContext pageContext = (PageContext) getJspContext();
		
		JspWriter out = pageContext.getOut();
		
		for (int i = 0; i < num; i++)
		{
			out.println(info);
		}
	}
}
