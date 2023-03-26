package edu.oakland.csi5450.controller;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

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
import edu.oakland.csi5450.bean.ApplianceInstance;
import edu.oakland.csi5450.bean.ErrorResponse;
import edu.oakland.csi5450.service.ApplianceService;

@RestController
@RequestMapping("/appliance")
public class ApplianceController
{
	
	@Autowired
	ApplianceService applianceService;
	
	@GetMapping("/")
	public List<Appliance> getAllAppliances() {
		return applianceService.getAllAppliances();
	}
	
	@GetMapping("/model/{modelNumber}")
	public ResponseEntity<Appliance> getAppliance(@Size(min=1, max=25) @PathVariable String modelNumber) {
		Appliance resp = applianceService.getApplianceByModelNumber(modelNumber);
		if(resp == null)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		else
			return new ResponseEntity<>(resp, HttpStatus.OK);
	}
	@GetMapping("/type")
	public ResponseEntity<Object> getAppliances(@Size(min=1, max=20) @RequestParam String type, 
			@Size(min=1, max=15) @RequestParam String manufacturer) {
		
		List<Appliance> resp = applianceService.getApplianceByTypeAndManufacturer(type, manufacturer);
		if(resp.isEmpty())
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		else
			return new ResponseEntity<>(resp, HttpStatus.OK);
	}
	
	@GetMapping("/home/{id}")
	public ResponseEntity<Object> getAppliancesByHomeId(@NotNull @Min(1) @PathVariable int id) {
		List<Appliance> resp = applianceService.getAppliancesByHomeId(id);
		if(resp.isEmpty())
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		else
			return new ResponseEntity<>(resp, HttpStatus.OK);
	}
	
	@PutMapping("/update")
	public ResponseEntity<Object> updateAppliance(@Valid @RequestBody Appliance appliance) {
		applianceService.sanitizeAppliance(appliance);
		
		if(!applianceService.updateAppliance(appliance))
			return new ResponseEntity<>(new ErrorResponse("Cannot Update this Appliance. Model Number does not exist."), HttpStatus.BAD_REQUEST);
		else
			return new ResponseEntity<>(HttpStatus.OK);
		
	}
	@PostMapping("/add")
	public ResponseEntity<Object> createAppliance(@Valid @RequestBody Appliance appliance) {
		applianceService.sanitizeAppliance(appliance);
		if(!applianceService.createAppliance(appliance))
			return new ResponseEntity<>(new ErrorResponse("An appliance with this model number already exists"), HttpStatus.BAD_REQUEST);
		else
			return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@PostMapping("/home/add")
	public ResponseEntity<Object> addApplianceHomeMapping(@Valid @RequestBody ApplianceInstance instance) {
		if(!applianceService.addApplianceHomeMapping(instance))
			return new ResponseEntity<>(new ErrorResponse("Mapping could not be added. Either the home or the model number does not exist, or the mapping exists already"), HttpStatus.BAD_REQUEST);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
