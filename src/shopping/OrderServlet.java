/*
 * 文件名：OrderServlet.java
 * 版权：Enmuser Technologies Co.,Ltd. Copyright 2016-2017
 * 描述：<描述>
 * 修改人：Administrator
 * 修改时间：2017年6月4日
 * 修改单号：<修改单号>
 * 修改内容：<修改内容>
 *
 */
package shopping;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.common.collect.Lists;

import shopping.utils.ShopUtils;

/**
 * <一句话功能描述> <功能详细描述>
 * 
 * @author 朱洪昌
 * @date 2017年6月4日
 * @version 1.0
 */
public class OrderServlet extends HttpServlet
{
	@Override
	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		String urlName = request.getServletPath().substring(1, request.getServletPath().lastIndexOf("."));
		System.out.println(urlName);
		if ("myOrder".equals(urlName))
		{
			myOrder(request, response);
		}
		if ("OrderDetail".equals(urlName))
		{
			OrderDetail(request, response);
		}
	}

	/**
	 * <一句话描述方法> <功能详细描述>
	 * 
	 * @param request
	 * @param response
	 * @return void
	 * @author: 朱洪昌
	 * @date: 2017年6月6日 下午10:03:06
	 */
	private void OrderDetail(HttpServletRequest request, HttpServletResponse response)
	{
		String orderNo = request.getParameter("orderNo");
		List<OrderDetailVO> orderDetails = Lists.newArrayList();
		User user = (User) request.getSession().getAttribute("user");
		// 从数据库查询订单信息
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		String url = "jdbc:mysql://127.0.0.1:3306/webapp?useUnicode=true&characterEncoding=utf8";
		String username = "root";
		String password = "";

		try
		{
			String sql = new StringBuffer()
					.append(" select us.username username,a.orderNo orderNo,it.id itemId, ")
					.append(" pt.name productName,it.num num,it.itemPrice itemprice, ")
					.append(" a.count count,a.totalPrice totalprice ")
					.append(" from ")
					.append(" ( select id,orderNo,user_id,totalPrice, ")
					.append(" (select count(1) from t_item i where i.order_id = o.id ) count ")
					.append(" from t_order o) a  ")
                    .append(" INNER JOIN t_item it on a.id = it.order_id ")
                    .append(" INNER JOIN t_user us on a.user_id = us.id ")
                    .append(" INNER JOIN t_product pt on it.product_id = pt.id ")
                    .append(" where a.orderNo = ? ")
					.toString();
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(url, username, password);
			con.setAutoCommit(false);
			ps = con.prepareStatement(sql);
			ps.setString(1, orderNo);
			rs = ps.executeQuery();
			while (rs.next())
			{
				OrderDetailVO orderDetailVO = new OrderDetailVO();
				orderDetailVO.setUsername(rs.getString("username"));
				orderDetailVO.setOrderNo(rs.getString("orderNo"));
				orderDetailVO.setItemId(rs.getInt("itemId"));
				orderDetailVO.setProductName(rs.getString("productName"));
				orderDetailVO.setNum(rs.getInt("num"));
				orderDetailVO.setItemprice(rs.getDouble("itemprice"));
				orderDetailVO.setCount(rs.getInt("count"));
				orderDetailVO.setTotalprice(rs.getDouble("totalprice"));
				orderDetails.add(orderDetailVO);
				
			}
			con.commit();
			request.setAttribute("OrderDetailVO", orderDetails);
			request.getRequestDispatcher("/jsp/shop/OrderDetail.jsp").forward(request, response);

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

	/**
	 * <一句话描述方法> <功能详细描述>
	 * 
	 * @param request
	 * @param response
	 * @return void
	 * @author: 朱洪昌
	 * @date: 2017年6月4日 下午11:24:39
	 */
	private void myOrder(HttpServletRequest request, HttpServletResponse response)
	{
		List<Order> orders = Lists.newArrayList();
		User user = (User) request.getSession().getAttribute("user");
		// 从数据库查询订单信息
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		String url = "jdbc:mysql://127.0.0.1:3306/webapp?useUnicode=true&characterEncoding=utf8";
		String username = "root";
		String password = "";

		try
		{
			String sql = new StringBuffer().append(" select * from ").append(" t_order ").append(" where user_id = ? ")
					.toString();
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(url, username, password);
			con.setAutoCommit(false);
			ps = con.prepareStatement(sql);
			ps.setInt(1, user.getId());
			rs = ps.executeQuery();
			while (rs.next())
			{
				Order order = new Order();
				order.setId(rs.getInt("id"));
				order.setOrderNo(rs.getString("orderNo"));
				order.setTotalPrice(rs.getDouble("totalPrice"));
				orders.add(order);
			}
			con.commit();
			request.setAttribute("orderList", orders);
			request.getRequestDispatcher("/jsp/shop/MyOrder.jsp").forward(request, response);

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
