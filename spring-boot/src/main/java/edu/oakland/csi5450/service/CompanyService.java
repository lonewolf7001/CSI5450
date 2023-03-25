package edu.oakland.csi5450.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

	@Transactional
	public int createCompany(NewCompany company)
	{
		company.setName(company.getName().toUpperCase());
		return companyDao.createCompany(company);
	}

	@Transactional
	public boolean updateCompany(Company company)
	{
		company.setName(company.getName().toUpperCase());
		if(companyDao.getCompany(company.getId()) == null)
			return false;
		companyDao.updateCompany(company);
		return true;
	}
}
