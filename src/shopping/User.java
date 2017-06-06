package shopping;

import java.io.Serializable;

public class User implements Serializable
{
    private Integer id;
    private String username;
    private String password;
    private String useremail;
    private String phone;
    private String address;
	public Integer getId()
	{
		return id;
	}
	public void setId(Integer id)
	{
		this.id = id;
	}
	public String getUsername()
	{
		return username;
	}
	public void setUsername(String username)
	{
		this.username = username;
	}
	public String getPassword()
	{
		return password;
	}
	public void setPassword(String password)
	{
		this.password = password;
	}
	public String getPhone()
	{
		return phone;
	}
	public void setPhone(String phone)
	{
		this.phone = phone;
	}
	
	
	public String getAddress()
	{
		return address;
	}
	public void setAddress(String address)
	{
		this.address = address;
	}
	
	/**
	 *获取 useremail
	 *
	 * @return 返回  useremail
	 */
	public String getUseremail()
	{
		return useremail;
	}
	/**
	 * 设置  useremail
	 *
	 * @param useremail 对 useremail 进行赋值
	 */
	public void setUseremail(String useremail)
	{
		this.useremail = useremail;
	}
	@Override
	public String toString()
	{
		StringBuilder builder = new StringBuilder();
		builder.append("User [id=");
		builder.append(id);
		builder.append(", username=");
		builder.append(username);
		builder.append(", password=");
		builder.append(password);
		builder.append(", useremail=");
		builder.append(useremail);
		builder.append(", phone=");
		builder.append(phone);
		builder.append(", address=");
		builder.append(address);
		builder.append("]");
		return builder.toString();
	}
	
}
