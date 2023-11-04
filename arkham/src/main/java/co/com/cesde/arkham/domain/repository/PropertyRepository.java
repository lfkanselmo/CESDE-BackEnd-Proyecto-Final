package co.com.cesde.arkham.domain.repository;

import co.com.cesde.arkham.domain.Property;

import java.util.List;
import java.util.Optional;

public interface PropertyRepository {
    void create(Property property);

    void update(Property property);

    void delete(Long propertyId);

    Optional<List<Property>> getByDistrict(String districtName);

    Optional<List<Property>> getByOwner(Long ownerId);

    List<Property> getAll();

    Optional<List<Property>> getByFree();
}
