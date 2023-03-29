package edu.oakland.csi5450.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import edu.oakland.csi5450.bean.ExtendedHomeInfo;
import edu.oakland.csi5450.bean.Home;
import edu.oakland.csi5450.repository.HomeDao;

@Service
public class HomeService {

    @Autowired
    private HomeDao homeDao;
    
    @Autowired
    private AddressService addressService;

    public List<Home> getAll() {
        return homeDao.getAll();
    }

    public Home getById(Integer homeId) {
        return homeDao.getById(homeId);
    }
    
    public List<Home> getByPriceRange(Integer min, Integer max) {
    	return homeDao.getHomesByPriceRange(min, max);
    }

    public Integer save(Home home) {
        Integer id = homeDao.save(home);
        return id != null ? id.intValue() : null;
    }

    public int update(Home home) {
        return homeDao.update(home);
    }

    public int deleteById(Integer homeId) {
        return homeDao.deleteById(homeId);
    }
    
    public List<ExtendedHomeInfo> getHomesByOwnerAndCity(int owner, @Nullable String city, boolean includePreviousOwners) {
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
    	for(ExtendedHomeInfo item : items) {
    		addressService.trimAddressResult(item.getAddressInfo());
    	}
    }
}
