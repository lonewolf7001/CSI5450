package edu.oakland.csi5450.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.oakland.csi5450.bean.Appliance;
import edu.oakland.csi5450.bean.ApplianceInstance;
import edu.oakland.csi5450.repository.ApplianceDao;
import edu.oakland.csi5450.repository.HomeDao;

@Service
public class ApplianceService
{
	@Autowired
	ApplianceDao applianceDao;
	
	@Autowired
	HomeDao homeDao;
	
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
	
	public List<Appliance> getAppliancesByHomeId(int homeId) {
		List<Appliance> appliances = applianceDao.getAppliancesByHomeId(homeId);
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
	
	@Transactional
	public boolean addApplianceHomeMapping(ApplianceInstance instance) {
		instance.setModelNumber(instance.getModelNumber().toUpperCase());
		if(instance.getSerialNumber() != null)
			instance.setSerialNumber(instance.getSerialNumber().toUpperCase());
		
		if(homeDao.getById(instance.getHomeId()) == null
				|| applianceDao.getApplianceByModelNumber(instance.getModelNumber()) == null
				|| applianceDao.doesApplianceHomeMappingExist(instance.getHomeId(), instance.getModelNumber())){
			return false;
		}
				
		applianceDao.addApplianceHome(instance);
		return true;
	}
	
	public void sanitizeAppliance(Appliance appliance) {
		appliance.setModelNumber(appliance.getModelNumber().toUpperCase());
		appliance.setApplianceType(appliance.getApplianceType().toUpperCase());
		appliance.setManufacturer(appliance.getManufacturer().toUpperCase());
	}
	
	
	private void trimApplianceResult(Appliance appliance) {
		appliance.setModelNumber(appliance.getModelNumber().trim());
		appliance.setApplianceType(appliance.getApplianceType().trim());
		appliance.setManufacturer(appliance.getManufacturer().trim());	
	}
	
}
