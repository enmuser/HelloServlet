/*
 * 文件名：MD5Encrypt.java
 * 版权：Enmuser Technologies Co.,Ltd. Copyright 2016-2017
 * 描述：<描述>
 * 修改人：Administrator
 * 修改时间：2017年6月4日
 * 修改单号：<修改单号>
 * 修改内容：<修改内容>
 *
 */
package shopping.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import sun.misc.BASE64Encoder;

/**
 * <一句话功能描述>
 * <功能详细描述>
 * @author 朱洪昌
 * @date 2017年6月4日
 * @version 1.0
 */
public class MD5Encrypt
{
    public static String encrypt(String src)
    {
    	try
		{
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			byte[] srcByte = md5.digest(src.getBytes());
			BASE64Encoder encoder = new BASE64Encoder();
			return encoder.encode(srcByte);
 		}
		catch (NoSuchAlgorithmException e)
		{
			e.printStackTrace();
		}
    	return src;
    }
}
