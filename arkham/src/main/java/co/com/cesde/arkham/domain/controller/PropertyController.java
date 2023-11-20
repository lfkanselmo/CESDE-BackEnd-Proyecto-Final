package co.com.cesde.arkham.domain.controller;

import co.com.cesde.arkham.domain.Property;
import co.com.cesde.arkham.domain.dto.property.PropertyListRecord;
import co.com.cesde.arkham.domain.dto.property.PropertyRegisterRecord;
import co.com.cesde.arkham.domain.dto.property.PropertyReturnRecord;
import co.com.cesde.arkham.domain.dto.property.PropertyUpdateRecord;
import co.com.cesde.arkham.domain.service.PropertyService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/property")
public class PropertyController {
    @Autowired
    private PropertyService propertyService;

    @PostMapping("/save")
    public ResponseEntity<PropertyReturnRecord> save(@RequestBody @Valid PropertyRegisterRecord propertyRegisterRecord,
                                                   UriComponentsBuilder uriComponentsBuilder) {
        Property saved = propertyService.save(new Property(propertyRegisterRecord));
        URI url = uriComponentsBuilder.path("/property/{id}").buildAndExpand(saved.getPropertyId()).toUri();
        return ResponseEntity.created(url).body(new PropertyReturnRecord(saved));
    }

    @PutMapping
    public ResponseEntity<PropertyListRecord> update(PropertyUpdateRecord propertyUpdateRecord) {
        Optional<Property> propertyOptional = propertyService.getById(propertyUpdateRecord.propertyId());
        if (propertyOptional.isPresent()) {
            Property property = propertyOptional.get();

            if (propertyUpdateRecord.price() != null) {
                property.setPrice(propertyUpdateRecord.price());
            }

            if (propertyUpdateRecord.free() != null) {
                property.setFree(propertyUpdateRecord.free());
            }

            if (propertyUpdateRecord.ownerId() != null) {
                property.setOwnerId(propertyUpdateRecord.ownerId());
            }

            if (propertyUpdateRecord.offer() != null &&
                    !propertyUpdateRecord.offer().isBlank()) {
                property.setOffer(propertyUpdateRecord.offer());
            }

            if (propertyUpdateRecord.propertyType() != null &&
                    !propertyUpdateRecord.propertyType().isBlank()) {
                property.setPropertyType(propertyUpdateRecord.propertyType());
            }

            if (propertyUpdateRecord.rooms() != null &&
                    propertyUpdateRecord.rooms() > 0) {
                property.setRooms(propertyUpdateRecord.rooms());
            }

            if (propertyUpdateRecord.bathrooms() != null &&
                    propertyUpdateRecord.bathrooms() > 0) {
                property.setBathrooms(propertyUpdateRecord.bathrooms());
            }

            if (propertyUpdateRecord.courtyard() != null) {
                property.setCourtyard(propertyUpdateRecord.courtyard());
            }

            if (propertyUpdateRecord.level() != null &&
                    propertyUpdateRecord.level() > 0) {
                property.setLevel(propertyUpdateRecord.level());
            }

            if (propertyUpdateRecord.area() != null &&
                    propertyUpdateRecord.area() > 0) {
                property.setArea(propertyUpdateRecord.area());
            }

            if (propertyUpdateRecord.naturalGas() != null) {
                property.setNaturalGas(propertyUpdateRecord.naturalGas());
            }

            if (propertyUpdateRecord.laundryArea() != null) {
                property.setLaundryArea(propertyUpdateRecord.laundryArea());
            }

            if (propertyUpdateRecord.address() != null &&
                    !propertyUpdateRecord.address().isBlank()) {
                property.setAddress(propertyUpdateRecord.address());
            }

            if (propertyUpdateRecord.district() != null &&
                    !propertyUpdateRecord.district().isBlank()) {
                property.setDistrict(propertyUpdateRecord.district());
            }

            if (propertyUpdateRecord.city() != null &&
                    !propertyUpdateRecord.city().isBlank()) {
                property.setCity(propertyUpdateRecord.city());
            }

            Property updated = propertyService.update(property);

            return ResponseEntity.ok(new PropertyListRecord(updated));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<Page<PropertyListRecord>> getAll(@PageableDefault() Pageable pagination) {
        Page<Property> all = propertyService.getAll(pagination);
        Page<PropertyListRecord> allPage = all.map(PropertyListRecord::new);
        return ResponseEntity.ok(allPage);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PropertyListRecord> getById(@PathVariable("id") Long propertyId) {
        return propertyService.getById(propertyId)
                .map(property -> ResponseEntity.ok(new PropertyListRecord(property)))
                .orElse(ResponseEntity.noContent().build());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<PropertyListRecord> delete(@PathVariable("id") Long propertyId) {
        if (propertyService.existsById(propertyId)) {
            propertyService.delete(propertyId);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/location/{districtName}")
    public ResponseEntity<List<PropertyListRecord>> getByDistrict(@PathVariable("districtName") String districtName) {
        List<Property> properties = propertyService.getByDistrict(districtName);
        return getReturnsToListRecord(properties);
    }

    @GetMapping("/owner/{id}")
    public ResponseEntity<List<PropertyListRecord>> getByOwner(@PathVariable("id") Long ownerId) {
        List<Property> propertiesOptional = propertyService.getByOwner(ownerId);
        return getReturnsToListRecord(propertiesOptional);
    }


    private ResponseEntity<List<PropertyListRecord>> getReturnsToListRecord(List<Property> properties) {
        if (!properties.isEmpty()) {
            List<PropertyListRecord> listPropertyRecord = properties
                    .stream()
                    .map(PropertyListRecord::new)
                    .toList();

            return ResponseEntity.ok(listPropertyRecord);
        } else {
            return ResponseEntity.noContent().build();
        }
    }


}

