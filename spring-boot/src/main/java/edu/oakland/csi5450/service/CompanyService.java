package edu.oakland.csi5450.service;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.oakland.csi5450.bean.Company;
import edu.oakland.csi5450.bean.NewCompany;
import edu.oakland.csi5450.repository.CompanyDao;

@Service
public class CompanyService
{
	@Autowired
	CompanyDao companyDao;
	
	public List<Company> getCompanies() {
		return companyDao.getCompanies();
	}

	public Company getCompanyById(int id)
	{
		return companyDao.getCompany(id);
	}

	public int createCompany(@Valid NewCompany company)
	{
		company.setName(company.getName().toUpperCase());
		return companyDao.createCompany(company);
	}

	public boolean updateCompany(@Valid Company company)
	{
		company.setName(company.getName().toUpperCase());
		return companyDao.updateCompany(company);
	}
}
