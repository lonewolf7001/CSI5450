package edu.oakland.csi5450.controller;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Size;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import edu.oakland.csi5450.bean.City;
import edu.oakland.csi5450.bean.ErrorResponse;
import edu.oakland.csi5450.service.CityService;

@RestController
@RequestMapping("/city")
public class CityController
{
	@Autowired
	CityService cityService;
	

	@GetMapping("/")
	public List<City> getCities() {
		return cityService.getCities();
	}
	
	@GetMapping("/name")
	public ResponseEntity<Object> getCity(@RequestParam @Size(max=30) String name) {
		City city = cityService.getCity(name);
		if(city == null)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		else
			return new ResponseEntity<>(city, HttpStatus.OK);
	}
	
	@PostMapping("/")
	public ResponseEntity<Object> addCity(@Valid @RequestBody City city) {
		if(!cityService.createCity(city))
			return new ResponseEntity<>(new ErrorResponse("A city with this name already exists"), HttpStatus.BAD_REQUEST);
		else
			return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@PutMapping("/")
	public ResponseEntity<Object> updateCity(@Valid @RequestBody City city) {
		if(!cityService.updateCity(city))
			return new ResponseEntity<>(new ErrorResponse("Cannot Update this City. City does not exist."), HttpStatus.BAD_REQUEST);
		else
			return new ResponseEntity<>(HttpStatus.OK);
	}
	
}
