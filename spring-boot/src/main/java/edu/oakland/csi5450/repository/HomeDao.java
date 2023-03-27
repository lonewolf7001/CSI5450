package edu.oakland.csi5450.repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.Types;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;

import edu.oakland.csi5450.bean.Home;
import edu.oakland.csi5450.util.DaoFailedException;

@Repository

public class HomeDao {

    private static final String SELECT_ALL_SQL = "SELECT * FROM home";
    private static final String SELECT_BY_ID_SQL = "SELECT * FROM home WHERE home_id=?";
    private static final String SELECT_BY_CURR_OWNER_ID = "SELECT * FROM home WHERE owner_id=?";
    private static final String SELECT_BY_PREV_OWNER_ID = "SELECT h.* FROM home h, sale s WHERE s.owner_id=? and h.home_id=s.home_id";
    private static final String SELECT_BY_CURR_OWNER_AND_CITY = " SELECT h.* from home h, address a WHERE a.home_id=h.home_id and h.owner_id=? and a.city=?";
    private static final String SELECT_BY_PREV_OWNER_AND_CITY = " SELECT h.* from home h, address a, sale s WHERE s.home_id=h.home_id and a.home_id=h.home_id and s.owner_id= ? and a.city=?";
    
    private static final String INSERT_SQL = "INSERT INTO home(floor_space, num_floors, num_bedrooms, full_bath, half_bath, land_size, year_built, home_type, is_for_sale) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String UPDATE_SQL = "UPDATE home SET floor_space=?, num_floors=?, num_bedrooms=?, full_bath=?, half_bath=?, land_size=?, year_built=?, home_type=?, is_for_sale=? WHERE home_id=?";
    private static final String DELETE_BY_ID_SQL = "DELETE FROM home WHERE home_id=?";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Home> getAll() {
        return jdbcTemplate.query(SELECT_ALL_SQL, new HomeRowMapper());
    }

    public Home getById(Integer homeId) {
        List<Home> result = jdbcTemplate.query(SELECT_BY_ID_SQL, new HomeRowMapper(), homeId);
        return result.isEmpty() ? null : result.get(0);
    }
    
    public List<Home> getHomesByOwner(int owner, boolean includePreviousOwners) {
    	final String query = includePreviousOwners ? SELECT_BY_PREV_OWNER_ID : SELECT_BY_CURR_OWNER_ID;
    	Object[] params = { owner };
    	int[] types = { Types.INTEGER };
    	return jdbcTemplate.query(query,  params, types, new HomeRowMapper());
    }
    public List<Home> getHomesByOwnerAndCity(int owner, String city, boolean includePreviousOwners) {
    	final String query = includePreviousOwners ? SELECT_BY_PREV_OWNER_AND_CITY : SELECT_BY_CURR_OWNER_AND_CITY;
    	Object[] params = { owner, city };
    	int[] types = { Types.INTEGER, Types.CHAR};
    	return jdbcTemplate.query(query,  params, types, new HomeRowMapper());
    }

    public Integer save(Home home) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(java.sql.Connection connection) throws SQLException {
                PreparedStatement pstmt = connection.prepareStatement(INSERT_SQL, Statement.RETURN_GENERATED_KEYS);
                pstmt.setInt(1, home.getFloorSpace());
                pstmt.setShort(2, home.getNumFloors());
                pstmt.setShort(3, home.getNumBedrooms());
                pstmt.setInt(4, home.getFullBaths());
                pstmt.setInt(5, home.getHalfBaths());
                pstmt.setDouble(6, home.getLandSize());
                pstmt.setShort(7, home.getYearBuilt());
                pstmt.setString(8, home.getHomeType());
                pstmt.setBoolean(9, home.getIsForSale());
                return pstmt;
            }
        }, keyHolder);
        Number key = keyHolder.getKey();
        return key != null ? key.intValue() : null;
    }

    public int update(Home home) {
        return jdbcTemplate.update(UPDATE_SQL, home.getFloorSpace(), home.getNumFloors(), home.getNumBedrooms(),
                home.getFullBaths(), home.getHalfBaths(), home.getLandSize(), home.getYearBuilt(), home.getHomeType(),
                home.getIsForSale(), home.getHomeId());
    }

    public int deleteById(Integer homeId) {
        return jdbcTemplate.update(DELETE_BY_ID_SQL, homeId);
    }
    
    public void updateHomeFromSale(Integer homeId, Long ownerId) {
		final String query = "update HOME SET owner_id=?, is_for_sale=false where home_id=?";
		try {
			int result = jdbcTemplate.update(query, ownerId, homeId);
			if (result != 1)
				throw new DaoFailedException("Error when trying to update home to reflect sale");
		} catch(DataAccessException e) {
			throw new DaoFailedException(e);
		}
    }	

    private static class HomeRowMapper implements RowMapper<Home> {

        @Override
        public Home mapRow(ResultSet rs, int rowNum) throws SQLException {
            Home home = new Home();
            home.setHomeId(rs.getInt("home_id"));
            home.setFloorSpace(rs.getInt("floor_space"));
            home.setNumFloors(rs.getShort("num_floors"));
            home.setNumBedrooms(rs.getShort("num_bedrooms"));
            home.setFullBaths(rs.getInt("num_fullbaths"));
            home.setHalfBaths(rs.getInt("num_halfbaths"));
            home.setLandSize(rs.getDouble("land_size"));
            home.setYearBuilt(rs.getShort("year_built"));
            home.setHomeType(rs.getString("home_type"));
            home.setIsForSale(rs.getBoolean("is_for_sale"));
            return home;
        }
    }
}
