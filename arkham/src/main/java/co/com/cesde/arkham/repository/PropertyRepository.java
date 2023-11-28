package co.com.cesde.arkham.repository;


import co.com.cesde.arkham.entity.Property;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface PropertyRepository extends JpaRepository<Property, Long> {

    List<Property> getByDistrict(String districtName);

    List<Property> getByOwnerId(Long ownerId);

    List<Property> getByOffer(String offer);

    Optional<Property> findByAddress(String address);

    @Query(
            """
            update Property p set p.active = false
            where p.propertyId = :propertyId
            """
    )
    void deleteProperty(Long propertyId);
}
