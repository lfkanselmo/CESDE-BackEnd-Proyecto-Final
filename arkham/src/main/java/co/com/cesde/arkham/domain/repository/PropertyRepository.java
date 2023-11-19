package co.com.cesde.arkham.domain.repository;

import co.com.cesde.arkham.domain.Property;
import co.com.cesde.arkham.persistence.entity.Inmueble;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface PropertyRepository {
    Property save(Property property);

    void delete(Long propertyId);

    Optional<Property> getByPropertyId(Long propertyId);

    List<Property> getByDistrict(String propertyDistrict);

    List<Property> getByOwner(Long ownerId);

    Page<Property> getAll(Pageable pagination);

    List<Property> getByFree();

    Boolean existsById(Long ownerId);
}
