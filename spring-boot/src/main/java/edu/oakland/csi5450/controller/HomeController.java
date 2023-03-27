package edu.oakland.csi5450.controller;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import edu.oakland.csi5450.bean.ErrorResponse;
import edu.oakland.csi5450.bean.ExtendedHomeInfo;
import edu.oakland.csi5450.bean.Home;
import edu.oakland.csi5450.bean.Sale;
import edu.oakland.csi5450.service.HomeService;
import edu.oakland.csi5450.service.SaleService;

@RestController
@RequestMapping("/homes")
@Validated
public class HomeController {

    @Autowired
    HomeService homeService;

    @Autowired
    SaleService saleService;
    
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
    
    @GetMapping("/owner")
    public ResponseEntity<Object> getHomesByOwner(
    		@RequestParam @NotNull @Min(1) int owner, 
    		@RequestParam(required=false) @Size(max=30) String city,
    		@RequestParam(required=false) boolean prev) {
    	List<ExtendedHomeInfo> resp = homeService.getHomesByOwnerAndCity(owner, city, prev);
    	if(resp.isEmpty())
    		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    	else
    		return new ResponseEntity<>(resp, HttpStatus.OK);
    }
    
    @GetMapping("/city")
    public ResponseEntity<Object> getHomesByCity(@RequestParam @NotNull @Size(min=1, max=30) String city) {
    	List<ExtendedHomeInfo> resp = homeService.getHomesByCity(city);
    	if(resp.isEmpty())
    		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    	else
    		return new ResponseEntity<>(resp, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<Home> addHome(@Valid @RequestBody Home home) {
        Integer homeId = homeService.save(home);
        if (homeId != null) {
            home.setHomeId(homeId);
            return new ResponseEntity<>(home, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping("/sell")
    public ResponseEntity<Object> sellHome(@Valid @RequestBody Sale req) {
    	String errorMessage = saleService.sellHome(req);
    	if(errorMessage != null)
    		return new ResponseEntity<>(new ErrorResponse(errorMessage), HttpStatus.BAD_REQUEST);
    	else
    		return new ResponseEntity<>(HttpStatus.OK);
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
