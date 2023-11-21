package co.com.cesde.arkham.repository;


import co.com.cesde.arkham.entity.Property;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface PropertyRepository extends JpaRepository<Property, Long> {

    List<Property> getByDistrict(String districtName);

    List<Property> getByOwnerId(Long ownerId);

    List<Property> getByOffer(String offer);
}
