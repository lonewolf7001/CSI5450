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
//import org.springframework.jdbc.core.PreparedStatementCreator;
//import org.springframework.jdbc.support.GeneratedKeyHolder;
//import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import edu.oakland.csi5450.bean.Address;
import edu.oakland.csi5450.bean.ExtendedHomeInfo;
import edu.oakland.csi5450.bean.Home;
import edu.oakland.csi5450.util.DaoFailedException;

@Repository

public class HomeDao {

    private static final String SELECT_ALL_SQL = "SELECT * FROM home";

    private static final String SELECT_BY_ID_SQL = "SELECT * FROM home WHERE home_id=?";
    private static final String INSERT_SQL = "INSERT INTO home(floor_space, num_floors, num_bedrooms, num_fullbaths, num_halfbaths, land_size, year_built, home_type, is_for_sale) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?) returning home_id";
    private static final String UPDATE_SQL = "UPDATE home SET floor_space=?, num_floors=?, num_bedrooms=?, num_fullbaths=?, num_halfbaths=?, land_size=?, year_built=?, home_type=?, is_for_sale=? WHERE home_id=?";
    private static final String DELETE_BY_ID_SQL = "DELETE FROM home WHERE home_id=?";

    private static final String SELECT_BY_BEDROOMS_SQL = "SELECT * FROM home WHERE num_bedrooms = ?";
    private static final String SELECT_BY_FULLBATHS_SQL = "SELECT * FROM home WHERE num_fullbaths = ?";

    private static final String EXTENDED_SELECT_CLAUSE = "select h.home_id, h.floor_space, h.num_floors, h.num_bedrooms, h.num_fullbaths, h.num_halfbaths, h.land_size, h.year_built, h.home_type, h.is_for_sale, h.owner_id,"
            + " a.address_id, a.house_num, a.street, a.apt_num, a.city, a.county, a.zip, "
            + " (select price from SALE s where s.home_id=h.home_id and s.sale_date=(select max(sale_date) from SALE s2 where s2.home_id=h.home_id)) as latest_price";
    private static final String QUERY_BY_CURR_OWNER_ID = " from HOME h, ADDRESS a"
            + " where h.home_id = a.home_id and h.owner_id=?";
    private static final String QUERY_BY_PREV_OWNER_ID = " from HOME h, ADDRESS a, SALE s0"
            + " where h.home_id = a.home_id and s0.home_id=h.home_id and h.owner_id=?";
    private static final String QUERY_BY_CURR_OWNER_AND_CITY = " from HOME h, ADDRESS a"
            + " where h.home_id = a.home_id and h.owner_id=? and a.city=?";
    private static final String QUERY_BY_PREV_OWNER_AND_CITY = " from HOME h, ADDRESS a, SALE s0"
            + " where h.home_id = a.home_id and s0.home_id=h.home_id and h.owner_id=? and a.city=?";
    private static final String QUERY_BY_CITY = " from HOME h, ADDRESS a"
            + " where h.home_id = a.home_id and a.city=?";

    private static final String QUERY_BY_PRICE_SELECT = "select h.* from home h, (select h.home_id, s.price from home h, sale s where s.home_id=h.home_id and s.sale_date= (select max(sale_date) from SALE s2 where s2.home_id=h.home_id)) as home_price where h.home_id = home_price.home_id ";
    private static final String PRICE_MAX_CLAUSE = " and home_price.price < ? ";
    private static final String PRICE_MIN_CLAUSE = " and home_price.price > ? ";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Home> getAll() {
        return jdbcTemplate.query(SELECT_ALL_SQL, new HomeRowMapper());
    }

    public Home getById(Integer homeId) {
        List<Home> result = jdbcTemplate.query(SELECT_BY_ID_SQL, new HomeRowMapper(), homeId);
        return result.isEmpty() ? null : result.get(0);
    }

    public List<Home> getHomesByPriceRange(Integer min, Integer max) {
        StringBuilder sb = new StringBuilder();
        sb.append(QUERY_BY_PRICE_SELECT);
        if (min != null)
            sb.append(PRICE_MIN_CLAUSE);
        if (max != null)
            sb.append(PRICE_MAX_CLAUSE);
        String query = sb.toString();

        if (min == null)
            return jdbcTemplate.query(query, new HomeRowMapper(), max);
        else if (max == null)
            return jdbcTemplate.query(query, new HomeRowMapper(), min);
        else
            return jdbcTemplate.query(query, new HomeRowMapper(), min, max);
    }

    public List<Home> getHomesByBedrooms(Short numBedrooms) {
        return jdbcTemplate.query(SELECT_BY_BEDROOMS_SQL, new HomeRowMapper(), numBedrooms);
    }

    public List<Home> getHomesByFullBaths(Integer numFullBaths) {
        return jdbcTemplate.query(SELECT_BY_FULLBATHS_SQL, new HomeRowMapper(), numFullBaths);
    }

    public List<ExtendedHomeInfo> getHomesByOwner(int owner, boolean includePreviousOwners) {
        StringBuilder sb = new StringBuilder();
        sb.append(EXTENDED_SELECT_CLAUSE);
        sb.append(includePreviousOwners ? QUERY_BY_PREV_OWNER_ID : QUERY_BY_CURR_OWNER_ID);
        Object[] params = { owner };
        int[] types = { Types.INTEGER };
        return jdbcTemplate.query(sb.toString(), params, types, new ExtendedHomeInfoRowMapper());
    }

    public List<ExtendedHomeInfo> getHomesByOwnerAndCity(int owner, String city, boolean includePreviousOwners) {
        StringBuilder sb = new StringBuilder();
        sb.append(EXTENDED_SELECT_CLAUSE);
        sb.append(includePreviousOwners ? QUERY_BY_PREV_OWNER_AND_CITY : QUERY_BY_CURR_OWNER_AND_CITY);
        Object[] params = { owner, city };
        int[] types = { Types.INTEGER, Types.CHAR };
        return jdbcTemplate.query(sb.toString(), params, types, new ExtendedHomeInfoRowMapper());
    }

    public List<ExtendedHomeInfo> getHomesByCity(String city) {
        StringBuilder sb = new StringBuilder();
        sb.append(EXTENDED_SELECT_CLAUSE);
        sb.append(QUERY_BY_CITY);
        Object[] params = { city };
        int[] types = { Types.CHAR };
        return jdbcTemplate.query(sb.toString(), params, types, new ExtendedHomeInfoRowMapper());
    }

    public Integer save(Home home) {
        // query returns newly generated key
        return jdbcTemplate.queryForObject(INSERT_SQL, Integer.class, home.getFloorSpace(),
                home.getNumFloors(), home.getNumBedrooms(), home.getFullBaths(), home.getHalfBaths(),
                home.getLandSize(), home.getYearBuilt(), home.getHomeType(), home.getIsForSale());
        // KeyHolder keyHolder = new GeneratedKeyHolder();
        // jdbcTemplate.update(new PreparedStatementCreator() {
        // @Override
        // public PreparedStatement createPreparedStatement(java.sql.Connection
        // connection) throws SQLException {
        // PreparedStatement pstmt = connection.prepareStatement(INSERT_SQL,
        // Statement.RETURN_GENERATED_KEYS);
        // pstmt.setInt(1, home.getFloorSpace());
        // pstmt.setShort(2, home.getNumFloors());
        // pstmt.setShort(3, home.getNumBedrooms());
        // pstmt.setInt(4, home.getFullBaths());
        // pstmt.setInt(5, home.getHalfBaths());
        // pstmt.setDouble(6, home.getLandSize());
        // pstmt.setShort(7, home.getYearBuilt());
        // pstmt.setString(8, home.getHomeType());
        // pstmt.setBoolean(9, home.getIsForSale());
        // return pstmt;
        // }
        // }, keyHolder);
        // Number key = keyHolder.getKey();
        // return key != null ? key.intValue() : null;
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
        } catch (DataAccessException e) {
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

    private static class ExtendedHomeInfoRowMapper implements RowMapper<ExtendedHomeInfo> {
        @Override
        public ExtendedHomeInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
            ExtendedHomeInfo home = new ExtendedHomeInfo();
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
            home.setCurrentOwner(rs.getLong("owner_id"));

            Address a = new Address();
            a.setId(rs.getInt("address_id"));
            a.setHouseNum(rs.getInt("house_num"));
            a.setStreet(rs.getString("street"));
            int aptNum = rs.getInt("apt_num");
            if (!rs.wasNull())
                a.setAptNum(aptNum);// nullable
            a.setCity(rs.getString("city"));
            a.setCounty(rs.getString("county"));
            a.setZip(rs.getInt("zip"));
            int homeId = rs.getInt("home_id");
            if (!rs.wasNull())
                a.setHomeId(homeId);

            home.setAddressInfo(a);

            home.setLatestPrice(rs.getInt("latest_price"));
            return home;
        }
    }
}
