package edu.oakland.csi5450.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.oakland.csi5450.bean.HomeOwner;
import edu.oakland.csi5450.repository.HomeOwnerDao;

@Service
public class HomeOwnerService
{
	@Autowired
	HomeOwnerDao homeOwnerDao;
	
	
	public HomeOwner getHomeOwnerById(long id) {
		return homeOwnerDao.getHomeOwnerById(id);
	}
	
	/**
	 * validates and sanitizes a HomeOwner object
	 * @param homeOwner
	 * @return
	 */
	public String validateHomeOwner(HomeOwner homeOwner) {
		if(homeOwner.getSsn() > 999999999 || homeOwner.getSsn() < 100000000)
			return "ssn must be exactly 9 digits";
		if(isNullOrEmpty(homeOwner.getFirstName()))
			return "firstName is required";
		if(isNullOrEmpty(homeOwner.getLastName()))
			return "lastName is required";
		if(homeOwner.getDateOfBirth() == null)
			return "dateOfBirth is required";
		if(isNullOrEmpty(homeOwner.getProfession()))
			return "profession is required";
		if(homeOwner.getPhone() < 1000000000 || homeOwner.getPhone() > 9999999999L)
			return "phone must be exactly 10 digits";
		if(isNullOrEmpty(homeOwner.getEmail()))
			return "email is required";
		
		if(homeOwner.getFirstName().length() > 25)
			return "firstName may be no more than 25 characters";
		if(homeOwner.getLastName().length() > 30)
			return "lastName may be no more than 30 characters";
		if(homeOwner.getProfession().length() > 50)
			return "profession may be no more than 50 characters";
		if(homeOwner.getEmail().length() > 50)
			return "email may be no more than 50 characters";
		
		homeOwner.setFirstName(homeOwner.getFirstName().toUpperCase());
		homeOwner.setLastName(homeOwner.getLastName().toUpperCase());
		homeOwner.setProfession(homeOwner.getProfession().toUpperCase());
		homeOwner.setEmail(homeOwner.getEmail().toUpperCase());
		return null;
	}
	
	private boolean isNullOrEmpty(String s) {
		return s == null || s.isEmpty();
	}
	
	/**
	 * Returns true if successful. False indicates that an owner with this ssn already exists
	 * @param homeOwner
	 * @return
	 */
	public boolean createHomeOwner(HomeOwner homeOwner) {
		return homeOwnerDao.createHomeOwner(homeOwner);
	}


	/**
	 * Returns true if successful. False indicates that an owner with this ssn does not exist
	 * @param homeOwner
	 * @return
	 */
	public boolean updateHomeOwner(HomeOwner homeOwner)
	{
		return homeOwnerDao.updateHomeOwner(homeOwner);
	}
}
