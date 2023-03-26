package edu.oakland.csi5450.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import edu.oakland.csi5450.bean.Home;
import edu.oakland.csi5450.service.HomeService;

@RestController
@RequestMapping("/homes")
@Validated
public class HomeController {

    @Autowired
    HomeService homeService;

    @GetMapping("")
    public ResponseEntity<List<Home>> getAllHomes() {
        List<Home> homes = homeService.getAll();
        return new ResponseEntity<>(homes, HttpStatus.OK);
    }

    @GetMapping("/{homeId}")
    public ResponseEntity<Home> getHomeById(@PathVariable Integer homeId) {
        Home home = homeService.getById(homeId);
        if (home != null) {
            return new ResponseEntity<>(home, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("")
    public ResponseEntity<Home> addHome(@Valid @RequestBody Home home) {
        Integer homeId = homeService.save(home);
        if (homeId != null) {
            home.setHomeId(homeId.intValue());
            return new ResponseEntity<>(home, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{homeId}")
    public ResponseEntity<Home> updateHome(@PathVariable Integer homeId, @Valid @RequestBody Home home) {
        Home existingHome = homeService.getById(homeId);
        if (existingHome != null) {
            home.setHomeId(homeId);
            int result = homeService.update(home);
            if (result == 1) {
                return new ResponseEntity<>(home, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{homeId}")
    public ResponseEntity<Void> deleteHome(@PathVariable Integer homeId) {
        int result = homeService.deleteById(homeId);
        if (result == 1) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
