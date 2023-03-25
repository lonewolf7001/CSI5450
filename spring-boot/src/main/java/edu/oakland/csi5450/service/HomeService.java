package edu.oakland.csi5450.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.oakland.csi5450.bean.Home;
import edu.oakland.csi5450.repository.HomeDao;

@Service
public class HomeService {

    // @Autowired
    HomeDao homeDao;
    // List<Home> getAll();

    // public
    public Home getHomeById(long id) {
        return homeDao.getById(id);
    }

    public int save(Home home) {
        return homeDao.save(home);
    }

    public int update(Home home) {
        return homeDao.update(home);
    }

    public int deleteById(long id) {
        return homeDao.deleteById(id);
    }

}
