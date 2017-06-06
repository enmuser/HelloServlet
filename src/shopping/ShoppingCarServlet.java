/*
 * 文件名：ShoppingCarServlet.java
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
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.common.collect.Maps;
import com.mysql.jdbc.Statement;

import shopping.utils.ShopUtils;

/**
 * <一句话功能描述> <功能详细描述>
 * 
 * @author 朱洪昌
 * @date 2017年6月3日
 * @version 1.0
 */
public class ShoppingCarServlet extends HttpServlet
{

	@Override
	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		String urlName = request.getServletPath().substring(1, request.getServletPath().lastIndexOf("."));
		if ("addShoppingCart".equals(urlName))
		{
			addShopingCart(request, response);
		}
		if ("showShoppingCart".equals(urlName))
		{
			showShoppingCart(request, response);
		}
		if ("ClearCart".equals(urlName))
		{
			clearCart(request, response);
		}
		if ("ClearCartItemByID".equals(urlName))
		{
			clearCartItemByID(request, response);
		}
		if ("ClearCartItemByIDs".equals(urlName))
		{
			clearCartItemByIDs(request, response);
		}
		if ("addNum".equals(urlName))
		{
			addNum(request, response);
		}
		if ("reduceNum".equals(urlName))
		{
			reduceNum(request, response);
		}
		if ("Order".equals(urlName))
		{
			order(request, response);
		}
		if ("submitOrder".equals(urlName))
		{
			submitOrder(request, response);
		}
	}

	/**
	 * <一句话描述方法> <功能详细描述>
	 * 
	 * @param request
	 * @param response
	 * @return void
	 * @author: 朱洪昌
	 * @date: 2017年6月4日 上午2:03:35
	 */
	private void submitOrder(HttpServletRequest request, HttpServletResponse response)
	{
		//获取初始数据
		String ids = request.getParameter("ids");
		System.out.println("submitOrder:"+ids);
		ids = ids.substring(0, ids.length());
		String[] idss = ids.split(",");
		String username = (String) request.getSession().getAttribute("userName");
		String passwd = (String) request.getSession().getAttribute("password");
		ShoppingCartVO cartVO = (ShoppingCartVO) request.getSession().getAttribute("Cart");
		//生成订单号
		String orderNo = new SimpleDateFormat("yyyMMddHHmmss").format(new Date());
		//获取用户信息
		User user = new User();
		int primaryKey = 0;
		//计算总价钱
		Map<Integer, Item> map = cartVO.getItems();
		Set<Integer> keys = map.keySet();
		double totalPrice = 0.0;
		for (Integer key : keys)
		{
			if(ids.contains(String.valueOf(key)))
			{
				Item item = map.get(key);
				totalPrice += item.getNum() * item.getProduct().getPrice();
			}
		}
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try
		{
			String url = "jdbc:mysql://127.0.0.1:3306/webapp?useUnicode=true&characterEncoding=utf8";
			String userName = "root";
			String password = "";
			String sql = new StringBuffer().append("select * ").append(" from ").append(" t_user ")
					.append(" where username = ? ").append(" and password = ? ").toString();
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(url, userName, password);
			con.setAutoCommit(false);
			ps = con.prepareStatement(sql);
			ps.setString(1, username);
			ps.setString(2, passwd);
			rs = ps.executeQuery();
			if (rs.next())
			{

				user.setUsername(rs.getString("username"));
				user.setUseremail(rs.getString("useremail"));
				user.setPhone(rs.getString("phone"));
				user.setAddress(rs.getString("address"));
				user.setId(rs.getInt("id"));
				user.setPassword(rs.getString("password"));
				con.commit();
			}

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

		Connection con1 = null;
		PreparedStatement ps1 = null;
		ResultSet rs1 = null;
		try
		{
			String url = "jdbc:mysql://127.0.0.1:3306/webapp?useUnicode=true&characterEncoding=utf8";
			String userName = "root";
			String password = "";
			String sql = new StringBuilder().append(" insert into t_order ").append("(user_id,orderNo,totalPrice) ")
					.append("values (?,?,?)").toString();
			Class.forName("com.mysql.jdbc.Driver");
			con1 = DriverManager.getConnection(url, userName, password);
			con1.setAutoCommit(false);
			ps1 = con1.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			ps1.setInt(1, user.getId());
			ps1.setString(2, orderNo);
			ps1.setDouble(3, totalPrice);
			ps1.executeUpdate();
			rs1 = ps1.getGeneratedKeys();
			if (rs1.next())
			{
				primaryKey = rs1.getInt(1);
			}
			con1.commit();
		}
		catch (Exception e)
		{
			try
			{
				con1.rollback();
			}
			catch (SQLException e1)
			{
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally
		{
			ShopUtils.closeConnection(rs1, ps1, con1);
		}

		for (Integer key : keys)
		{
			if(ids.contains(String.valueOf(key)))
			{
				Connection con2 = null;
				PreparedStatement ps2 = null;
				ResultSet rs2 = null;
				try
				{
					String url = "jdbc:mysql://127.0.0.1:3306/webapp?useUnicode=true&characterEncoding=utf8";
					String userName = "root";
					String password = "";
					String sql = new StringBuilder().append(" insert into t_item ")
							.append("(product_id,num,order_id,itemPrice) ").append("values (?,?,?,?)").toString();
					Class.forName("com.mysql.jdbc.Driver");
					con2 = DriverManager.getConnection(url, userName, password);
					con2.setAutoCommit(false);
					ps2 = con2.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
					ps2.setInt(1, key);
					ps2.setInt(2, map.get(key).getNum());
					ps2.setDouble(3, primaryKey);
					ps2.setDouble(4, map.get(key).getNum() * map.get(key).getProduct().getPrice());
					ps2.executeUpdate();
					con2.commit();
				}
				catch (Exception e)
				{
					try
					{
						con2.rollback();
					}
					catch (SQLException e1)
					{
						e1.printStackTrace();
					}
					e.printStackTrace();
				} finally
				{
					ShopUtils.closeConnection(rs2, ps2, con2);
				}
			}
		}

//		request.setAttribute("orderNo", orderNo);
		//塞入订单号
		request.getSession().setAttribute("orderNo", orderNo);
		//清已结算的购物车内容
		for (int i = 0; i < idss.length; i++)
		{
			map.remove(Integer.parseInt(idss[i]));
		}
		
//		try
//		{
//			request.getRequestDispatcher("/jsp/shop/OrderSuccess.jsp").forward(request, response);
//		}
//		catch (ServletException e)
//		{
//			e.printStackTrace();
//		}
//		catch (IOException e)
//		{
//			e.printStackTrace();
//		}
		try
		{
			response.sendRedirect(request.getContextPath()+"/jsp/shop/OrderSuccess.jsp");
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * <一句话描述方法> <功能详细描述>
	 * 
	 * @param request
	 * @param response
	 * @return void
	 * @author: 朱洪昌
	 * @date: 2017年6月4日 上午1:15:28
	 */
	private void order(HttpServletRequest request, HttpServletResponse response)
	{
		String ids = request.getParameter("ids");
		ids = ids.substring(0, ids.length() - 1);
		String username = (String) request.getSession().getAttribute("userName");
		String passwd = (String) request.getSession().getAttribute("password");
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try
		{
			String url = "jdbc:mysql://127.0.0.1:3306/webapp?useUnicode=true&characterEncoding=utf8";
			String userName = "root";
			String password = "";
			String sql = new StringBuffer().append("select * ").append(" from ").append(" t_user ")
					.append(" where username = ? ").append(" and password = ? ").toString();
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(url, userName, password);
			con.setAutoCommit(false);
			ps = con.prepareStatement(sql);
			ps.setString(1, username);
			ps.setString(2, passwd);
			rs = ps.executeQuery();
			if (rs.next())
			{
				User user = new User();
				user.setUsername(rs.getString("username"));
				user.setUseremail(rs.getString("useremail"));
				user.setPhone(rs.getString("phone"));
				user.setAddress(rs.getString("address"));
				user.setId(rs.getInt("id"));
				user.setPassword(rs.getString("password"));
				request.setAttribute("user", user);
				System.out.println("order:"+ids);
				request.setAttribute("ids", ids);
				request.getRequestDispatcher("/jsp/shop/Order.jsp").forward(request, response);
				con.commit();
			}

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
	 * @date: 2017年6月4日 上午12:55:24
	 */
	private void reduceNum(HttpServletRequest request, HttpServletResponse response)
	{
		String productId = request.getParameter("productId");
		ShoppingCartVO cartVO = (ShoppingCartVO) request.getSession().getAttribute("Cart");
		Map<Integer, Item> map = cartVO.getItems();
		Item item = map.get(Integer.parseInt(productId));
		int num = item.getNum() - 1;
		if (0 == num)
		{
			map.remove(Integer.parseInt(productId));
		}
		item.setNum(num);
		try
		{
			request.getRequestDispatcher("/jsp/shop/ShoppingCart.jsp").forward(request, response);
		}
		catch (ServletException e)
		{
			e.printStackTrace();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * <一句话描述方法> <功能详细描述>
	 * 
	 * @param request
	 * @param response
	 * @return void
	 * @author: 朱洪昌
	 * @date: 2017年6月4日 上午12:54:59
	 */
	private void addNum(HttpServletRequest request, HttpServletResponse response)
	{
		String productId = request.getParameter("productId");
		ShoppingCartVO cartVO = (ShoppingCartVO) request.getSession().getAttribute("Cart");
		Map<Integer, Item> map = cartVO.getItems();
		Item item = map.get(Integer.parseInt(productId));
		item.setNum(item.getNum() + 1);
		try
		{
			request.getRequestDispatcher("/jsp/shop/ShoppingCart.jsp").forward(request, response);
		}
		catch (ServletException e)
		{
			e.printStackTrace();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * <一句话描述方法> <功能详细描述>
	 * 
	 * @param request
	 * @param response
	 * @return void
	 * @author: 朱洪昌
	 * @date: 2017年6月4日 上午12:13:23
	 */
	private void clearCartItemByIDs(HttpServletRequest request, HttpServletResponse response)
	{
		String ids = request.getParameter("ids");
		ShoppingCartVO cartVO = (ShoppingCartVO) request.getSession().getAttribute("Cart");
		Map<Integer, Item> map = cartVO.getItems();
		ids = ids.substring(0, ids.length() - 1);
		String[] idss = ids.split(",");
		for (String id : idss)
		{
			map.remove(Integer.parseInt(id));
		}
		try
		{
			request.getRequestDispatcher("/jsp/shop/ShoppingCart.jsp").forward(request, response);
		}
		catch (ServletException e)
		{
			e.printStackTrace();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * <一句话描述方法> <功能详细描述>
	 * 
	 * @param request
	 * @param response
	 * @return void
	 * @author: 朱洪昌
	 * @date: 2017年6月3日 下午11:32:17
	 */
	private void clearCartItemByID(HttpServletRequest request, HttpServletResponse response)
	{
		String productId = request.getParameter("productId");
		ShoppingCartVO cartVO = (ShoppingCartVO) request.getSession().getAttribute("Cart");
		Map<Integer, Item> map = cartVO.getItems();
		map.remove(Integer.parseInt(productId));
		try
		{
			request.getRequestDispatcher("/jsp/shop/ShoppingCart.jsp").forward(request, response);
		}
		catch (ServletException e)
		{
			e.printStackTrace();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * <一句话描述方法> <功能详细描述>
	 * 
	 * @param request
	 * @param response
	 * @return void
	 * @author: 朱洪昌
	 * @date: 2017年6月3日 下午11:26:00
	 */
	private void clearCart(HttpServletRequest request, HttpServletResponse response)
	{
		request.getSession().removeAttribute("Cart");
		try
		{
			request.getRequestDispatcher("/jsp/shop/ShoppingCart.jsp").forward(request, response);
		}
		catch (ServletException e)
		{
			e.printStackTrace();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * <一句话描述方法> <功能详细描述>
	 * 
	 * @param request
	 * @param response
	 * @return void
	 * @author: 朱洪昌
	 * @date: 2017年6月3日 下午12:24:00
	 */
	private void showShoppingCart(HttpServletRequest request, HttpServletResponse response)
	{

	}

	/**
	 * <一句话描述方法> <功能详细描述>
	 * 
	 * @param request
	 * @param response
	 * @return void
	 * @author: 朱洪昌
	 * @date: 2017年6月3日 上午11:45:19
	 */
	private void addShopingCart(HttpServletRequest request, HttpServletResponse response)
	{
		String username = (String) request.getSession().getAttribute("userName");
		if (null == username)
		{
			request.setAttribute("message", "你还没登录,不能加入购物车");
			try
			{
				request.getRequestDispatcher("/OfferingList.product").forward(request, response);
			}
			catch (ServletException e)
			{
				e.printStackTrace();
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
			return;
		}
		String productId = request.getParameter("productId");
		if (null != request.getSession().getAttribute("Cart"))
		{
			ShoppingCartVO vo = (ShoppingCartVO) request.getSession().getAttribute("Cart");
			Set<Integer> keys = vo.getItems().keySet();
			for (Integer key : keys)
			{
				if (key == Integer.parseInt(productId))
				{
					int num = vo.getItems().get(key).getNum();
					vo.getItems().get(key).setNum(num + 1);
					try
					{
						request.getRequestDispatcher("/OfferingList.product").forward(request, response);
					}
					catch (ServletException e)
					{
						e.printStackTrace();
					}
					catch (IOException e)
					{
						e.printStackTrace();
					}
					return;
				}
			}
		}
		ShoppingCartVO cartVO = null;
		if (null == (ShoppingCartVO) request.getSession().getAttribute("Cart"))
		{
			cartVO = new ShoppingCartVO();
			Map<Integer, Item> map = new HashMap<Integer, Item>();
			cartVO.setItems(map);
		} else
		{
			cartVO = (ShoppingCartVO) request.getSession().getAttribute("Cart");
		}
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try
		{
			String url = "jdbc:mysql://127.0.0.1:3306/webapp?useUnicode=true&characterEncoding=utf8";
			String userName = "root";
			String password = "";
			String sql = new StringBuffer().append("select * ").append(" from ").append(" t_product ")
					.append(" where id = ? ").toString();
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(url, userName, password);
			con.setAutoCommit(false);
			ps = con.prepareStatement(sql);
			ps.setInt(1, Integer.parseInt(productId));
			rs = ps.executeQuery();
			if (rs.next())
			{
				Product product = new Product();
				int proId = rs.getInt("id");
				String productname = rs.getString("name");
				Double productprice = rs.getDouble("price");
				String description = rs.getString("description");
				product.setId(proId);
				product.setPrice(productprice);
				product.setDescription(description);
				product.setName(productname);
				Item item = new Item();
				item.setNum(1);
				item.setProduct(product);
				cartVO.getItems().put(proId, item);
			}
			request.getSession().setAttribute("Cart", cartVO);
			request.getRequestDispatcher("/OfferingList.product").forward(request, response);
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
