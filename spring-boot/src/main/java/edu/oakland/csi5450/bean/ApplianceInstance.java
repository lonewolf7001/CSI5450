package edu.oakland.csi5450.bean;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class ApplianceInstance
{
	@NotNull
	@Min(1)
	Integer homeId;
	
	@NotNull
	@Size(min=1, max=25)
	String modelNumber;
	
	@Size(max=30)
	String serialNumber;
	
	public Integer getHomeId()
	{
		return homeId;
	}
	public void setHomeId(Integer homeId)
	{
		this.homeId = homeId;
	}
	public String getModelNumber()
	{
		return modelNumber;
	}
	public void setModelNumber(String modelNumber)
	{
		this.modelNumber = modelNumber;
	}
	public String getSerialNumber()
	{
		return serialNumber;
	}
	public void setSerialNumber(String serialNumber)
	{
		this.serialNumber = serialNumber;
	}
	
}
