/*
 * 文件名：FunctionUtils.java
 * 版权：Enmuser Technologies Co.,Ltd. Copyright 2016-2017
 * 描述：<描述>
 * 修改人：Administrator
 * 修改时间：2017年6月11日
 * 修改单号：<修改单号>
 * 修改内容：<修改内容>
 *
 */
package jsp.el.function;

import java.util.Arrays;

/**
 * <一句话功能描述>
 * <功能详细描述>
 * @author 朱洪昌
 * @date 2017年6月11日
 * @version 1.0
 */
public class FunctionUtils
{
	public static String getCheckedHB(String value, String hB)
	{
		String[] hbs = hB.substring(1,hB.length()-1).split(",");
		if(Arrays.toString(hbs).contains(value))
		{
			return "checked='checked'";
		}
		return "";
	}

	public static String getSelected(String value, String education)
	{
		if(value.equals(education))
		{
			return "selected='selected'";
		}
		return "";
	}

	public static String getChecked(String value, String sex)
	{
		if(value.equals(sex))
		{
			return "checked='checked'";
		}
		return "";
	}
}
