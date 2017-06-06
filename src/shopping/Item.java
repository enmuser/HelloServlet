/*
 * 文件名：Item.java
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

/**
 * <一句话功能描述> <功能详细描述>
 * 
 * @author 朱洪昌
 * @date 2017年6月3日
 * @version 1.0
 */
public class Item implements Serializable
{
	private Integer id;
	private Product product;
	private Integer num;
	private Order order;
	private Double price;

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
	 * 获取 product
	 *
	 * @return 返回 product
	 */
	public Product getProduct()
	{
		return product;
	}

	/**
	 * 设置 product
	 *
	 * @param product
	 *            对 product 进行赋值
	 */
	public void setProduct(Product product)
	{
		this.product = product;
	}

	/**
	 * 获取 num
	 *
	 * @return 返回 num
	 */
	public Integer getNum()
	{
		return num;
	}

	/**
	 * 设置 num
	 *
	 * @param num
	 *            对 num 进行赋值
	 */
	public void setNum(Integer num)
	{
		this.num = num;
	}

	/**
	 * 获取 order
	 *
	 * @return 返回 order
	 */
	public Order getOrder()
	{
		return order;
	}

	/**
	 * 设置 order
	 *
	 * @param order
	 *            对 order 进行赋值
	 */
	public void setOrder(Order order)
	{
		this.order = order;
	}

	/**
	 * 获取 price
	 *
	 * @return 返回 price
	 */
	public Double getPrice()
	{
		return price;
	}

	/**
	 * 设置 price
	 *
	 * @param price
	 *            对 price 进行赋值
	 */
	public void setPrice(Double price)
	{
		this.price = price;
	}
	@Override
	public String toString()
	{
		StringBuilder builder = new StringBuilder();
		builder.append("Item [id=");
		builder.append(id);
		builder.append(", product=");
		builder.append(product);
		builder.append(", num=");
		builder.append(num);
		builder.append(", order=");
		builder.append(order);
		builder.append(", price=");
		builder.append(price);
		builder.append("]");
		return builder.toString();
	}

}
