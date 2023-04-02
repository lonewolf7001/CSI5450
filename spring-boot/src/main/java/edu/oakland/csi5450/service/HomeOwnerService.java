package edu.oakland.csi5450.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.oakland.csi5450.bean.HomeOwner;
import edu.oakland.csi5450.repository.HomeOwnerDao;

@Service
public class HomeOwnerService {
	@Autowired
	HomeOwnerDao homeOwnerDao;

	public List<HomeOwner> getAllHomeOwners() {
		return homeOwnerDao.getHomeOwners();
	}

	public HomeOwner getHomeOwnerById(long id) {
		return homeOwnerDao.getHomeOwnerById(id);
	}

	public List<HomeOwner> getHomeOwnerByHomeType(List<String> homeTypes) {
		return homeOwnerDao.getHomeOwnerByHomeType(homeTypes);
	}
	/**
	 * validates and sanitizes a HomeOwner object. Returns an error string, or null
	 * if the bean is valid
	 * 
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
	 * Returns true if successful. False indicates that an owner with this ssn
	 * already exists
	 * 
	 * @param homeOwner
	 * @return
	 */
	@Transactional
	public boolean createHomeOwner(HomeOwner homeOwner) {
		if (homeOwnerDao.getHomeOwnerById(homeOwner.getSsn()) != null) {
			return false;
		}
		homeOwnerDao.createHomeOwner(homeOwner);
		return true;
	}

	/**
	 * Returns true if successful. False indicates that an owner with this ssn does
	 * not exist
	 * 
	 * @param homeOwner
	 * @return
	 */
	@Transactional
	public boolean updateHomeOwner(HomeOwner homeOwner) {
		if (homeOwnerDao.getHomeOwnerById(homeOwner.getSsn()) == null) {
			return false;
		}
		homeOwnerDao.updateHomeOwner(homeOwner);
		return true;
	}
}
