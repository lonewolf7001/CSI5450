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

import edu.oakland.csi5450.bean.HomeOwner;
import edu.oakland.csi5450.util.DaoFailedException;

@Repository
public class HomeOwnerDao {
	@Autowired
	JdbcTemplate jdbcTemplate;

	public List<HomeOwner> getHomeOwners() {
		final String query = "SELECT ssn, first_name, last_name, num_dependents, annual_income, date_of_birth, profession, phone, email FROM HOME_OWNER";
		try {
			return jdbcTemplate.query(query, getRowMapper());

		} catch (DataAccessException e) {
			throw new DaoFailedException(e);
		}
	}

	/**
	 * Returns the HomeOwner associated with this id (ssn). Returns null if none is
	 * found.
	 * 
	 * @param id
	 * @return
	 */
	public HomeOwner getHomeOwnerById(long id) {
		final String query = "SELECT ssn, first_name, last_name, num_dependents, annual_income, date_of_birth, profession, phone, email FROM HOME_OWNER WHERE ssn=?";
		Object[] params = { id };
		int[] types = { Types.BIGINT };
		try {
			List<HomeOwner> result = jdbcTemplate.query(query, params, types, getRowMapper());
			return result.isEmpty() ? null : result.get(0);
		} catch (DataAccessException e) {
			throw new DaoFailedException(e);
		}
	}

	public void createHomeOwner(HomeOwner o) {
		final String query = "INSERT INTO HOME_OWNER(ssn, first_name, last_name, num_dependents, annual_income, date_of_birth, profession, phone, email)"
				+ "values (?,?,?,?,?,?,?,?, ?)";
		int result = jdbcTemplate.update(query, o.getSsn(), o.getFirstName(), o.getLastName(), o.getNumDependents(),
				o.getAnnualIncome(),
				o.getDateOfBirth(), o.getProfession(), o.getPhone(), o.getEmail());
		if (result != 1)
			throw new DaoFailedException("Error while creating home owner");
	}

	public void updateHomeOwner(HomeOwner o) {
		final String query = "UPDATE HOME_OWNER SET first_name = ?, last_name = ?, num_dependents = ?, annual_income = ?, "
				+ "date_of_birth = ?, profession = ?, phone = ?, email = ? "
				+ "WHERE ssn = ?";
		int result = jdbcTemplate.update(query, o.getFirstName(), o.getLastName(), o.getNumDependents(),
				o.getAnnualIncome(),
				o.getDateOfBirth(), o.getProfession(), o.getPhone(), o.getEmail(), o.getSsn());
		if (result != 1)
			throw new DaoFailedException("Error while updating home owner");
	}

	private RowMapper<HomeOwner> getRowMapper() {
		return new RowMapper<HomeOwner>() {

			@Override
			public HomeOwner mapRow(ResultSet rs, int i) throws SQLException {
				HomeOwner resp = new HomeOwner();
				resp.setSsn(rs.getInt("ssn"));
				resp.setFirstName(rs.getString("first_name"));
				resp.setLastName(rs.getString("last_name"));
				resp.setNumDependents(rs.getInt("num_dependents"));
				resp.setAnnualIncome(rs.getInt("annual_income"));
				resp.setDateOfBirth(rs.getDate("date_of_birth"));
				resp.setProfession(rs.getString("profession"));
				resp.setPhone(rs.getLong("phone"));
				resp.setEmail(rs.getString("email"));
				return resp;
			}
		};
	}

	public HomeOwner getById(Long ownerId) {
		return null;
	}
}
