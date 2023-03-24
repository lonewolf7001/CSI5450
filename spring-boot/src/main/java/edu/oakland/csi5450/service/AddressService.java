package edu.oakland.csi5450.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.oakland.csi5450.bean.Address;
import edu.oakland.csi5450.bean.NewAddress;
import edu.oakland.csi5450.repository.AddressDao;
import edu.oakland.csi5450.repository.CityDao;

@Service
public class AddressService
{
	
	@Autowired
	AddressDao addressDao;
	
	@Autowired
	CityDao cityDao;
	
	public Address getAddressById(int id)
	{
		Address address = addressDao.getAddressById(id);
		if(address != null) {
			address.setStreet(address.getStreet().trim());
			address.setCity(address.getCity().trim());
			address.setCounty(address.getCounty());
		}
		return address;
	}

	/**
	 * 
	 * @param address
	 * @return null if address was not created (city not found), the created address ID otherwise
	 */
	@Transactional
	public Integer createAddress(NewAddress address)
	{
		if(cityDao.getCity(address.getCity()) == null)
			return null;
		return addressDao.createAddress(address);
	}

	/**
	 * 
	 * @param address
	 * @return false if either the given address or the city does not exist, true otherwise
	 */
	@Transactional
	public boolean updateAddress(Address address)
	{
		
		if(addressDao.getAddressById(address.getId()) == null
				|| cityDao.getCity(address.getCity()) == null)
			return false;
		addressDao.updateAddress(address);
		return true;
	}
	
	public void sanitizeAddress(NewAddress address) {
		address.setStreet(address.getStreet().toUpperCase());
		address.setCity(address.getCity().toUpperCase());
		address.setCounty(address.getCounty().toUpperCase());
	}
}
