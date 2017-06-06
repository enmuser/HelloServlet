/*
 * 文件名：Product.java
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
public class Product implements Serializable
{
	private Integer id;
	private String name;
	private String description;
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
	 * 获取 name
	 *
	 * @return 返回 name
	 */
	public String getName()
	{
		return name;
	}

	/**
	 * 设置 name
	 *
	 * @param name
	 *            对 name 进行赋值
	 */
	public void setName(String name)
	{
		this.name = name;
	}

	/**
	 * 获取 description
	 *
	 * @return 返回 description
	 */
	public String getDescription()
	{
		return description;
	}

	/**
	 * 设置 description
	 *
	 * @param description
	 *            对 description 进行赋值
	 */
	public void setDescription(String description)
	{
		this.description = description;
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
		builder.append("Product [id=");
		builder.append(id);
		builder.append(", name=");
		builder.append(name);
		builder.append(", description=");
		builder.append(description);
		builder.append(", price=");
		builder.append(price);
		builder.append("]");
		return builder.toString();
	}
    
}
