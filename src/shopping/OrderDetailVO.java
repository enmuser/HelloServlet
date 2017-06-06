/*
 * 文件名：OrderDetailVO.java
 * 版权：Enmuser Technologies Co.,Ltd. Copyright 2016-2017
 * 描述：<描述>
 * 修改人：Administrator
 * 修改时间：2017年6月6日
 * 修改单号：<修改单号>
 * 修改内容：<修改内容>
 *
 */
package shopping;

/**
 * <一句话功能描述> <功能详细描述>
 * 
 * @author 朱洪昌
 * @date 2017年6月6日
 * @version 1.0
 */
public class OrderDetailVO
{
	private String username;

	private String orderNo;

	private int itemId;

	private String productName;

	private int num;

	private double itemprice;

	private int count;

	private double totalprice;

	/**
	 * 获取 username
	 *
	 * @return 返回 username
	 */
	public String getUsername()
	{
		return username;
	}

	/**
	 * 设置 username
	 *
	 * @param username
	 *            对 username 进行赋值
	 */
	public void setUsername(String username)
	{
		this.username = username;
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
	 * 获取 itemId
	 *
	 * @return 返回 itemId
	 */
	public int getItemId()
	{
		return itemId;
	}

	/**
	 * 设置 itemId
	 *
	 * @param itemId
	 *            对 itemId 进行赋值
	 */
	public void setItemId(int itemId)
	{
		this.itemId = itemId;
	}

	/**
	 * 获取 productName
	 *
	 * @return 返回 productName
	 */
	public String getProductName()
	{
		return productName;
	}

	/**
	 * 设置 productName
	 *
	 * @param productName
	 *            对 productName 进行赋值
	 */
	public void setProductName(String productName)
	{
		this.productName = productName;
	}

	/**
	 * 获取 num
	 *
	 * @return 返回 num
	 */
	public int getNum()
	{
		return num;
	}

	/**
	 * 设置 num
	 *
	 * @param num
	 *            对 num 进行赋值
	 */
	public void setNum(int num)
	{
		this.num = num;
	}

	/**
	 * 获取 itemprice
	 *
	 * @return 返回 itemprice
	 */
	public double getItemprice()
	{
		return itemprice;
	}

	/**
	 * 设置 itemprice
	 *
	 * @param itemprice
	 *            对 itemprice 进行赋值
	 */
	public void setItemprice(double itemprice)
	{
		this.itemprice = itemprice;
	}

	/**
	 * 获取 count
	 *
	 * @return 返回 count
	 */
	public int getCount()
	{
		return count;
	}

	/**
	 * 设置 count
	 *
	 * @param count
	 *            对 count 进行赋值
	 */
	public void setCount(int count)
	{
		this.count = count;
	}

	/**
	 * 获取 totalprice
	 *
	 * @return 返回 totalprice
	 */
	public double getTotalprice()
	{
		return totalprice;
	}

	/**
	 * 设置 totalprice
	 *
	 * @param totalprice
	 *            对 totalprice 进行赋值
	 */
	public void setTotalprice(double totalprice)
	{
		this.totalprice = totalprice;
	}

	@Override
	public String toString()
	{
		StringBuilder builder = new StringBuilder();
		builder.append("OrderDetailVO [username=");
		builder.append(username);
		builder.append(", orderNo=");
		builder.append(orderNo);
		builder.append(", itemId=");
		builder.append(itemId);
		builder.append(", productName=");
		builder.append(productName);
		builder.append(", num=");
		builder.append(num);
		builder.append(", itemprice=");
		builder.append(itemprice);
		builder.append(", count=");
		builder.append(count);
		builder.append(", totalprice=");
		builder.append(totalprice);
		builder.append("]");
		return builder.toString();
	}
	
	

}
