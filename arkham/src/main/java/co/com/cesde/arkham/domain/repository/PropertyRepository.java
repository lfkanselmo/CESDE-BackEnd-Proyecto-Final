package co.com.cesde.arkham.domain.repository;

import co.com.cesde.arkham.domain.Property;
import co.com.cesde.arkham.persistence.entity.Inmueble;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface PropertyRepository {
    Optional<Property> save(Property property);

    void delete(Integer propertyId);

    Optional<Property> getByPropertyId(Integer propertyId);

    Optional<List<Property>> getByDistrict(String propertyDistrict);

    Optional<List<Property>> getByOwner(Integer ownerId);

    Optional<Page<Property>> getAll(Pageable pagination);

    Optional<List<Property>> getByFree();
}
