package co.com.cesde.arkham.domain.repository;


import co.com.cesde.arkham.domain.Location;

public interface LocationRepository {
    void create(Location location);

    void update(Location location);

    void delete(Long locationId);
}
