package edu.oakland.csi5450.bean;

public class ExtendedHomeInfo extends Home
{
	private Address addressInfo;
	private int latestPrice;
	private Long currentOwner;
	
	public Address getAddressInfo()
	{
		return addressInfo;
	}
	public void setAddressInfo(Address addressInfo)
	{
		this.addressInfo = addressInfo;
	}
	public int getLatestPrice()
	{
		return latestPrice;
	}
	public void setLatestPrice(int latestPrice)
	{
		this.latestPrice = latestPrice;
	}
	public Long getCurrentOwner()
	{
		return currentOwner;
	}
	public void setCurrentOwner(Long currentOwner)
	{
		this.currentOwner = currentOwner;
	}
	
}
