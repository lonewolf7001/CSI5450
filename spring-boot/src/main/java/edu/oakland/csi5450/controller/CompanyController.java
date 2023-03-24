package edu.oakland.csi5450.controller;

import java.util.List;

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

import edu.oakland.csi5450.bean.Company;
import edu.oakland.csi5450.bean.ErrorResponse;
import edu.oakland.csi5450.bean.NewCompany;
import edu.oakland.csi5450.bean.NewCompanyResponse;
import edu.oakland.csi5450.service.CompanyService;

@RestController
@RequestMapping("/company")
public class CompanyController
{
	@Autowired
	CompanyService companyService;
	
	@GetMapping("/")
	public List<Company> getCompanies() {
		return companyService.getCompanies();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Object> getCompany(@PathVariable int id) {
		Company company = companyService.getCompanyById(id);
		if(company == null)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		else
			return new ResponseEntity<>(company, HttpStatus.OK);
	}
	
	@PostMapping("/")
	public ResponseEntity<Object> addCompany(@Valid @RequestBody NewCompany company) {
		
		int id = companyService.createCompany(company);
		return new ResponseEntity<>(new NewCompanyResponse(id), HttpStatus.OK);
	}
	
	@PutMapping("/")
	public ResponseEntity<Object> updateCompany(@Valid @RequestBody Company company) {
		if(!companyService.updateCompany(company))
			return new ResponseEntity<>(new ErrorResponse("Cannot Update this Company. Company does not exist."), HttpStatus.BAD_REQUEST);
		else
			return new ResponseEntity<>(HttpStatus.OK);
	}
	

}
