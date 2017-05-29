package user.servlet.together;

public class UserInfo 
{
	private String userId;
	private String username ;
	private double salary;
	private String userEmail;
	private String sex;
	private String education;
	private String hobbies;
	private String password;
	
	
	public String getPassword()
	{
		return password;
	}
	public void setPassword(String password)
	{
		this.password = password;
	}
	public String getUserId()
	{
		return userId;
	}
	public void setUserId(String userId)
	{
		this.userId = userId;
	}
	public String getUsername()
	{
		return username;
	}
	public void setUsername(String username)
	{
		this.username = username;
	}
	public double getSalary()
	{
		return salary;
	}
	public void setSalary(double salary)
	{
		this.salary = salary;
	}
	public String getUserEmail()
	{
		return userEmail;
	}
	public void setUserEmail(String userEmail)
	{
		this.userEmail = userEmail;
	}
	public String getSex()
	{
		return sex;
	}
	public void setSex(String sex)
	{
		this.sex = sex;
	}
	public String getEducation()
	{
		return education;
	}
	public void setEducation(String education)
	{
		this.education = education;
	}
	public String getHobbies()
	{
		return hobbies;
	}
	public void setHobbies(String hobbies)
	{
		this.hobbies = hobbies;
	}
	@Override
	public String toString()
	{
		StringBuilder builder = new StringBuilder();
		builder.append("UserInfo [userId=");
		builder.append(userId);
		builder.append(", username=");
		builder.append(username);
		builder.append(", salary=");
		builder.append(salary);
		builder.append(", userEmail=");
		builder.append(userEmail);
		builder.append(", sex=");
		builder.append(sex);
		builder.append(", education=");
		builder.append(education);
		builder.append(", hobbies=");
		builder.append(hobbies);
		builder.append(", password=");
		builder.append(password);
		builder.append("]");
		return builder.toString();
	}
	
}
