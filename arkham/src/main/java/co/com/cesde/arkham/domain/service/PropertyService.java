package co.com.cesde.arkham.domain.service;

import co.com.cesde.arkham.domain.Property;
import co.com.cesde.arkham.domain.repository.PropertyRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PropertyService {
    @Autowired
    private PropertyRepository propertyRepository;

    @Transactional
    public Property save(Property property) {
        return propertyRepository.save(property);
    }

    public Optional<Property> getById(Long propertyId) {
        return propertyRepository.getByPropertyId(propertyId);
    }

    @Transactional
    public void delete(Long propertyId) {
        Optional<Property> propertyOptional = propertyRepository.getByPropertyId(propertyId);
        if(propertyOptional.isPresent()){
            Property property = propertyOptional.get();
            property.setActive(false);
            propertyRepository.save(property);
        }
    }

    public List<Property> getByDistrict(String districtName) {
        return propertyRepository.getByDistrict(districtName);
    }

    public List<Property> getByOwner(Long ownerId) {
       return propertyRepository.getByOwner(ownerId);
    }

    public Page<Property> getAll(Pageable pagination) {
        return propertyRepository.getAll(pagination);
    }

    List<Property> getByFree() {
        return propertyRepository.getByavailability();
    }

    @Transactional
    public Property update(Property property) {
        return propertyRepository.save(property);
    }

    public boolean existsById(Long propertyId) {
        return propertyRepository.existsById(propertyId);
    }
}
