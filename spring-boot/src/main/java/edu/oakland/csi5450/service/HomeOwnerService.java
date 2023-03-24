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
	 * validates and sanitizes a HomeOwner object. Returns an error string, or null if the bean is valid 
	 * @param homeOwner
	 * @return
	 */
	public void sanitizeHomeOwner(HomeOwner homeOwner) {
		
		homeOwner.setFirstName(homeOwner.getFirstName().toUpperCase());
		homeOwner.setLastName(homeOwner.getLastName().toUpperCase());
		homeOwner.setProfession(homeOwner.getProfession().toUpperCase());
		homeOwner.setEmail(homeOwner.getEmail().toUpperCase());
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
