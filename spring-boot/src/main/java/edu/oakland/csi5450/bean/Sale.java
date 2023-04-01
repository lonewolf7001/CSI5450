package edu.oakland.csi5450.bean;

import java.sql.Date;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class Sale
{
	@NotNull
	@Min(1)
	private Integer homeId;
	
	@NotNull
	@Min(100000000)
	@Max(999999999)
	private Long ownerId;
	
	@NotNull
	@Min(1)
	private Integer agentId;
	
	@NotNull
	@Min(1)
	private Integer companyId;
	
	@Min(1)
	private Integer price;
	
	@NotNull
	private Date saleDate;

	public Integer getHomeId()
	{
		return homeId;
	}

	public void setHomeId(Integer homeId)
	{
		this.homeId = homeId;
	}

	public Long getOwnerId()
	{
		return ownerId;
	}

	public void setOwnerId(Long ownerId)
	{
		this.ownerId = ownerId;
	}

	public Integer getAgentId()
	{
		return agentId;
	}

	public void setAgentId(Integer agentId)
	{
		this.agentId = agentId;
	}

	public Integer getCompanyId()
	{
		return companyId;
	}

	public void setCompanyId(Integer companyId)
	{
		this.companyId = companyId;
	}

	public Integer getPrice()
	{
		return price;
	}

	public void setPrice(Integer price)
	{
		this.price = price;
	}

	public Date getSaleDate()
	{
		return saleDate;
	}

	public void setSaleDate(Date saleDate)
	{
		this.saleDate = saleDate;
	}
	
}
