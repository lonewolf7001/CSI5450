package edu.oakland.csi5450.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.oakland.csi5450.bean.Home;
import edu.oakland.csi5450.repository.HomeDao;

@Service
public class HomeService {

    @Autowired
    private HomeDao homeDao;

    public List<Home> getAll() {
        return homeDao.getAll();
    }

    public Home getById(Integer homeId) {
        return homeDao.getById(homeId);
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
}
