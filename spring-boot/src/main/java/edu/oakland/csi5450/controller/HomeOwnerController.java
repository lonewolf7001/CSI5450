<<<<<<< HEAD
package edu.oakland.csi5450.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.oakland.csi5450.bean.ErrorResponse;
import edu.oakland.csi5450.bean.HomeOwner;
import edu.oakland.csi5450.service.HomeOwnerService;

@RestController
@RequestMapping("/owner")
public class HomeOwnerController
{
	@Autowired
	HomeOwnerService homeOwnerService;
	
	@GetMapping("/id/{id}")
	public ResponseEntity<HomeOwner> getOwnerById(@PathVariable long id) {
		HomeOwner response = homeOwnerService.getHomeOwnerById(id);
		if(response == null) { //ID not found
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(response, HttpStatus.OK);
		}
	}
	
	@PostMapping("/add")
	public ResponseEntity<Object> createHomeOwner(@Valid @RequestBody HomeOwner req){
		homeOwnerService.sanitizeHomeOwner(req);
		if(!homeOwnerService.createHomeOwner(req))
			return new ResponseEntity<>(new ErrorResponse("An owner with this SSN already exists"), HttpStatus.BAD_REQUEST);
		else
			return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@PutMapping("/update")
	public ResponseEntity<Object> updateHomeOwner(@Valid @RequestBody HomeOwner req) {
		homeOwnerService.sanitizeHomeOwner(req);
		if(!homeOwnerService.updateHomeOwner(req))
			return new ResponseEntity<>(new ErrorResponse("Cannot Update this Home Owner. Home Owner does not exist."), HttpStatus.BAD_REQUEST);
		else
			return new ResponseEntity<>(HttpStatus.OK);
	}
	
}
=======
package edu.oakland.csi5450.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.oakland.csi5450.bean.ErrorResponse;
import edu.oakland.csi5450.bean.HomeOwner;
import edu.oakland.csi5450.service.HomeOwnerService;

@RestController
@RequestMapping("/owner")
public class HomeOwnerController
{
	@Autowired
	HomeOwnerService homeOwnerService;
	
	@GetMapping("/id/{id}")
	public ResponseEntity<HomeOwner> getOwnerById(@PathVariable long id) {
		HomeOwner response = homeOwnerService.getHomeOwnerById(id);
		if(response == null) { //ID not found
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(response, HttpStatus.OK);
		}
	}
	
	@PostMapping("/add")
	public ResponseEntity<Object> createHomeOwner(@RequestBody HomeOwner req){
		String validationError = homeOwnerService.validateHomeOwner(req);
		if(validationError != null) {
			return new ResponseEntity<>(new ErrorResponse(validationError), HttpStatus.BAD_REQUEST);
		}
		if(!homeOwnerService.createHomeOwner(req))
			return new ResponseEntity<>(new ErrorResponse("An owner with this SSN already exists"), HttpStatus.BAD_REQUEST);
		else
			return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@PutMapping("/update")
	public ResponseEntity<Object> updateHomeOwner(@RequestBody HomeOwner req) {
		String validationError = homeOwnerService.validateHomeOwner(req);
		if(validationError != null) {
			return new ResponseEntity<>(new ErrorResponse(validationError), HttpStatus.BAD_REQUEST);
		}
		if(!homeOwnerService.updateHomeOwner(req))
			return new ResponseEntity<>(new ErrorResponse("Cannot Update this Home Owner. Home Owner does not exist."), HttpStatus.BAD_REQUEST);
		else
			return new ResponseEntity<>(HttpStatus.OK);
	}
	
}
>>>>>>> b20760b (Synced with calib backend springboot pages)
