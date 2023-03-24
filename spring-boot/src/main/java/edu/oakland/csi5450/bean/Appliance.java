package edu.oakland.csi5450.bean;

public class Appliance
{
	private String applianceType;
	private String modelNumber;
	private String manufacturer;
	private int year;
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
