package edu.oakland.csi5450.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.oakland.csi5450.bean.City;
import edu.oakland.csi5450.repository.CityDao;

@Service
public class CityService
{
	@Autowired
	CityDao cityDao;
	
	public List<City> getCities() {
		return cityDao.getCities();
	}
	
	public City getCity(String name) {
		return cityDao.getCity(name.toUpperCase());
	}
	
	public boolean updateCity(City city) {
		city.setName(city.getName().toUpperCase());
		return cityDao.updateCity(city);
	}
	
	public boolean createCity(City city) {
		city.setName(city.getName().toUpperCase());
		return cityDao.createCity(city);
	}
}
