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

import edu.oakland.csi5450.bean.City;
import edu.oakland.csi5450.util.DaoFailedException;

@Repository
public class CityDao
{
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	public List<City> getCities() {
		final String query = "SELECT city_name, population FROM CITY ORDER BY city_name";
		try {
			return jdbcTemplate.query(query, new RowMapper<City>(){
	
				@Override
				public City mapRow(ResultSet rs, int i) throws SQLException
				{
					City c = new City();
					c.setName(rs.getString("city_name"));
					c.setPopulation(rs.getInt("population"));
					return c;
				}
				
			});
		} catch(DataAccessException e) {
			throw new DaoFailedException(e);
		}
	}
	
	public City getCity(String name) {
		final String query = "SELECT city_name, population from CITY WHERE city_name=?";
		Object[] params = { name };
		int[] types = { Types.CHAR };
		try {
			List<City> result = jdbcTemplate.query(query,  params, types, new RowMapper<City>(){
	
				@Override
				public City mapRow(ResultSet rs, int i) throws SQLException
				{
					City c = new City();
					c.setName(rs.getString("city_name"));
					c.setPopulation(rs.getInt("population"));
					return c;
				}
				
			});
			
			return result.isEmpty() ? null : result.get(0);
		} catch(DataAccessException e) {
			throw new DaoFailedException(e);
		}
	}
	
	public void createCity(City city) {
		final String query = "INSERT INTO CITY (city_name, population) VALUES (?,?)";
		try {
			int result = jdbcTemplate.update(query, city.getName(), city.getPopulation());
			if(result != 1)
				throw new DaoFailedException("Error occurred while creating city population");
		} catch(DataAccessException e) {
			throw new DaoFailedException(e);
		}
		
	}
	
	public void updateCity(City city) {
		final String query = "UPDATE CITY SET population=? where city_name=?";
		try {
			int result = jdbcTemplate.update(query, city.getPopulation(), city.getName());
			if(result != 1)
				throw new DaoFailedException("Error occurred while updating city population");
		} catch(DataAccessException e) {
			throw new DaoFailedException(e);
		}
	}
}
