package edu.oakland.csi5450.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import edu.oakland.csi5450.bean.Appliance;
import edu.oakland.csi5450.bean.ErrorResponse;
import edu.oakland.csi5450.service.ApplianceService;

@RestController
@RequestMapping("/appliance")
public class ApplianceController
{
	
	@Autowired
	ApplianceService applianceService;
	
	@GetMapping("/model/{modelNumber}")
	public ResponseEntity<Appliance> getAppliance(@PathVariable String modelNumber) {
		String validationError = applianceService.validateModelNumber(modelNumber);
		if(validationError != null)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
		Appliance resp = applianceService.getApplianceByModelNumber(modelNumber);
		if(resp == null)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		else
			return new ResponseEntity<>(resp, HttpStatus.OK);
	}
	@GetMapping("/type")
	public ResponseEntity<Object> getAppliances(@RequestParam String type, @RequestParam String manufacturer) {
		if(applianceService.validateApplianceType(type) != null
				|| applianceService.validateManufacturer(manufacturer) != null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		List<Appliance> resp = applianceService.getApplianceByTypeAndManufacturer(type, manufacturer);
		if(resp.isEmpty())
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		else
			return new ResponseEntity<>(resp, HttpStatus.OK);
	}
	
	@PutMapping("/update")
	public ResponseEntity<Object> updateAppliance(@RequestBody Appliance appliance) {
		String validationError = applianceService.validateAppliance(appliance);
		if(validationError != null) {
			return new ResponseEntity<>(new ErrorResponse(validationError), HttpStatus.BAD_REQUEST);
		}
		
		if(!applianceService.updateAppliance(appliance))
			return new ResponseEntity<>(new ErrorResponse("Cannot Update this Appliance. Model Number does not exist."), HttpStatus.BAD_REQUEST);
		else
			return new ResponseEntity<>(HttpStatus.OK);
		
	}
	@PostMapping("/add")
	public ResponseEntity<Object> createAppliance(@RequestBody Appliance appliance) {
		String validationError = applianceService.validateAppliance(appliance);
		if(validationError != null) {
			return new ResponseEntity<>(new ErrorResponse(validationError), HttpStatus.BAD_REQUEST);
		}
		if(!applianceService.createAppliance(appliance))
			return new ResponseEntity<>(new ErrorResponse("An appliance with this model number already exists"), HttpStatus.BAD_REQUEST);
		else
			return new ResponseEntity<>(HttpStatus.OK);
	}
	
}
