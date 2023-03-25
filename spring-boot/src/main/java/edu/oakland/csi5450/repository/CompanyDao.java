package edu.oakland.csi5450.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import edu.oakland.csi5450.bean.Company;
import edu.oakland.csi5450.bean.NewCompany;
import edu.oakland.csi5450.util.DaoFailedException;

@Repository
public class CompanyDao
{
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	public List<Company> getCompanies() {
		final String query = "SELECT company_id, company_name, commission_rate from REAL_ESTATE_COMPANY order by company_id";
		try {
			return jdbcTemplate.query(query, new RowMapper<Company>(){

				@Override
				public Company mapRow(ResultSet rs, int i) throws SQLException
				{
					Company c = new Company();
					c.setId(rs.getInt("company_id"));
					c.setName(rs.getString("company_name"));
					c.setCommission(rs.getDouble("commission_rate"));
					return c;
				}
				
			});
		} catch(DataAccessException e) {
			throw new DaoFailedException(e);
		}
	}
	
	public Company getCompany(int id) {
		final String query = "SELECT company_id, company_name, commission_rate from REAL_ESTATE_COMPANY where company_id=?";
		Object[] params = { id };
		int[] types = { Types.INTEGER };
		try {
			List<Company> result = jdbcTemplate.query(query, params, types, new RowMapper<Company>(){

				@Override
				public Company mapRow(ResultSet rs, int i) throws SQLException
				{
					Company c = new Company();
					c.setId(rs.getInt("company_id"));
					c.setName(rs.getString("company_name"));
					c.setCommission(rs.getDouble("commission_rate"));
					return c;
				}
				
			});
			return result.isEmpty() ? null : result.get(0);
		} catch(DataAccessException e) {
			throw new DaoFailedException(e);
		}
	}
	
	public int createCompany(NewCompany company) {
		final String query = "INSERT INTO REAL_ESTATE_COMPANY(company_name, commission_rate) VALUES (?,?) returning company_id";
		Object[] params = { company.getName(), company.getCommission() };
		int[] types = {Types.VARCHAR, Types.DECIMAL };
		try{
			return jdbcTemplate.queryForObject(query, params, types, Integer.class);
		} catch(DataAccessException e) {
			throw new DaoFailedException(e);
		}
	}
	
	public void updateCompany(Company company) {
		final String query = "UPDATE REAL_ESTATE_COMPANY SET company_name=?, commission_rate=? WHERE company_id=?";
		try {
			int result = jdbcTemplate.update(query, company.getName(), company.getCommission(), company.getId());
			if(result != 1)
				throw new DaoFailedException("Error occurred while updating real estate company");
		} catch(DataAccessException e) {
			throw new DaoFailedException(e);
		}
	}
}
