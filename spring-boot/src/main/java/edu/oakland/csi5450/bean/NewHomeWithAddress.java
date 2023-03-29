package edu.oakland.csi5450.bean;

import javax.validation.constraints.NotNull;

public class NewHomeWithAddress extends Home
{
	@NotNull
	private NewAddress address;

	public NewAddress getAddress()
	{
		return address;
	}

	public void setAddress(NewAddress address)
	{
		this.address = address;
	}
	
}
