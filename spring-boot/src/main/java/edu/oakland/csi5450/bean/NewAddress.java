package edu.oakland.csi5450.bean;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

public class NewAddress
{
	@Min(1)
	private int houseNum;
	
	@Size(min=1, max=15)
	private String street;
	
	@Max(9999)
	@Min(0)
	private Integer aptNum;
	
	@Size(min=1, max=30)
	private String city;
	
	@Size(min=1, max=15)
	private String county;
	
	@Min(10000)
	@Max(99999)
	private int zip;
	
	@Min(0)
	private Integer homeId;
	
	public int getHouseNum()
	{
		return houseNum;
	}
	public void setHouseNum(int houseNum)
	{
		this.houseNum = houseNum;
	}
	public String getStreet()
	{
		return street;
	}
	public void setStreet(String street)
	{
		this.street = street;
	}
	public Integer getAptNum()
	{
		return aptNum;
	}
	public void setAptNum(Integer aptNum)
	{
		this.aptNum = aptNum;
	}
	public String getCity()
	{
		return city;
	}
	public void setCity(String city)
	{
		this.city = city;
	}
	public String getCounty()
	{
		return county;
	}
	public void setCounty(String county)
	{
		this.county = county;
	}
	public int getZip()
	{
		return zip;
	}
	public void setZip(int zip)
	{
		this.zip = zip;
	}
	public Integer getHomeId()
	{
		return homeId;
	}
	public void setHomeId(Integer homeId)
	{
		this.homeId = homeId;
	}
}
