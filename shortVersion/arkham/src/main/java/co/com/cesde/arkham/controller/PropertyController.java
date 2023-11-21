package co.com.cesde.arkham.controller;

import co.com.cesde.arkham.dto.property.PropertyListRecord;
import co.com.cesde.arkham.dto.property.PropertyRegisterRecord;
import co.com.cesde.arkham.dto.property.PropertyReturnRecord;
import co.com.cesde.arkham.dto.property.PropertyUpdateRecord;
import co.com.cesde.arkham.entity.Offer;
import co.com.cesde.arkham.entity.Property;
import co.com.cesde.arkham.entity.PropertyType;
import co.com.cesde.arkham.repository.PropertyRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/property")
public class PropertyController {
    @Autowired
    private PropertyRepository propertyRepository;

    @PostMapping("/save")
    public ResponseEntity<PropertyReturnRecord> save(@RequestBody @Valid PropertyRegisterRecord propertyRegisterRecord,
                                                   UriComponentsBuilder uriComponentsBuilder) {
        Property saved = propertyRepository.save(new Property(propertyRegisterRecord));
        URI url = uriComponentsBuilder.path("/property/{id}").buildAndExpand(saved.getPropertyId()).toUri();
        return ResponseEntity.created(url).body(new PropertyReturnRecord(saved));
    }

    @PutMapping
    public ResponseEntity<PropertyListRecord> update(PropertyUpdateRecord propertyUpdateRecord) {
        Property property = propertyRepository.getReferenceById(propertyUpdateRecord.propertyId());


            if (propertyUpdateRecord.price() != null) {
                property.setPrice(propertyUpdateRecord.price());
            }

            if (propertyUpdateRecord.availability() != null) {
                property.setAvailability(propertyUpdateRecord.availability());
            }

            if (propertyUpdateRecord.ownerId() != null) {
                property.setOwnerId(propertyUpdateRecord.ownerId());
            }

            if (propertyUpdateRecord.offer() != null &&
                    !propertyUpdateRecord.offer().isBlank()) {
                property.setOffer(Offer.valueOf(propertyUpdateRecord.offer()));
            }

            if (propertyUpdateRecord.propertyType() != null &&
                    !propertyUpdateRecord.propertyType().isBlank()) {
                property.setPropertyType(PropertyType.valueOf(propertyUpdateRecord.propertyType()));
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

            Property updated = propertyRepository.save(property);

            return ResponseEntity.ok(new PropertyListRecord(updated));
    }

    @GetMapping
    public ResponseEntity<Page<PropertyListRecord>> getAll(@PageableDefault() Pageable pagination) {
        Page<Property> all = propertyRepository.findAll(pagination);
        Page<PropertyListRecord> allPage = all.map(PropertyListRecord::new);
        return ResponseEntity.ok(allPage);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PropertyListRecord> getById(@PathVariable("id") Long propertyId) {
        Property property = propertyRepository.getReferenceById(propertyId);
        return ResponseEntity.ok(new PropertyListRecord(property));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<PropertyListRecord> delete(@PathVariable("id") Long propertyId) {
        if (propertyRepository.existsById(propertyId)) {
            propertyRepository.deleteById(propertyId);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/location/{districtName}")
    public ResponseEntity<List<PropertyListRecord>> getByDistrict(@PathVariable("districtName") String districtName) {
        List<Property> properties = propertyRepository.getByDistrict(districtName);
        return getReturnsToListRecord(properties);
    }

    @GetMapping("/offer/{offer}")
    public ResponseEntity<List<PropertyListRecord>> getByOffer(@PathVariable("offer") String offer) {
        List<Property> properties = propertyRepository.getByOffer(offer);
        return getReturnsToListRecord(properties);
    }

    @GetMapping("/owner/{id}")
    public ResponseEntity<List<PropertyListRecord>> getByOwner(@PathVariable("id") Long ownerId) {
        List<Property> propertiesOptional = propertyRepository.getByOwnerId(ownerId);
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

