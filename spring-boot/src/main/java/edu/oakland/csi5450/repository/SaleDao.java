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

import edu.oakland.csi5450.bean.Sale;
import edu.oakland.csi5450.util.DaoFailedException;

@Repository
public class SaleDao {
	@Autowired
	JdbcTemplate jdbcTemplate;

	public void addSale(Sale req) {
		final String query = "insert into SALE (home_id, sale_date, owner_id, agent_id, company_id, price) values (?, ?, ?, ?, ?, ?)";
		try {
			int result = jdbcTemplate.update(query, req.getHomeId(), req.getSaleDate(), req.getOwnerId(),
					req.getAgentId(), req.getCompanyId(), req.getPrice());
			if (result != 1)
				throw new DaoFailedException("Error when trying to sell home");
		} catch (DataAccessException e) {
			throw new DaoFailedException(e);
		}
	}

	public List<Sale> getSalesByHome(int homeId) {
		final String query = "select home_id, sale_date, owner_id, agent_id, company_id, price from SALE where home_id=?";
		try {
			Object[] params = { homeId };
			int[] types = { Types.INTEGER };
			return jdbcTemplate.query(query, params, types, getRowMapper());

		} catch (DataAccessException e) {
			throw new DaoFailedException(e);
		}
	}

	public Sale getMostRecentSale(int homeId) {
		final String query = "select home_id, sale_date, owner_id, agent_id, company_id, price from SALE where home_id=? and sale_date=(select max(sale_date) from SALE where home_id=?)";
		try {
			Object[] params = { homeId, homeId };
			int[] types = { Types.INTEGER, Types.INTEGER };
			List<Sale> result = jdbcTemplate.query(query, params, types, getRowMapper());
			return result.isEmpty() ? null : result.get(0);

		} catch (DataAccessException e) {
			throw new DaoFailedException(e);
		}
	}

	private RowMapper<Sale> getRowMapper() {
		return new RowMapper<Sale>() {
			@Override
			public Sale mapRow(ResultSet rs, int arg1) throws SQLException {
				Sale s = new Sale();
				s.setHomeId(rs.getInt("home_id"));
				s.setSaleDate(rs.getDate("sale_date"));
				s.setOwnerId(rs.getLong("owner_id"));
				s.setAgentId(rs.getInt("agent_id"));
				s.setCompanyId(rs.getInt("company_id"));
				s.setPrice(rs.getInt("price"));
				return s;
			}
		};
	}

	public List<Sale> getAllSales() {
		return null;
	}

	public List<Sale> getSalesByHomeId(int homeId) {
		final String query = "SELECT home_id, sale_date, owner_id, agent_id, company_id, price FROM SALE WHERE home_id=?";
		try {
			Object[] params = { homeId };
			int[] types = { Types.INTEGER };
			return jdbcTemplate.query(query, params, types, getRowMapper());

		} catch (DataAccessException e) {
			throw new DaoFailedException(e);
		}
	}

}
