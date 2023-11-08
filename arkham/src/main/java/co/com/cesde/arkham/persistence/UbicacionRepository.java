package co.com.cesde.arkham.persistence;

import co.com.cesde.arkham.domain.District;
import co.com.cesde.arkham.domain.Location;
import co.com.cesde.arkham.domain.repository.LocationRepository;
import co.com.cesde.arkham.persistence.crud.UbicacionJpaRepository;
import co.com.cesde.arkham.persistence.entity.Barrio;
import co.com.cesde.arkham.persistence.entity.Ubicacion;
import co.com.cesde.arkham.persistence.mapper.DistrictMapper;
import co.com.cesde.arkham.persistence.mapper.LocationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public class UbicacionRepository implements LocationRepository {
    @Autowired
    private UbicacionJpaRepository ubicacionJpaRepository;
    @Autowired
    private LocationMapper mapperLocation;
    @Autowired
    private DistrictMapper mapperDistrict;

    @Override
    public Location save(Location location) {
        Ubicacion ubicacion = mapperLocation.toUbicacion(location);
        ubicacionJpaRepository.save(ubicacion);
        return location;
    }

    @Override
    public void delete(Long locationId) {
        ubicacionJpaRepository.deleteById(locationId);
    }

    @Override
    public Optional<Location> findByLocationId(Long locationId) {
        Optional<Ubicacion> ubicacion = ubicacionJpaRepository.findById(locationId);
        return ubicacion.map(ubicacionOpcional -> mapperLocation.toLocation(ubicacionOpcional));
    }

    @Override
    public Optional<List<Location>> finByDistrict(District district) {
        Barrio barrio = mapperDistrict.toBarrio(district);
        List<Ubicacion> ubicaciones = ubicacionJpaRepository.findByBarrio(barrio);
        return Optional.of(ubicaciones
                .stream()
                .map(ubicacionOpcional -> mapperLocation.toLocation(ubicacionOpcional))
                .toList());
    }
}
