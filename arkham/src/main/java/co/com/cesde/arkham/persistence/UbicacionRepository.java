package co.com.cesde.arkham.persistence;

import co.com.cesde.arkham.domain.Location;
import co.com.cesde.arkham.domain.repository.LocationRepository;
import co.com.cesde.arkham.persistence.crud.UbicacionJpaRepository;
import co.com.cesde.arkham.persistence.entity.Ubicacion;
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

    @Override
    public Location save(Location location) {
        Ubicacion ubicacion = mapperLocation.toUbicacion(location);
        return mapperLocation.toLocation(ubicacionJpaRepository.save(ubicacion));
    }

    @Override
    public void delete(Integer locationId) {
        ubicacionJpaRepository.deleteById(locationId);
    }

    @Override
    public Optional<Location> getById(Integer locationId) {
        Optional<Ubicacion> ubicacion = ubicacionJpaRepository.findById(locationId);
        return ubicacion.map(ubicacionOpcional -> mapperLocation.toLocation(ubicacionOpcional));
    }

    @Override
    public Optional<List<Location>> getByDistrict(String district) {
        List<Ubicacion> ubicaciones = ubicacionJpaRepository.findByBarrio(district);
        return Optional.of(ubicaciones
                .stream()
                .map(ubicacionOpcional -> mapperLocation.toLocation(ubicacionOpcional))
                .toList());
    }
}
