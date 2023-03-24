<<<<<<< HEAD
package edu.oakland.csi5450.bean;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class NewAgent
{
	@Size(min=1, max=25)
	private String firstName;
	
	@Size(min=1, max=30)
	private String lastName;
	
	@Min(1000000000)
	@Max(9999999999L)
	private long phone;
	
	@NotNull
	@Email
	private String email;
	
	public String getFirstName()
	{
		return firstName;
	}
	public void setFirstName(String firstName)
	{
		this.firstName = firstName;
	}
	public String getLastName()
	{
		return lastName;
	}
	public void setLastName(String lastName)
	{
		this.lastName = lastName;
	}
	public long getPhone()
	{
		return phone;
	}
	public void setPhone(long phone)
	{
		this.phone = phone;
	}
	public String getEmail()
	{
		return email;
	}
	public void setEmail(String email)
	{
		this.email = email;
	}

}
=======
package edu.oakland.csi5450.bean;

public class NewAgent
{
	String firstName;
	String lastName;
	long phone;
	String email;
	public String getFirstName()
	{
		return firstName;
	}
	public void setFirstName(String firstName)
	{
		this.firstName = firstName;
	}
	public String getLastName()
	{
		return lastName;
	}
	public void setLastName(String lastName)
	{
		this.lastName = lastName;
	}
	public long getPhone()
	{
		return phone;
	}
	public void setPhone(long phone)
	{
		this.phone = phone;
	}
	public String getEmail()
	{
		return email;
	}
	public void setEmail(String email)
	{
		this.email = email;
	}

}
>>>>>>> b20760b (Synced with calib backend springboot pages)
