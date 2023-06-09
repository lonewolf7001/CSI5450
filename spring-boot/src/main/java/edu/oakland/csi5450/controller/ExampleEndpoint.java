package edu.oakland.csi5450.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import edu.oakland.csi5450.bean.ExampleRequest;
import edu.oakland.csi5450.bean.HomeOwner;
import edu.oakland.csi5450.service.ExampleService;
import edu.oakland.csi5450.util.DaoFailedException;

@RestController
public class ExampleEndpoint
{
	@Autowired
	ExampleService service;
	
	@PostMapping("/endpoint")
	public HomeOwner getExampleResponse(@RequestBody ExampleRequest request) throws DaoFailedException {
		int ssn = request.getSsn();
		return service.getExampleResponse(ssn);
	}
	@GetMapping("/")
	public Integer test() {
		return 4;
	}

}
