package edu.oakland.csi5450.bean;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class Company extends NewCompany
{
	@NotNull
	@Min(0)
	private int id;

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}
	
	
}
