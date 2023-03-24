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

import edu.oakland.csi5450.bean.Address;
import edu.oakland.csi5450.bean.NewAddress;
import edu.oakland.csi5450.util.DaoFailedException;

@Repository
public class AddressDao
{

	@Autowired
	JdbcTemplate jdbcTemplate;

	public Address getAddressById(int id)
	{
		final String query = "SELECT address_id, house_num, street, apt_num, city, county, zip, home_id FROM ADDRESS WHERE address_id=?";
		Object[] params = { id };
		int[] types = { Types.INTEGER };
		try {
			List<Address> result = jdbcTemplate.query(query,  params, types, new RowMapper<Address>(){

				@Override
				public Address mapRow(ResultSet rs, int i) throws SQLException
				{
					Address a = new Address();
					a.setId(rs.getInt("address_id"));
					a.setHouseNum(rs.getInt("house_num"));					
					a.setStreet(rs.getString("street"));
					int aptNum = rs.getInt("apt_num");
					if(!rs.wasNull())
						a.setAptNum(aptNum);//nullable
					a.setCity(rs.getString("city"));
					a.setCounty(rs.getString("county"));
					a.setZip(rs.getInt("zip"));
					int homeId = rs.getInt("home_id");
					if(!rs.wasNull())
						a.setHomeId(homeId);
					return a;
				}
				
			});
			return result.isEmpty() ? null : result.get(0);
		} catch(DataAccessException e) {
			throw new DaoFailedException(e);
		}
	}

	public int createAddress(NewAddress address)
	{
		final String query = "INSERT INTO ADDRESS (house_num, street, apt_num, city, county, zip, home_id) values (?,?,?,?,?,?,?) RETURNING address_id";
		Object[] params = { address.getHouseNum(), address.getStreet(), address.getAptNum(), address.getCity(), address.getCounty(),
				address.getZip(), address.getHomeId() };
		int[] types = { Types.INTEGER, Types.CHAR, Types.SMALLINT, Types.CHAR, Types.CHAR, Types.INTEGER, Types.INTEGER };
		return jdbcTemplate.queryForObject(query,  params, types, Integer.class);
	}

	public void updateAddress(Address address)
	{
		final String query = "UPDATE ADDRESS SET house_num=?, street=?, apt_num=?, city=?, county=?, zip=?, home_id=? WHERE address_id=?";
		try {
			int result = jdbcTemplate.update(query, address.getHouseNum(), address.getStreet(), address.getAptNum(), address.getCity(), address.getCounty(),
					address.getZip(), address.getHomeId(), address.getId());
			if(result != 1)
				throw new DaoFailedException("Error while updating address");
		} catch(DataAccessException e) {
			throw new DaoFailedException(e);
		}
	}

}
