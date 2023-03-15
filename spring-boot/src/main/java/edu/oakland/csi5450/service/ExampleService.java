package edu.oakland.csi5450.service;

import java.sql.Date;

import org.springframework.stereotype.Service;

import edu.oakland.csi5450.bean.HomeOwnerResponse;

@Service
public class ExampleService
{
	public HomeOwnerResponse getExampleResponse(int ssn) {
		HomeOwnerResponse response = new HomeOwnerResponse();
		response.setSsn(ssn);
		response.setFirstName("DOUG");
		response.setLastName("JUDY");
		response.setDateOfBirth(new Date(1995, 12, 20));
		response.setNumDependents(2);
		response.setEmail("dougjudy@oakland.edu");
		response.setPhone(8002225578L);
		response.setProfession("CAR THIEF");
		response.setAnnualIncome(120000);
		return response;
	}

}
