/*
 * 文件名：FileUpload.java
 * 版权：Enmuser Technologies Co.,Ltd. Copyright 2016-2017
 * 描述：<描述>
 * 修改人：Administrator
 * 修改时间：2017年6月11日
 * 修改单号：<修改单号>
 * 修改内容：<修改内容>
 *
 */
package fileUpload;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.google.common.collect.Maps;

/**
 * <一句话功能描述> <功能详细描述>
 * 
 * @author 朱洪昌
 * @date 2017年6月11日
 * @version 1.0
 */
public class FileUpload extends HttpServlet
{
	/* (non-Javadoc)
	 * <p>Title:service</p> 
	 * <p>Description: </p> 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException 
	 * @see javax.servlet.http.HttpServlet#service(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse) 
	 */ 
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		// 获取(表单上传以enctype='multipart/form-data')文件上传的输入流并输出在console
		// InputStream inputStream = request.getInputStream();
		// byte[] b = new byte[28800];
		//
		// int i = inputStream.read(b);
		//
		// System.out.println(new String(b,0,i));

		// 由于解析输出流的内容比较复杂，所以借用第三方jar进行解析Commons-fileupload依赖Commons-io和Commons-lang

		// 提供解析文件的参数
		DiskFileItemFactory factory = new DiskFileItemFactory();

		// 获得解析工具
		ServletFileUpload fileUpload = new ServletFileUpload(factory);

		// 解析请求

		try
		{
			List<FileItem> items = fileUpload.parseRequest(request);

			// 因为返回的List的操作不便，将其转换成Map
			Map<String, FileItem> fileMap = Maps.newHashMap();

			for (FileItem fileItem : items)
			{
				// System.out.println(fileItem.getFieldName()+"&&&&");
				fileMap.put(fileItem.getFieldName(), fileItem);
			}

			/*
			 * System.out.println(fileMap.get("username"));
			 * System.out.println(fileMap.get("file"));
			 */

			// 获取文件名称
			System.out.println(fileMap.get("file").getName());
			// 获取文件类型
			System.out.println(fileMap.get("file").getContentType());
			// 文件大小
			System.out.println(fileMap.get("file").getSize());

		    //读取字节流转换成字符流输出到控制台
			BufferedReader reader = new BufferedReader(
					new InputStreamReader(fileMap.get("file").getInputStream(), "utf-8"));
			System.out.println(reader.readLine());
			
			
			//文件上传限制
			if(fileMap.get("file").getName().endsWith(".jsp"))
			{
				request.setAttribute("message", "不能上传.jsp文件");
				request.getRequestDispatcher("/jsp/fileUpload/fileUpload.jsp").forward(request, response);
				return;
			}
			
			String uploadDir = getServletContext().getRealPath("uploadResource");
			System.out.println(uploadDir);
			fileMap.get("file").write(new File(uploadDir,fileMap.get("file").getName()));
			
		}
		catch (FileUploadException e)
		{
			e.printStackTrace();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

	}
}
