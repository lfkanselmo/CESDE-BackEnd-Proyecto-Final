package co.com.cesde.arkham.domain.service;

import co.com.cesde.arkham.domain.Property;
import co.com.cesde.arkham.domain.repository.PropertyRepository;
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

    public Optional<Property> save(Property property) {
        return propertyRepository.save(property);
    }

    public Optional<Property> getById(Integer id) {
        return propertyRepository.getByPropertyId(id);
    }

    public Boolean delete(Integer id) {
        return getById(id).map(property -> {
            propertyRepository.delete(id);
            return true;
        }).orElse(false);
    }

    public Optional<List<Property>> getByDistrict(String districtName) {
        return propertyRepository.getByDistrict(districtName);
    }

    public Optional<List<Property>> getByOwner(Integer ownerId) {
       return propertyRepository.getByOwner(ownerId);
    }

    public Optional<Page<Property>> getAll(Pageable pagination) {
        return propertyRepository.getAll(pagination);
    }

    Optional<List<Property>> getByFree() {
        return propertyRepository.getByFree();
    }
}
