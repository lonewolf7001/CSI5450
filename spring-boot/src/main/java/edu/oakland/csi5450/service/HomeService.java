package edu.oakland.csi5450.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.oakland.csi5450.bean.ExtendedHomeInfo;
import edu.oakland.csi5450.bean.Home;
import edu.oakland.csi5450.bean.HomeSearchCriteria;
import edu.oakland.csi5450.bean.HomeWithApplianceManufacturer;
import edu.oakland.csi5450.bean.HomeWithPrice;
import edu.oakland.csi5450.bean.HomeWithSoldCount;
import edu.oakland.csi5450.bean.NewAddress;
import edu.oakland.csi5450.bean.NewHomeWithAddress;
import edu.oakland.csi5450.bean.NewHomeWithAddressResponse;
import edu.oakland.csi5450.repository.AddressDao;
import edu.oakland.csi5450.repository.CityDao;
import edu.oakland.csi5450.repository.HomeDao;
import edu.oakland.csi5450.repository.InvalidDataException;

@Service
public class HomeService {

    @Autowired
    private HomeDao homeDao;

    @Autowired
    private AddressService addressService;

    @Autowired
    private AddressDao addressDao;

    @Autowired
    private CityDao cityDao;

    public List<Home> getAll() {
        return homeDao.getAll();
    }

    public Home getById(Integer homeId) {
        return homeDao.getById(homeId);
    }
    
    public List<HomeWithApplianceManufacturer> getSingleBrandHomes() {
    	List<HomeWithApplianceManufacturer> resp = homeDao.getHomesWithSameApplianceManufacturer();
    	for(HomeWithApplianceManufacturer home : resp)
    		home.setManufacturer(home.getManufacturer().trim());
    	return resp;
    }

    public List<HomeWithPrice> getByPriceRange(Integer min, Integer max, String city) {
    	String sanitizedCity = city == null ? null : city.toUpperCase();
        return homeDao.getHomesByPriceRange(min, max, sanitizedCity);
    }
    
    public List<HomeWithPrice> getByCriteria(HomeSearchCriteria criteria) {
    	//sanitize input
    	criteria.setCity(criteria.getCity() == null ? null : criteria.getCity().toUpperCase());
    	criteria.setHomeType(criteria.getHomeType() == null ? null : criteria.getHomeType().toUpperCase());
    	
    	return homeDao.getHomesByCriteria(criteria);
    }
    
    public List<HomeWithSoldCount> getByNumberOfTimesSold(Integer min, Integer max) {
    	return homeDao.getHomesBySoldCount(min, max);
    }
    
    /**
     * returns true if criteria are valid, false if not specified
     * @param criteria
     * @return
     */
    public boolean validateCriteria(HomeSearchCriteria criteria) {
    	return criteria.getCity() != null || criteria.getFloorSpace() != null || criteria.getNumFloors() != null 
    			|| criteria.getNumBedrooms() != null || criteria.getFullBaths() != null || criteria.getHalfBaths() != null
    			|| criteria.getLandSize() != null || criteria.getYearBuilt() != null || criteria.getHomeType() != null
    			|| criteria.getIsForSale() != null;
    }

    @Transactional
    public Integer saveHome(Home home) {
        if (home.getHomeType().equals("APARTMENT") && home.getNumFloors() != null && home.getNumFloors() > 1) {
            throw new InvalidDataException("Apartments cannot have more than one floor.");
        }
        if (home.getHomeType().equals("MANSION") && home.getFloorSpace() < 6000) {
            throw new InvalidDataException("Mansions must have at least 6000 square feet of floor space.");
        }
        
        return homeDao.save(home);
    }

    @Transactional
    /**
     * @param home
     * @return null if the city does not exist
     */
    public NewHomeWithAddressResponse saveWithAddress(NewHomeWithAddress home) {
        sanitizeHomeWithAddress(home);
        if (cityDao.getCity(home.getAddress().getCity()) == null)
            return null;
        Integer homeId = homeDao.save(home);
        NewAddress address = home.getAddress();
        address.setHomeId(homeId);
        Integer addressId = addressDao.createAddress(address);
        NewHomeWithAddressResponse response = new NewHomeWithAddressResponse();
        response.setAddressId(addressId);
        response.setHomeId(homeId);
        return response;
    }

    @Transactional
    public Integer save(Home home) {
        Integer id = homeDao.save(home);
        return id != null ? id.intValue() : null;
    }

    @Transactional
    public int update(Home home) {
        return homeDao.update(home);
    }

    public int deleteById(Integer homeId) {
        return homeDao.deleteById(homeId);
    }

    public List<Home> getHomesByBedrooms(Short numBedrooms) {
        return homeDao.getHomesByBedrooms(numBedrooms);
    }

    public List<Home> getHomesByFullBaths(Integer numFullBaths) {
        return homeDao.getHomesByFullBaths(numFullBaths);
    }

    public List<ExtendedHomeInfo> getHomesByOwnerAndCity(int owner, @Nullable String city,
            boolean includePreviousOwners) {
        List<ExtendedHomeInfo> result = (city == null)
                ? homeDao.getHomesByOwner(owner, includePreviousOwners)
                : homeDao.getHomesByOwnerAndCity(owner, city.toUpperCase(), includePreviousOwners);
        trimExtendedHomeResponse(result);
        return result;
    }

    public List<ExtendedHomeInfo> getHomesByCity(String city) {
        List<ExtendedHomeInfo> result = homeDao.getHomesByCity(city.toUpperCase());
        trimExtendedHomeResponse(result);
        return result;
    }

    public void trimExtendedHomeResponse(List<ExtendedHomeInfo> items) {
        for (ExtendedHomeInfo item : items) {
            addressService.trimAddressResult(item.getAddressInfo());
        }
    }

    public void sanitizeHomeWithAddress(NewHomeWithAddress home) {
        addressService.sanitizeAddress(home.getAddress());
        home.setHomeType(home.getHomeType().toUpperCase());
    }
}
