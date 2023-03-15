package edu.oakland.csi5450.bean;

import java.sql.Date;

public class HomeOwnerResponse
{
	
	private int ssn;
	private String firstName;
	private String lastName;
	private int numDependents;
	private int annualIncome;
	private Date dateOfBirth;
	private String profession;
	private long phone;
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
