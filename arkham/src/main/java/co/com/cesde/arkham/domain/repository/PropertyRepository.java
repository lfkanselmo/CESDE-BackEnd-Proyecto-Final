package co.com.cesde.arkham.domain.repository;

import co.com.cesde.arkham.domain.Owner;
import co.com.cesde.arkham.domain.Property;

import java.util.List;
import java.util.Optional;

public interface PropertyRepository {
    Optional<Property> save(Property property);

    void delete(Integer propertyId);

    Optional<Property> getByPropertyId(Integer propertyId);

    Optional<List<Property>> getByDistrict(String propertyDistrict);

    Optional<List<Property>> getByOwner(Integer ownerId);

    List<Property> getAll();

    Optional<List<Property>> getByFree();
}
