package edu.oakland.csi5450.bean;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Appliance
{
	@Size(min=1, max=20)
	private String applianceType;
	@Size(min=1, max=25)
	private String modelNumber;
	@Size(min=1, max=15)
	private String manufacturer;
	@Min(1500)
	private int year;
	@NotNull
	@Min(0)
	private double price;
	
	public String getApplianceType()
	{
		return applianceType;
	}
	public void setApplianceType(String applianceType)
	{
		this.applianceType = applianceType;
	}
	public String getModelNumber()
	{
		return modelNumber;
	}
	public void setModelNumber(String modelNumber)
	{
		this.modelNumber = modelNumber;
	}
	public String getManufacturer()
	{
		return manufacturer;
	}
	public void setManufacturer(String manufacturer)
	{
		this.manufacturer = manufacturer;
	}
	public int getYear()
	{
		return year;
	}
	public void setYear(int year)
	{
		this.year = year;
	}
	public double getPrice()
	{
		return price;
	}
	public void setPrice(double price)
	{
		this.price = price;
	}
	
}
