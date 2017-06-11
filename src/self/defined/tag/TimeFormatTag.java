/*
 * 文件名：TimeFormatTag.java 版权：Enmuser Technologies Co.,Ltd. Copyright 2016-2017
 * 描述：<描述> 修改人：Administrator 修改时间：2017年6月11日 修改单号：<修改单号> 修改内容：<修改内容>
 *
 */
package self.defined.tag;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
public class TimeFormatTag extends SimpleTagSupport
{
	private String srcTime;

	private String srcFormat;

	private String targetFormat;

	/**
	 * 获取 srcTime
	 *
	 * @return 返回 srcTime
	 */
	public String getSrcTime()
	{
		return srcTime;
	}

	/**
	 * 设置 srcTime
	 *
	 * @param srcTime 对 srcTime 进行赋值
	 */
	public void setSrcTime(String srcTime)
	{
		this.srcTime = srcTime;
	}

	/**
	 * 获取 srcFormat
	 *
	 * @return 返回 srcFormat
	 */
	public String getSrcFormat()
	{
		return srcFormat;
	}

	/**
	 * 设置 srcFormat
	 *
	 * @param srcFormat 对 srcFormat 进行赋值
	 */
	public void setSrcFormat(String srcFormat)
	{
		this.srcFormat = srcFormat;
	}

	/**
	 * 获取 targetFormat
	 *
	 * @return 返回 targetFormat
	 */
	public String getTargetFormat()
	{
		return targetFormat;
	}

	/**
	 * 设置 targetFormat
	 *
	 * @param targetFormat 对 targetFormat 进行赋值
	 */
	public void setTargetFormat(String targetFormat)
	{
		this.targetFormat = targetFormat;
	}

	@Override
	public void doTag() throws JspException, IOException
	{
		PageContext pageContext = (PageContext) getJspContext();

		JspWriter out = pageContext.getOut();
		
		SimpleDateFormat format = new SimpleDateFormat(srcFormat);
		String targetTime = null;
		try
		{
			Date date = format.parse(srcTime);
			
			targetTime = new SimpleDateFormat(targetFormat).format(date);
			
		}
		catch (ParseException e)
		{
			e.printStackTrace();
		}
		out.println(targetTime);
	}
}
