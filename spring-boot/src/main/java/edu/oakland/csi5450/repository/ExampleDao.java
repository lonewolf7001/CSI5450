package edu.oakland.csi5450.repository;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import edu.oakland.csi5450.util.DaoFailedException;

@Repository
public class ExampleDao
{
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	public int getDummyValue() throws DaoFailedException {
		try {
			return jdbcTemplate.queryForObject("Select 1 as DUMMY", new RowMapper<Integer>() {
				@Override
				public Integer mapRow(ResultSet rs, int rownum) throws SQLException
				{
					return rs.getInt("DUMMY");
				}
		});
		} catch(DataAccessException e) {
			throw new DaoFailedException("example query failed");
		}
		
	}

}
