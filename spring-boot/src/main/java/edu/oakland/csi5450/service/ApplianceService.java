package edu.oakland.csi5450.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.oakland.csi5450.bean.Appliance;
import edu.oakland.csi5450.repository.ApplianceDao;

@Service
public class ApplianceService
{
	@Autowired
	ApplianceDao applianceDao;
	
	public List<Appliance> getAllAppliances() {
		List<Appliance> appliances = applianceDao.getAppliances();
		for(Appliance appliance : appliances) {
			trimApplianceResult(appliance);
		}
		return appliances;
	}
	
	public Appliance getApplianceByModelNumber(String modelNumber) {
		Appliance result = applianceDao.getApplianceByModelNumber(modelNumber.toUpperCase());
		trimApplianceResult(result);
		return result;
	}
	
	public List<Appliance> getApplianceByTypeAndManufacturer(String type, String manufacturer) {
		List<Appliance> appliances = applianceDao.getApplianceByTypeAndManufacturer(type.toUpperCase(), manufacturer.toUpperCase());
		for(Appliance appliance : appliances) {
			trimApplianceResult(appliance);
		}
		return appliances;
	}
	
	
	/**
	 * 
	 * @param appliance
	 * @return true if successfully created, false if an appliance with this model number already exists
	 */
	@Transactional
	public boolean createAppliance(Appliance appliance) {
		if(applianceDao.getApplianceByModelNumber(appliance.getModelNumber()) != null)
			return false;
		applianceDao.createAppliance(appliance);
		return true;
	}
	
	/**
	 * 
	 * @param appliance
	 * @return true if successfully modified, false if an appliance with this model number does not exist
	 */
	@Transactional
	public boolean updateAppliance(Appliance appliance)
	{
		if(applianceDao.getApplianceByModelNumber(appliance.getModelNumber()) == null)
			return false;
		applianceDao.updateAppliance(appliance);
		return true;
	}
	
	public void sanitizeAppliance(Appliance appliance) {
		appliance.setModelNumber(appliance.getModelNumber().toUpperCase());
		appliance.setApplianceType(appliance.getApplianceType().toUpperCase());
		appliance.setManufacturer(appliance.getManufacturer().toUpperCase());
	}
	public String validateModelNumber(String modelNumber) {
		if(isNullOrEmpty(modelNumber))
			return "modelNumber is required";
		if(modelNumber.length() > 25)
			return "modelNumber may be no more than 25 characters";
		return null;
	}
	public String validateManufacturer(String manufacturer) {
		if(isNullOrEmpty(manufacturer))
			return "manufacturer is required";
		if(manufacturer.length() > 15)
			return "manufacturer may be no more than 15 characters";
		return null;
	}
	public String validateApplianceType(String type) {
		if(isNullOrEmpty(type))
			return "applianceType is required";
		if(type.length() > 20)
			return "applianceType may be no more than 20 characters";
		return null;
	}
	
	
	private void trimApplianceResult(Appliance appliance) {
		appliance.setModelNumber(appliance.getModelNumber().trim());
		appliance.setApplianceType(appliance.getApplianceType().trim());
		appliance.setManufacturer(appliance.getManufacturer().trim());	
	}
	private boolean isNullOrEmpty(String s) {
		return s == null || s.isEmpty();
	}
	
}
