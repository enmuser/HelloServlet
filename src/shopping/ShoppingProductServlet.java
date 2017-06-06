/*
 * 文件名：ShoppingProductServlet.java
 * 版权：Enmuser Technologies Co.,Ltd. Copyright 2016-2017
 * 描述：<描述>
 * 修改人：Administrator
 * 修改时间：2017年6月3日
 * 修改单号：<修改单号>
 * 修改内容：<修改内容>
 *
 */
package shopping;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import shopping.utils.ShopUtils;
import user.servlet.together.UserInfo;

/**
 * <一句话功能描述> <功能详细描述>
 * 
 * @author 朱洪昌
 * @date 2017年6月3日
 * @version 1.0
 */
public class ShoppingProductServlet extends HttpServlet
{

	@Override
	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		String urlName = request.getServletPath().substring(1,request.getServletPath().lastIndexOf("."));
		System.out.println(urlName);
		if("OfferingList".equals(urlName))
		{
			OfferingList(request,response);
		}
//		if("login".equals(urlName))
//		{
//			login(request,response);
//		}
	}

	/** 
	  * <一句话描述方法>
	  * <功能详细描述>
	  * @param request
	  * @param response 
	  * @return void 
	  * @author: 朱洪昌
	  * @date: 2017年6月3日 上午11:09:00
	  */ 
	private void OfferingList(HttpServletRequest request, HttpServletResponse response)
	{
		List<Product> productInfos = new ArrayList<Product>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try
		{
			String url = "jdbc:mysql://127.0.0.1:3306/webapp?useUnicode=true&characterEncoding=utf8";
			String userName = "root";
			String password = "";
			String sql = new StringBuffer().append("select * ").append(" from ").append(" t_product ").toString();
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(url, userName, password);
			con.setAutoCommit(false);
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next())
			{
				Product info = new Product();
				int productId = rs.getInt("id");
				String productname = rs.getString("name");
				Double productprice = rs.getDouble("price");
				String description = rs.getString("description");
				info.setId(productId);
				info.setPrice(productprice);
				info.setName(productname);
				info.setDescription(description);
				productInfos.add(info);

			}
			request.setAttribute("productList", productInfos);
			request.getRequestDispatcher("/jsp/shop/OfferingList.jsp").forward(request, response);
			con.commit();
		}
		catch (Exception e)
		{
			try
			{
				con.rollback();
			}
			catch (SQLException e1)
			{
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally
		{
			ShopUtils.closeConnection(rs, ps, con);
		}
	}
}
