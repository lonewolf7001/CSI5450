package edu.oakland.csi5450.bean;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class City
{
	@NotNull
	@Size(min=1, max=30)
	private String name;
	
	@Min(0)
	private int population;
	
	public String getName()
	{
		return name;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	public int getPopulation()
	{
		return population;
	}
	public void setPopulation(int population)
	{
		this.population = population;
	}
	
}
