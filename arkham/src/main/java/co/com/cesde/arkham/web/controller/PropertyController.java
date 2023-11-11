package co.com.cesde.arkham.web.controller;

import co.com.cesde.arkham.domain.Property;
import co.com.cesde.arkham.domain.service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/property")
public class PropertyController {
    @Autowired
    private PropertyService propertyService;

    @PostMapping("/save")
    public ResponseEntity<Property> save(@RequestBody Property property) {
        return propertyService.save(property).map(propertyOptional -> new ResponseEntity<>(propertyOptional,HttpStatus.CREATED))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Property> getById(@PathVariable("id") Integer id) {
        return propertyService.getById(id)
                .map(property -> new ResponseEntity(property, HttpStatus.OK))
                .orElse(new ResponseEntity(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable("id") Integer id) {
        if (propertyService.delete(id)) {
            return new ResponseEntity(HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/location/{districtName}")
    public ResponseEntity<List<Property>> getByDistrict(@PathVariable("districtName") String districtName) {
        return propertyService.getByDistrict(districtName)
                .map(properties -> new ResponseEntity<>(properties,HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/owner/{id}")
    public ResponseEntity<List<Property>> getByOwner(@PathVariable("id") Integer ownerId) {
        Optional<List<Property>> properties = propertyService.getByOwner(ownerId);
        return properties
                .map(propertiesList -> new ResponseEntity<>(propertiesList, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }


}

