package edu.oakland.csi5450.bean;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class NewCompany
{
	@NotNull
	@Size(min=1, max=50)
	private String name;
	
	@NotNull
	@Min(0)
	@Max(100)
	private double commission;
	
	public String getName()
	{
		return name;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	public double getCommission()
	{
		return commission;
	}
	public void setCommission(double commission)
	{
		this.commission = commission;
	}
	
	
}
