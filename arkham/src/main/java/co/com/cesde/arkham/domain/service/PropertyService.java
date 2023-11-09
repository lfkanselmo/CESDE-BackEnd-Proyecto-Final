package co.com.cesde.arkham.domain.service;

import co.com.cesde.arkham.domain.Location;
import co.com.cesde.arkham.domain.Owner;
import co.com.cesde.arkham.domain.Property;
import co.com.cesde.arkham.domain.repository.PropertyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PropertyService {
    @Autowired
    private PropertyRepository propertyRepository;

    public Property save(Property property){
        return propertyRepository.save(property);
    }

    public Optional<Property> getById(Integer id){
        return propertyRepository.getById(id);
    }

    public Boolean delete(Integer id){
        return getById(id).map(property -> {
         propertyRepository.delete(id);
         return true;
        }).orElse(false);
    }

    public Optional<Property> getByLocation(Location location){
        return propertyRepository.getByLocation(location);
    }

    public Optional<List<Property>> getByOwner(Owner owner){
        return propertyRepository.getByOwner(owner);
    }

    public List<Property> getAll(){
        return propertyRepository.getAll();
    }

    Optional<List<Property>> getByFree(){
        return propertyRepository.getByFree();
    }
}
