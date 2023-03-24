package edu.oakland.csi5450.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.oakland.csi5450.bean.Appliance;
import edu.oakland.csi5450.repository.ApplianceDao;

@Service
public class ApplianceService
{
	@Autowired
	ApplianceDao applianceDao;
	
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
	public boolean createAppliance(Appliance appliance) {
		return applianceDao.createAppliance(appliance);
	}
	
	/**
	 * 
	 * @param appliance
	 * @return true if successfully modified, false if an appliance with this model number does not exist
	 */
	public boolean updateAppliance(Appliance appliance)
	{
		return applianceDao.updateAppliance(appliance);
	}
	
	public String validateAppliance(Appliance appliance) {
		String error = validateModelNumber(appliance.getModelNumber());
		if(error != null) return error;
		
		error = validateApplianceType(appliance.getApplianceType());
		if(error != null) return error;
		
		error = validateManufacturer(appliance.getManufacturer());
		if(error != null) return error;
		
		if(appliance.getYear() > 9999 || appliance.getYear() < 1000)
			return "year must be exactly 4 digits";
		
		appliance.setModelNumber(appliance.getModelNumber().toUpperCase());
		appliance.setApplianceType(appliance.getApplianceType().toUpperCase());
		appliance.setManufacturer(appliance.getManufacturer().toUpperCase());
		return null;
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
