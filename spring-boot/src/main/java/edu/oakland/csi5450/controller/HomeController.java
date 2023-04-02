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
import edu.oakland.csi5450.bean.HomeSearchCriteria;
import edu.oakland.csi5450.bean.HomeWithApplianceManufacturer;
import edu.oakland.csi5450.bean.HomeWithPrice;
import edu.oakland.csi5450.bean.HomeWithSoldCount;
import edu.oakland.csi5450.bean.NewHomeWithAddress;
import edu.oakland.csi5450.bean.NewHomeWithAddressResponse;
import edu.oakland.csi5450.bean.Sale;
import edu.oakland.csi5450.service.HomeService;
import edu.oakland.csi5450.service.SaleService;
import edu.oakland.csi5450.repository.InvalidDataException;

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

    @GetMapping("/price")
    public ResponseEntity<Object> getHomesByPriceRange(
            @RequestParam(required = false) Integer min,
            @RequestParam(required = false) Integer max,
            @RequestParam(required = false) @Size(max=30) String city) {
        if (min == null && max == null) {
            return new ResponseEntity<>(new ErrorResponse("Either minimum price or maximum price must be specified"),
                    HttpStatus.BAD_REQUEST);
        }
        List<HomeWithPrice> resp = homeService.getByPriceRange(min, max, city);
        if (resp.isEmpty())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        else
            return new ResponseEntity<>(resp, HttpStatus.OK);
    }
    @PostMapping("/criteria")
    public ResponseEntity<Object> getHomesByCriteria(@RequestBody @Valid HomeSearchCriteria criteria) {
    	
    	if(!homeService.validateCriteria(criteria))
    		return new ResponseEntity<>(new ErrorResponse("No criteria specified"), HttpStatus.BAD_REQUEST);
    	List<HomeWithPrice> resp = homeService.getByCriteria(criteria);
        if (resp.isEmpty())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        else
            return new ResponseEntity<>(resp, HttpStatus.OK);
    }
    
    @GetMapping("/soldcount")
    public ResponseEntity<Object> getHomesByNumberOfTimesSold(
            @RequestParam(required = false) Integer min,
            @RequestParam(required = false) Integer max) {
    	if (min == null && max == null) {
            return new ResponseEntity<>(new ErrorResponse("Either minimum or maximum number of times sold must be specified"),
                    HttpStatus.BAD_REQUEST);
        }
        List<HomeWithSoldCount> resp = homeService.getByNumberOfTimesSold(min, max);
        if (resp.isEmpty())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        else
            return new ResponseEntity<>(resp, HttpStatus.OK);
    }
    
    @GetMapping("/singlebrand")
    public List<HomeWithApplianceManufacturer> getSingleBrandHomes() {
    	return homeService.getSingleBrandHomes();
    }

    @GetMapping("/bedrooms/{numBedrooms}")
    public ResponseEntity<List<Home>> getHomesByBedrooms(@PathVariable Short numBedrooms) {
        List<Home> homes = homeService.getHomesByBedrooms(numBedrooms);
        return new ResponseEntity<>(homes, HttpStatus.OK);
    }

    @GetMapping("/fullbaths/{numFullBaths}")
    public ResponseEntity<List<Home>> getHomesByFullBaths(@PathVariable Integer numFullBaths) {
        List<Home> homes = homeService.getHomesByFullBaths(numFullBaths);
        return new ResponseEntity<>(homes, HttpStatus.OK);
    }

    @GetMapping("/owner")
    public ResponseEntity<Object> getHomesByOwner(
            @RequestParam @NotNull @Min(1) int owner,
            @RequestParam(required = false) @Size(max = 30) String city,
            @RequestParam(required = false) boolean prev) {
        List<ExtendedHomeInfo> resp = homeService.getHomesByOwnerAndCity(owner, city, prev);
        if (resp.isEmpty())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        else
            return new ResponseEntity<>(resp, HttpStatus.OK);
    }

    @GetMapping("/city")
    public ResponseEntity<Object> getHomesByCity(@RequestParam @NotNull @Size(min = 1, max = 30) String city) {
        List<ExtendedHomeInfo> resp = homeService.getHomesByCity(city);
        if (resp.isEmpty())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        else
            return new ResponseEntity<>(resp, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Home> addHome(@Valid @RequestBody Home home) {
        Integer homeId = homeService.save(home);
        if (homeId != null) {
            home.setHomeId(homeId);
            return new ResponseEntity<>(home, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/add/address")
    public ResponseEntity<Object> addHomeWithAddress(@Valid @RequestBody NewHomeWithAddress home) {
        NewHomeWithAddressResponse resp = homeService.saveWithAddress(home);
        if (resp == null)
            return new ResponseEntity<>(new ErrorResponse("City does not exist"), HttpStatus.BAD_REQUEST);
        else
            return new ResponseEntity<>(resp, HttpStatus.CREATED);

    }

    @PostMapping
    public ResponseEntity<Integer> saveHome(@RequestBody Home home) {
        if (home.getHomeType().equals("A") && home.getNumFloors() != null && home.getNumFloors() > 1) {
            throw new InvalidDataException("Apartments cannot have more than one floor.");
        }
        if (home.getHomeType().equals("M") && home.getFloorSpace() < 6000) {
            throw new InvalidDataException("Mansions must have at least 6000 square feet of floor space.");
        }
        Integer savedHomeId = homeService.save(home);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedHomeId);
    }

    @PostMapping("/sell")
    public ResponseEntity<Object> sellHome(@Valid @RequestBody Sale req) {
        String errorMessage = saleService.sellHome(req);
        if (errorMessage != null)
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

    @GetMapping("/{id}/former-owners")
    public ResponseEntity<List<String>> getFormerHomeOwners(@PathVariable Integer id) {
        List<String> formerOwners = homeService.getFormerOwners(id);
        if (formerOwners != null && !formerOwners.isEmpty()) {
            return ResponseEntity.ok(formerOwners);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
