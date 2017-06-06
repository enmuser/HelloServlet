/*
 * 文件名：Order.java
 * 版权：Enmuser Technologies Co.,Ltd. Copyright 2016-2017
 * 描述：<描述>
 * 修改人：Administrator
 * 修改时间：2017年6月3日
 * 修改单号：<修改单号>
 * 修改内容：<修改内容>
 *
 */
package shopping;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * <一句话功能描述> <功能详细描述>
 * 
 * @author 朱洪昌
 * @date 2017年6月3日
 * @version 1.0
 */
public class Order implements Serializable
{
	private Integer id;
	private User user;
	private String orderNo;
	private Double totalPrice;
	private Set<Item> items = new HashSet<Item>();
	private Integer count;

	/**
	 * 获取 id
	 *
	 * @return 返回 id
	 */
	public Integer getId()
	{
		return id;
	}

	/**
	 * 设置 id
	 *
	 * @param id
	 *            对 id 进行赋值
	 */
	public void setId(Integer id)
	{
		this.id = id;
	}

	/**
	 * 获取 user
	 *
	 * @return 返回 user
	 */
	public User getUser()
	{
		return user;
	}

	/**
	 * 设置 user
	 *
	 * @param user
	 *            对 user 进行赋值
	 */
	public void setUser(User user)
	{
		this.user = user;
	}

	/**
	 * 获取 orderNo
	 *
	 * @return 返回 orderNo
	 */
	public String getOrderNo()
	{
		return orderNo;
	}

	/**
	 * 设置 orderNo
	 *
	 * @param orderNo
	 *            对 orderNo 进行赋值
	 */
	public void setOrderNo(String orderNo)
	{
		this.orderNo = orderNo;
	}

	/**
	 * 获取 totalPrice
	 *
	 * @return 返回 totalPrice
	 */
	public Double getTotalPrice()
	{
		return totalPrice;
	}

	/**
	 * 设置 totalPrice
	 *
	 * @param totalPrice
	 *            对 totalPrice 进行赋值
	 */
	public void setTotalPrice(Double totalPrice)
	{
		this.totalPrice = totalPrice;
	}

	/**
	 * 获取 items
	 *
	 * @return 返回 items
	 */
	public Set<Item> getItems()
	{
		return items;
	}

	/**
	 * 设置 items
	 *
	 * @param items
	 *            对 items 进行赋值
	 */
	public void setItems(Set<Item> items)
	{
		this.items = items;
	}

	
	/**
	 *获取 count
	 *
	 * @return 返回  count
	 */
	public Integer getCount()
	{
		return count;
	}

	/**
	 * 设置  count
	 *
	 * @param count 对 count 进行赋值
	 */
	public void setCount(Integer count)
	{
		this.count = count;
	}

	/* (non-Javadoc)
	 * <p>Title:toString</p> 
	 * <p>Description: </p> 
	 * @return 
	 * @see java.lang.Object#toString() 
	 */ 
	@Override
	public String toString()
	{
		StringBuilder builder = new StringBuilder();
		builder.append("Order [id=");
		builder.append(id);
		builder.append(", user=");
		builder.append(user);
		builder.append(", orderNo=");
		builder.append(orderNo);
		builder.append(", totalPrice=");
		builder.append(totalPrice);
		builder.append(", items=");
		builder.append(items);
		builder.append(", count=");
		builder.append(count);
		builder.append("]");
		return builder.toString();
	}

}
