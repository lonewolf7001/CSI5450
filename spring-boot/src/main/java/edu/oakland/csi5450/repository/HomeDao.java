package edu.oakland.csi5450.repository;

import java.sql.Types;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import edu.oakland.csi5450.bean.Address;
import edu.oakland.csi5450.bean.ExtendedHomeInfo;
import edu.oakland.csi5450.bean.Home;
import edu.oakland.csi5450.bean.HomeSearchCriteria;
import edu.oakland.csi5450.bean.HomeWithPrice;
import edu.oakland.csi5450.bean.HomeWithSoldCount;
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
	private static final String CITY_CLAUSE = " and h.city = ?";
    private static final String CRITERIA_SELECT_CLAUSE = "select h.*, (select price from SALE s where s.home_id=h.home_id and s.sale_date=(select max(sale_date) from SALE s2 where s2.home_id=h.home_id)) as latest_price";
    private static final String CRITERIA_FROM_CLAUSE = " from home h";
    
    private static final String QUERY_BY_SOLD_COUNT_SUBQUERY = "select h.home_id, count(s.sale_date) as sales from home h, sale s where h.home_id = s.home_id group by h.home_id ";
    
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Home> getAll() {
        return jdbcTemplate.query(SELECT_ALL_SQL, getDefaultMapper());
    }

    public Home getById(Integer homeId) {
        List<Home> result = jdbcTemplate.query(SELECT_BY_ID_SQL, getDefaultMapper(), homeId);
        return result.isEmpty() ? null : result.get(0);
    }

    /**
     * Either min or max (or both) should be specified. City is optional
     * @param min
     * @param max
     * @param city
     * @return
     */
    public List<HomeWithPrice> getHomesByPriceRange(Integer min, Integer max, String city) {
        StringBuilder sb = new StringBuilder();
        sb.append(QUERY_BY_PRICE_SELECT);
        if (min != null)
            sb.append(PRICE_MIN_CLAUSE);
        if (max != null)
            sb.append(PRICE_MAX_CLAUSE);
        if (city != null)
        	sb.append(CITY_CLAUSE);
        String query = sb.toString();

        List<Object> params = new ArrayList<>();
        List<Integer> typeList = new ArrayList<>();
        if(min != null) {
        	params.add(min);
        	typeList.add(Types.INTEGER);
        }
        if(max != null) {
        	params.add(min);
        	typeList.add(Types.INTEGER);
        }
        if(city != null) {
        	params.add(min);
        	typeList.add(Types.CHAR);
        }
        int[] types = typeList.stream().mapToInt(i->i).toArray();
        return jdbcTemplate.query(query, params.toArray(), types, getHomeWithPriceMapper());
    }
    
    public List<HomeWithSoldCount> getHomesBySoldCount(Integer min, Integer max) {
    	StringBuilder subquery = new StringBuilder(QUERY_BY_SOLD_COUNT_SUBQUERY);
    	if(min != null && max != null)
    		subquery.append(" having count(sale_date) >= ? and count(sale_date) <= ?");
    	else if( min != null)
    		subquery.append(" having count(sale_date) >= ? ");
    	else //max only
    		subquery.append(" having count(sale_date) <= ? ");
    	
    	String query = " select h.*, t.sales from home h, (" + subquery.toString() + ") as t where h.home_id=t.home_id";
    	
    	if(min != null && max != null)
    		return jdbcTemplate.query(query, getHomeWithSoldCountMapper(), min, max);
    	else if(min != null)
    		return jdbcTemplate.query(query, getHomeWithSoldCountMapper(), min);
    	else
    		return jdbcTemplate.query(query, getHomeWithSoldCountMapper(), max);

    }
    
    public List<HomeWithPrice> getHomesByCriteria(HomeSearchCriteria criteria) {
    	List<Object> params = new ArrayList<>();
        List<Integer> typeList = new ArrayList<>();
    	List<String> clauses = new ArrayList<>();
        
        StringBuilder sb = new StringBuilder();
    	sb.append(CRITERIA_SELECT_CLAUSE);
    	sb.append(CRITERIA_FROM_CLAUSE);
    	if(criteria.getCity() != null) {
    		sb.append(" inner join address a on a.home_id=h.home_id ");
    		clauses.add(" a.city = ? ");
    		typeList.add(Types.CHAR);
    		params.add(criteria.getCity());
    	}

    	sb.append(" where ");
    	
    	if(criteria.getFloorSpace() != null) {
    		clauses.add(" h.floor_space = ? ");
    		typeList.add(Types.INTEGER);
    		params.add(criteria.getFloorSpace());
    	}
    	if(criteria.getNumFloors() != null) {
    		clauses.add(" h.num_floors = ? ");
    		typeList.add(Types.SMALLINT);
    		params.add(criteria.getNumFloors());
    	}
    	if(criteria.getNumBedrooms() != null) {
    		clauses.add(" h.num_bedrooms = ? ");
    		typeList.add(Types.SMALLINT);
    		params.add(criteria.getNumBedrooms());
    	}
    	if(criteria.getFullBaths() != null) {
    		clauses.add(" h.num_fullbaths = ? ");
    		typeList.add(Types.SMALLINT);
    		params.add(criteria.getFullBaths());
    	}
    	if(criteria.getHalfBaths() != null) {
    		clauses.add(" h.num_halfbaths = ? ");
    		typeList.add(Types.SMALLINT);
    		params.add(criteria.getHalfBaths());
    	}
    	if(criteria.getLandSize() != null) {
    		clauses.add(" h.land_size = ? ");
    		typeList.add(Types.DECIMAL);
    		params.add(criteria.getLandSize());
    	}
    	if(criteria.getYearBuilt() != null) {
    		clauses.add(" h.year_built = ?");
    		typeList.add(Types.SMALLINT);
    		params.add(criteria.getYearBuilt());
    	}
    	if(criteria.getHomeType() != null) {
    		clauses.add(" h.home_type = ? ");
    		typeList.add(Types.CHAR);
    		params.add(criteria.getHomeType());
    	}
    	if(criteria.getIsForSale() != null) {
    		clauses.add(" h.is_for_sale = ?");
    		typeList.add(Types.BOOLEAN);
    		params.add(criteria.getIsForSale());
    	}
    	
    	sb.append(String.join(" and ", clauses));
    	int[] types = typeList.stream().mapToInt(i->i).toArray();
        return jdbcTemplate.query(sb.toString(), params.toArray(), types, getHomeWithPriceMapper());
    }
    
    public List<Home> getHomesByBedrooms(Short numBedrooms) {
        return jdbcTemplate.query(SELECT_BY_BEDROOMS_SQL, getDefaultMapper(), numBedrooms);
    }

    public List<Home> getHomesByFullBaths(Integer numFullBaths) {
        return jdbcTemplate.query(SELECT_BY_FULLBATHS_SQL, getDefaultMapper(), numFullBaths);
    }

    public List<ExtendedHomeInfo> getHomesByOwner(int owner, boolean includePreviousOwners) {
        StringBuilder sb = new StringBuilder();
        sb.append(EXTENDED_SELECT_CLAUSE);
        sb.append(includePreviousOwners ? QUERY_BY_PREV_OWNER_ID : QUERY_BY_CURR_OWNER_ID);
        Object[] params = { owner };
        int[] types = { Types.INTEGER };
        return jdbcTemplate.query(sb.toString(), params, types, getExtendedMapper());
    }

    public List<ExtendedHomeInfo> getHomesByOwnerAndCity(int owner, String city, boolean includePreviousOwners) {
        StringBuilder sb = new StringBuilder();
        sb.append(EXTENDED_SELECT_CLAUSE);
        sb.append(includePreviousOwners ? QUERY_BY_PREV_OWNER_AND_CITY : QUERY_BY_CURR_OWNER_AND_CITY);
        Object[] params = { owner, city };
        int[] types = { Types.INTEGER, Types.CHAR };
        return jdbcTemplate.query(sb.toString(), params, types, getExtendedMapper());
    }

    public List<ExtendedHomeInfo> getHomesByCity(String city) {
        StringBuilder sb = new StringBuilder();
        sb.append(EXTENDED_SELECT_CLAUSE);
        sb.append(QUERY_BY_CITY);
        Object[] params = { city };
        int[] types = { Types.CHAR };
        return jdbcTemplate.query(sb.toString(), params, types, getExtendedMapper());
    }

    public Integer save(Home home) {
        if (home.getHomeType().equals("APARTMENT") && home.getNumFloors() != null && home.getNumFloors() > 1) {
            throw new InvalidDataException("Apartments cannot have more than one floor.");
        }
        if (home.getHomeType().equals("MANSION") && home.getFloorSpace() < 6000) {
            throw new InvalidDataException("Mansions must have at least 6000 square feet of floor space.");
        }
        return jdbcTemplate.queryForObject(INSERT_SQL, Integer.class, home.getFloorSpace(),
                home.getNumFloors(), home.getNumBedrooms(), home.getFullBaths(), home.getHalfBaths(),
                home.getLandSize(), home.getYearBuilt(), home.getHomeType(), home.getIsForSale());
    }

    // public Integer save(Home home) {
    // query returns newly generated key
    // return jdbcTemplate.queryForObject(INSERT_SQL, Integer.class,
    // home.getFloorSpace(),
    // home.getNumFloors(), home.getNumBedrooms(), home.getFullBaths(),
    // home.getHalfBaths(),
    // home.getLandSize(), home.getYearBuilt(), home.getHomeType(),
    // home.getIsForSale());
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
    // }

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

    private HomeRowMapper<Home> getDefaultMapper() {
    	return new HomeRowMapper<>(Home::new, (h,rs) -> {});
    }
    private HomeRowMapper<HomeWithPrice> getHomeWithPriceMapper() {
    	return new HomeRowMapper<>(HomeWithPrice::new, (h, rs) -> h.setLatestPrice(rs.getInt("latest_price")));
    }
    private HomeRowMapper<HomeWithSoldCount> getHomeWithSoldCountMapper() {
    	return new HomeRowMapper<>(HomeWithSoldCount::new, (h, rs) -> h.setSoldCount(rs.getInt("sales")));
    }
    private HomeRowMapper<ExtendedHomeInfo> getExtendedMapper() {
    	return new HomeRowMapper<>(ExtendedHomeInfo::new, (home, rs) -> {
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
    	});
    }
    
    @FunctionalInterface
    private static interface FieldMapper<T extends Home> {
    	public void accept(T home, ResultSet rs) throws SQLException;
    }
    
    private static class HomeRowMapper<T extends Home> implements RowMapper<T> {
    	private Supplier<T> factory;
    	private FieldMapper<T> additionalFieldMapper;
    	public HomeRowMapper(Supplier<T> factory, FieldMapper<T> additionalFieldMapper) {
    		this.factory = factory;
    		this.additionalFieldMapper = additionalFieldMapper;
    	}
        @Override
        public T mapRow(ResultSet rs, int rowNum) throws SQLException {
            T home = factory.get();
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
            additionalFieldMapper.accept(home, rs);
            return home;
        }
    }
}
