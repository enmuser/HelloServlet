/*
 * 文件名：ShoppingCartVO.java
 * 版权：Enmuser Technologies Co.,Ltd. Copyright 2016-2017
 * 描述：<描述>
 * 修改人：Administrator
 * 修改时间：2017年6月3日
 * 修改单号：<修改单号>
 * 修改内容：<修改内容>
 *
 */
package shopping;

import java.util.List;
import java.util.Map;

/**
 * <一句话功能描述> <功能详细描述>
 * 
 * @author 朱洪昌
 * @date 2017年6月3日
 * @version 1.0
 */
public class ShoppingCartVO
{
	private Map<Integer, Item> items;
	private Double price;

	/**
	 * 获取 items
	 *
	 * @return 返回 items
	 */
	public Map<Integer, Item> getItems()
	{
		return items;
	}

	/**
	 * 设置 items
	 *
	 * @param items
	 *  对 items 进行赋值
	 */
	public void setItems(Map<Integer, Item> items)
	{
		this.items = items;
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
 
	public void add(Integer productId)
	{
		
	}
	
	private void removeByProductId(List<Integer> productId)
	{
		
	}
	
	private void modifyProductNum(Integer produtId,Boolean value)
	{
		
	}
	
	private void clear()
	{
		
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
		builder.append("ShoppingCartVO [items=");
		builder.append(items);
		builder.append(", price=");
		builder.append(price);
		builder.append("]");
		return builder.toString();
	}
	
	
}
