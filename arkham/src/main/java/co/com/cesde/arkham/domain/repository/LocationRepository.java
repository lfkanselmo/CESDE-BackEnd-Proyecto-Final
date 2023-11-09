package co.com.cesde.arkham.domain.repository;


import co.com.cesde.arkham.domain.Location;

import java.util.List;
import java.util.Optional;

public interface LocationRepository {
    Location save(Location location);

    void delete(Integer locationId);

    Optional<Location> getById(Integer locationId);
    Optional<List<Location>> getByDistrict(String district);
}
