package edu.oakland.csi5450.bean;

import java.sql.Date;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class HomeOwner
{
	
	@Min(100000000)
	@Max(999999999)
	private int ssn;
	
	@Size(min=1, max=25)
	private String firstName;
	
	@Size(min=1, max=30)
	private String lastName;
	
	@NotNull
	private int numDependents;
	
	@NotNull
	private int annualIncome;
	
	@NotNull
	private Date dateOfBirth;
	
	@Size(min=1, max=50)
	private String profession;
	
	@Min(1000000000)
	@Max(9999999999L)
	private long phone;
	
	@Email
	@Size(max=50)
	private String email;

	
	public int getSsn()
	{
		return ssn;
	}
	public void setSsn(int ssn)
	{
		this.ssn = ssn;
	}
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
	public int getNumDependents()
	{
		return numDependents;
	}
	public void setNumDependents(int numDependents)
	{
		this.numDependents = numDependents;
	}
	public int getAnnualIncome()
	{
		return annualIncome;
	}
	public void setAnnualIncome(int annualIncome)
	{
		this.annualIncome = annualIncome;
	}
	public Date getDateOfBirth()
	{
		return dateOfBirth;
	}
	public void setDateOfBirth(Date dateOfBirth)
	{
		this.dateOfBirth = dateOfBirth;
	}
	public String getProfession()
	{
		return profession;
	}
	public void setProfession(String profession)
	{
		this.profession = profession;
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
