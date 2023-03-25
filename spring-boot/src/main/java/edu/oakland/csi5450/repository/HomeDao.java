package edu.oakland.csi5450.repository;

import java.sql.SQLException;
import java.util.List;
import java.sql.ResultSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import edu.oakland.csi5450.bean.Home;

@Repository
public class HomeDao {

    private static final String SELECT_ALL_SQL = "SELECT * FROM home";
    private static final String SELECT_BY_ID_SQL = "SELECT * FROM home WHERE home_id=?";
    private static final String INSERT_SQL = "INSERT INTO home(floor_space, num_floors, num_bedrooms, full_bath, half_bath, land_size, year_built, home_type, is_for_sale) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String UPDATE_SQL = "UPDATE home SET floor_space=?, num_floors=?, num_bedrooms=?, full_bath=?, half_bath=?, land_size=?, year_built=?, home_type=?, is_for_sale=? WHERE home_id=?";
    private static final String DELETE_BY_ID_SQL = "DELETE FROM home WHERE home_id=?";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Home> getAll() {
        return jdbcTemplate.query(SELECT_ALL_SQL, new HomeRowMapper());
    }

    public Home getById(Long homeId) {
        return jdbcTemplate.queryForObject(SELECT_BY_ID_SQL, new HomeRowMapper(), homeId);
    }

    public int save(Home home) {
        return jdbcTemplate.update(INSERT_SQL, home.getFloorSpace(), home.getNumFloors(), home.getNumBedrooms(),
                home.getFullBaths(), home.getHalfBaths(), home.getLandSize(), home.getYearBuilt(), home.getHomeType(),
                home.getIsForSale());
    }

    public int update(Home home) {
        return jdbcTemplate.update(UPDATE_SQL, home.getFloorSpace(), home.getNumFloors(), home.getNumBedrooms(),
                home.getFullBaths(), home.getHalfBaths(), home.getLandSize(), home.getYearBuilt(), home.getHomeType(),
                home.getIsForSale(), home.getHomeId());
    }

    public int deleteById(Long homeId) {
        return jdbcTemplate.update(DELETE_BY_ID_SQL, homeId);
    }

    private static class HomeRowMapper implements RowMapper<Home> {

        @Override
        public Home mapRow(ResultSet rs, int rowNum) throws SQLException {
            Home home = new Home();
            home.setHomeId(rs.getLong("home_id"));
            home.setFloorSpace(rs.getInt("floor_space"));
            home.setNumFloors(rs.getShort("num_floors"));
            home.setNumBedrooms(rs.getShort("num_bedrooms"));
            home.setFullBaths(rs.getInt("full_bath"));
            home.setHalfBaths(rs.getInt("half_bath"));
            home.setLandSize(rs.getDouble("land_size"));
            home.setYearBuilt(rs.getShort("year_built"));
            home.setHomeType(rs.getString("home_type"));
            home.setIsForSale(rs.getBoolean("is_for_sale"));
            return home;
        }

    }

}
