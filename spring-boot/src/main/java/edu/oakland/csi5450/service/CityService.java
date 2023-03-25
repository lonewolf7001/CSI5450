package edu.oakland.csi5450.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.oakland.csi5450.bean.City;
import edu.oakland.csi5450.repository.CityDao;

@Service
public class CityService
{
	@Autowired
	CityDao cityDao;
	
	public List<City> getCities() {
		List<City> cities = cityDao.getCities(); 
		for(City city: cities)
			city.setName(city.getName().trim());
		return cities; 
	}
	
	public City getCity(String name) {
		City city = cityDao.getCity(name.toUpperCase());
		city.setName(city.getName().trim());
		return city;
	}
	
	
	@Transactional
	public boolean updateCity(City city) {
		city.setName(city.getName().toUpperCase());
		if(cityDao.getCity(city.getName()) == null)
			return false;
		cityDao.updateCity(city);
		return true;
	}
	
	@Transactional
	public boolean createCity(City city) {
		city.setName(city.getName().toUpperCase());
		if(cityDao.getCity(city.getName()) != null)
			return false;
		cityDao.createCity(city);
		return true;
	}
}
