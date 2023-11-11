package co.com.cesde.arkham.domain.service;

import co.com.cesde.arkham.domain.Location;
import co.com.cesde.arkham.domain.Owner;
import co.com.cesde.arkham.domain.Property;
import co.com.cesde.arkham.domain.repository.LocationRepository;
import co.com.cesde.arkham.domain.repository.OwnerRepository;
import co.com.cesde.arkham.domain.repository.PropertyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PropertyService {
    @Autowired
    private PropertyRepository propertyRepository;
    @Autowired
    private OwnerRepository ownerRepository;
    @Autowired
    private LocationRepository locationRepository;

    public Property save(Property property) {
        return propertyRepository.save(property);
    }

    public Optional<Property> getById(Integer id) {
        return propertyRepository.getById(id);
    }

    public Boolean delete(Integer id) {
        return getById(id).map(property -> {
            propertyRepository.delete(id);
            return true;
        }).orElse(false);
    }

    public Optional<List<Property>> getByLocation(String districtName) {
        Optional<List<Location>> locationsList = locationRepository.getByDistrict(districtName);
        if (locationsList.isPresent()) {
            List<Location> locations = locationsList.get();
            List<Property> properties = null;
            locations.forEach(location -> {
                Optional<Property> propertyOptional = propertyRepository.getByLocation(location);
                propertyOptional.ifPresent(property -> properties.add(property));
            });

            return Optional.of(properties);
        }

        return Optional.empty();
    }

    public Optional<List<Property>> getByOwner(Integer ownerId) {
        Optional<Owner> ownerOptional = ownerRepository.getById(ownerId);
        if(ownerOptional.isPresent()){
            Owner owner = ownerOptional.get();
            return propertyRepository.getByOwner(owner);
        }
        return Optional.empty();
    }

    public List<Property> getAll() {
        return propertyRepository.getAll();
    }

    Optional<List<Property>> getByFree() {
        return propertyRepository.getByFree();
    }
}
