package edu.oakland.csi5450.service;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.oakland.csi5450.bean.HomeOwnerResponse;
import edu.oakland.csi5450.repository.ExampleDao;
import edu.oakland.csi5450.util.DaoFailedException;

@Service
public class ExampleService
{
	@Autowired
	ExampleDao dao;
	
	public HomeOwnerResponse getExampleResponse(int ssn) throws DaoFailedException {
		HomeOwnerResponse response = new HomeOwnerResponse();
		response.setSsn(ssn);
		response.setFirstName("DOUG");
		response.setLastName("JUDY");
		response.setDateOfBirth(new Date(1995, 12, 20));
		response.setNumDependents(dao.getDummyValue());
		response.setEmail("dougjudy@oakland.edu");
		response.setPhone(8002225578L);
		response.setProfession("CAR THIEF");
		response.setAnnualIncome(120000);
		return response;
	}

}
