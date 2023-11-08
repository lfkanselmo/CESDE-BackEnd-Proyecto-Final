package co.com.cesde.arkham.domain.repository;


import co.com.cesde.arkham.domain.District;
import co.com.cesde.arkham.domain.Location;

import java.util.List;
import java.util.Optional;

public interface LocationRepository {
    Location save(Location location);

    void delete(Long locationId);

    Optional<Location> findByLocationId(Long locationId);
    Optional<List<Location>> finByDistrict(District district);
}
