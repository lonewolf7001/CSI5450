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

import edu.oakland.csi5450.bean.Address;
import edu.oakland.csi5450.bean.ErrorResponse;
import edu.oakland.csi5450.bean.NewAddress;
import edu.oakland.csi5450.bean.NewAddressResponse;
import edu.oakland.csi5450.service.AddressService;

@RestController
@RequestMapping("/address")
public class AddressController
{
	@Autowired
	AddressService addressService;
	
	@GetMapping("/id/{id}")
	public ResponseEntity<Object> getAddressById(@PathVariable int id) {
		Address address = addressService.getAddressById(id);
		if(address == null)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		else
			return new ResponseEntity<>(address, HttpStatus.OK);
	}
	
	//TODO: add GET method for lookup without ID
	
	@PostMapping("/")
	public ResponseEntity<Object> createAddress(@Valid @RequestBody NewAddress address) {
		addressService.sanitizeAddress(address);
		Integer addressId = addressService.createAddress(address);
		if(addressId == null)
			return new ResponseEntity<>(new ErrorResponse("Cannot create address. City not found"), HttpStatus.BAD_REQUEST);
		else
			return new ResponseEntity<>(new NewAddressResponse(addressId), HttpStatus.OK);
	}
	
	@PutMapping("/")
	public ResponseEntity<Object> updateAddress(@Valid @RequestBody Address address) {
		addressService.sanitizeAddress(address);
		if(!addressService.updateAddress(address))
			return new ResponseEntity<>(new ErrorResponse("Cannot Update this Address. Either the address does not exist, or the chosen city does not exist"), HttpStatus.BAD_REQUEST);
		else
			return new ResponseEntity<>(HttpStatus.OK);
	}
}
