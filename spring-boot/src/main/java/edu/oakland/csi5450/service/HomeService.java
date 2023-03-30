package edu.oakland.csi5450.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.oakland.csi5450.bean.ExtendedHomeInfo;
import edu.oakland.csi5450.bean.Home;
import edu.oakland.csi5450.bean.NewAddress;
import edu.oakland.csi5450.bean.NewHomeWithAddress;
import edu.oakland.csi5450.bean.NewHomeWithAddressResponse;
import edu.oakland.csi5450.repository.AddressDao;
import edu.oakland.csi5450.repository.CityDao;
import edu.oakland.csi5450.repository.HomeDao;

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

    public List<Home> getByPriceRange(Integer min, Integer max) {
        return homeDao.getHomesByPriceRange(min, max);
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
