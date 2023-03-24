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
import org.springframework.transaction.annotation.Transactional;

import edu.oakland.csi5450.bean.Appliance;
import edu.oakland.csi5450.util.DaoFailedException;

@Repository
public class ApplianceDao
{
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	public Appliance getApplianceByModelNumber(String modelNumber) {
		final String query = "SELECT model_number, appliance_type, manufacturer, year, price FROM APPLIANCE where model_number=?";
		Object[] params = { modelNumber };
		int[] types = {Types.CHAR};
		try {
			List<Appliance> result = jdbcTemplate.query(query,  params, types, new RowMapper<Appliance>(){
	
				@Override
				public Appliance mapRow(ResultSet rs, int i) throws SQLException
				{
					Appliance a = new Appliance();
					a.setModelNumber(rs.getString("model_number"));
					a.setApplianceType(rs.getString("appliance_type"));
					a.setManufacturer(rs.getString("manufacturer"));
					a.setYear(rs.getInt("year"));
					a.setPrice(rs.getDouble("price"));
					return a;
				}
				
			});
			return result.isEmpty() ? null : result.get(0);
		} catch(DataAccessException e) {
			throw new DaoFailedException(e);
		}
	}
	
	public List<Appliance> getApplianceByTypeAndManufacturer(String type, String manufacturer) {
		final String query = "SELECT model_number, appliance_type, manufacturer, year, price FROM APPLIANCE where appliance_type=? and manufacturer=?";

		Object[] params = { type, manufacturer };
		int[] types = {Types.CHAR, Types.CHAR};
		try {
			return jdbcTemplate.query(query,  params, types, new RowMapper<Appliance>(){
	
				@Override
				public Appliance mapRow(ResultSet rs, int i) throws SQLException
				{
					Appliance a = new Appliance();
					a.setModelNumber(rs.getString("model_number"));
					a.setApplianceType(rs.getString("appliance_type"));
					a.setManufacturer(rs.getString("manufacturer"));
					a.setYear(rs.getInt("year"));
					a.setPrice(rs.getDouble("price"));
					return a;
				}
				
			});
		} catch(DataAccessException e) {
			throw new DaoFailedException(e);
		}
	}
	
	@Transactional
	public boolean createAppliance(Appliance appliance) {
		if(getApplianceByModelNumber(appliance.getModelNumber()) != null)
			return false;
		final String query = "INSERT INTO APPLIANCE(model_number, appliance_type, manufacturer, year, price) VALUES (?, ?, ?, ?, ?)";
		int result = jdbcTemplate.update(query, appliance.getModelNumber(), appliance.getApplianceType(), appliance.getManufacturer(),
				appliance.getYear(), appliance.getPrice());
		if(result != 1)
			throw new DaoFailedException("Error occurred while creating appliance");
		return true;
	}

	@Transactional
	public boolean updateAppliance(Appliance appliance)
	{
		if(getApplianceByModelNumber(appliance.getModelNumber()) == null)
			return false;
		final String query = "UPDATE APPLIANCE SET appliance_type=?, manufacturer=?, price=?, year=? WHERE model_number=?";
		int result = jdbcTemplate.update(query, appliance.getApplianceType(), appliance.getManufacturer(), appliance.getPrice(), 
				appliance.getYear(), appliance.getModelNumber());
		if(result != 1)
			throw new DaoFailedException("Error occurred while updating appliance");
		return true;
	}
	
	
}
