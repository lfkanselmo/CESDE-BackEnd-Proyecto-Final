package co.com.cesde.arkham.domain.repository;


import co.com.cesde.arkham.domain.Location;

public interface LocationRepository {
    Location create(Location location);

    Location update(Location location);

    void delete(Long locationId);
}
