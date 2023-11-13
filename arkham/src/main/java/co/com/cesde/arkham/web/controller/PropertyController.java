package co.com.cesde.arkham.web.controller;

import co.com.cesde.arkham.domain.Property;
import co.com.cesde.arkham.domain.dto.property.PropertyListRecord;
import co.com.cesde.arkham.domain.dto.property.PropertyRegisterRecord;
import co.com.cesde.arkham.domain.service.PropertyService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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
    public ResponseEntity save(@RequestBody @Valid PropertyRegisterRecord propertyRegisterRecord) {
        if(propertyService.save(new Property(propertyRegisterRecord)).isPresent()){
            return new ResponseEntity<>(HttpStatus.CREATED);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<Page<Property>> getAll(@PageableDefault(size = 10) Pageable pagination){
        return propertyService.getAll(pagination)
                .map(properties -> new ResponseEntity<>(properties,HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PropertyListRecord> getById(@PathVariable("id") Integer id) {
        return propertyService.getById(id)
                .map(property -> new ResponseEntity<>(new PropertyListRecord(property), HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
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
    public ResponseEntity<List<PropertyListRecord>> getByDistrict(@PathVariable("districtName") String districtName) {
        Optional<List<Property>> propertiesOptional = propertyService.getByDistrict(districtName);
        return getReturnsToListRecord(propertiesOptional);
    }

    @GetMapping("/owner/{id}")
    public ResponseEntity<List<PropertyListRecord>> getByOwner(@PathVariable("id") Integer ownerId) {
        Optional<List<Property>> propertiesOptional = propertyService.getByOwner(ownerId);
        return getReturnsToListRecord(propertiesOptional);
    }



    private ResponseEntity<List<PropertyListRecord>> getReturnsToListRecord(Optional<List<Property>> propertiesOptional){
        if (propertiesOptional.isPresent()){
            List<Property> propertyList = propertiesOptional.get();
            List<PropertyListRecord> listPropertyRecord = propertyList
                    .stream()
                    .map(PropertyListRecord::new)
                    .toList();

            return new ResponseEntity<>(listPropertyRecord,HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


}

