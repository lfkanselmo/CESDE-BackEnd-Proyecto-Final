package co.com.cesde.arkham.domain.repository;

import co.com.cesde.arkham.domain.District;
import co.com.cesde.arkham.domain.Location;
import co.com.cesde.arkham.domain.Owner;
import co.com.cesde.arkham.domain.Property;

import java.util.List;
import java.util.Optional;

public interface PropertyRepository {
    Property create(Property property);

    Property update(Property property);

    void delete(Long propertyId);

    Optional<List<Property>> getByDistrict(List<Location> locations);

    Optional<List<Property>> getByOwner(Owner owner);

    List<Property> getAll();

    Optional<List<Property>> getByFree();
}
