package co.com.cesde.arkham.web.controller;

import co.com.cesde.arkham.domain.Location;
import co.com.cesde.arkham.domain.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/locations")
public class LocationController {
    @Autowired
    private LocationService locationService;

    @PostMapping("/save")
    public ResponseEntity<Location> save(@RequestBody Location location){
        return new ResponseEntity<>(locationService.save(location), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Location> getById(@PathVariable("id") Integer id){
        return locationService.getById(id)
                .map(location -> new ResponseEntity(location,HttpStatus.OK))
                .orElse(new ResponseEntity(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/district/{districtName}")
    public ResponseEntity<List<Location>> getByDistrict(@PathVariable("districtName") String district){
        return locationService.getByDistrict(district)
                .map(locations -> new ResponseEntity(locations,HttpStatus.OK))
                .orElse(new ResponseEntity(HttpStatus.NOT_FOUND));
    }
}
