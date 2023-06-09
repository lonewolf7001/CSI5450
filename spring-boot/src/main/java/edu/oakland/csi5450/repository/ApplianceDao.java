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

import edu.oakland.csi5450.bean.Appliance;
import edu.oakland.csi5450.bean.ApplianceInstance;
import edu.oakland.csi5450.util.DaoFailedException;

@Repository
public class ApplianceDao
{
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	public List<Appliance> getAppliances() {
		final String query = "SELECT model_number, appliance_type, manufacturer, year, price FROM APPLIANCE";
		try {
			return jdbcTemplate.query(query, getRowMapper());
		} catch(DataAccessException e) {
			throw new DaoFailedException(e);
		}
	}
	
	public Appliance getApplianceByModelNumber(String modelNumber) {
		final String query = "SELECT model_number, appliance_type, manufacturer, year, price FROM APPLIANCE where model_number=?";
		Object[] params = { modelNumber };
		int[] types = {Types.CHAR};
		try {
			List<Appliance> result = jdbcTemplate.query(query,  params, types, getRowMapper());
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
			return jdbcTemplate.query(query,  params, types, getRowMapper());
		} catch(DataAccessException e) {
			throw new DaoFailedException(e);
		}
	}
	
	public List<Appliance> getAppliancesByHomeId(int homeId) {
		final String query = "SELECT a.model_number, a.appliance_type, a.manufacturer, a.year, a.price FROM APPLIANCE a, HOME_APPLIANCE_MAPPING m WHERE a.model_number=m.model_number and m.home_id=?";
		Object[] params = { homeId };
		int[] types = {Types.INTEGER};
		try {
			return jdbcTemplate.query(query, params, types, getRowMapper());
		} catch(DataAccessException e) {
			throw new DaoFailedException(e);
		}	
	}
	
	
	public void createAppliance(Appliance appliance) {
		final String query = "INSERT INTO APPLIANCE(model_number, appliance_type, manufacturer, year, price) VALUES (?, ?, ?, ?, ?)";
		int result = jdbcTemplate.update(query, appliance.getModelNumber(), appliance.getApplianceType(), appliance.getManufacturer(),
				appliance.getYear(), appliance.getPrice());
		if(result != 1)
			throw new DaoFailedException("Error occurred while creating appliance");
	}

	public void updateAppliance(Appliance appliance)
	{
		final String query = "UPDATE APPLIANCE SET appliance_type=?, manufacturer=?, price=?, year=? WHERE model_number=?";
		int result = jdbcTemplate.update(query, appliance.getApplianceType(), appliance.getManufacturer(), appliance.getPrice(), 
				appliance.getYear(), appliance.getModelNumber());
		if(result != 1)
			throw new DaoFailedException("Error occurred while updating appliance");
	}
	
	public void addApplianceHome(ApplianceInstance instance) {
		final String query = "INSERT INTO HOME_APPLIANCE_MAPPING (model_number, home_id, serial_number) VALUES (?,?,?)";
		try {
			int result = jdbcTemplate.update(query, instance.getModelNumber(), instance.getHomeId(), instance.getSerialNumber());
			if(result != 1)
				throw new DaoFailedException("Error while adding appliance-home mapping");
		} catch(DataAccessException e) {
			throw new DaoFailedException(e);
		}
	}
	
	public boolean doesApplianceHomeMappingExist(int homeId, String modelNumber) {
		final String query = "SELECT serial_number from HOME_APPLIANCE_MAPPING where model_number=? and home_id=?";
		Object[] params = { modelNumber, homeId };
		int[] types = { Types.CHAR, Types.INTEGER };
		List<Integer> result = jdbcTemplate.query(query,  params, types, new RowMapper<Integer>(){
			@Override
			public Integer mapRow(ResultSet arg0, int arg1) throws SQLException
			{
				return 1;
			}			
		});
		return !result.isEmpty();
	}
	
	private RowMapper<Appliance> getRowMapper() {
		return new RowMapper<Appliance>(){
			
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
		};
	}
}
