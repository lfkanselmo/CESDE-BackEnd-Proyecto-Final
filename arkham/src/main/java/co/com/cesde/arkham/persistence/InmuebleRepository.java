package co.com.cesde.arkham.persistence;

import co.com.cesde.arkham.domain.Location;
import co.com.cesde.arkham.domain.Owner;
import co.com.cesde.arkham.domain.Property;
import co.com.cesde.arkham.domain.repository.PropertyRepository;
import co.com.cesde.arkham.persistence.crud.InmuebleJpaRepository;
import co.com.cesde.arkham.persistence.entity.Inmueble;
import co.com.cesde.arkham.persistence.entity.Propietario;
import co.com.cesde.arkham.persistence.entity.Ubicacion;
import co.com.cesde.arkham.persistence.mapper.LocationMapper;
import co.com.cesde.arkham.persistence.mapper.OwnerMapper;
import co.com.cesde.arkham.persistence.mapper.PropertyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public class InmuebleRepository implements PropertyRepository {
    @Autowired
    private InmuebleJpaRepository inmuebleJpaRepository;
    @Autowired
    private PropertyMapper mapperProperty;
    @Autowired
    private OwnerMapper mapperOwner;
    @Autowired
    private LocationMapper mapperLocation;

    @Override
    public Property save(Property property) {
        Inmueble inmueble = mapperProperty.toInmueble(property);
        return mapperProperty.toProperty(inmuebleJpaRepository.save(inmueble));
    }

    @Override
    public void delete(Integer propertyId) {
        inmuebleJpaRepository.deleteById(propertyId);
    }

    @Override
    public Optional<Property> getById(Integer propertyId) {
        Inmueble inmueble = inmuebleJpaRepository.getById(propertyId);
        return Optional.of(mapperProperty.toProperty(inmueble));
    }

    @Override
    public Optional<Property> getByLocation(Location location) {
        Ubicacion ubicacion = mapperLocation.toUbicacion(location);
        Inmueble inmueble = inmuebleJpaRepository.findByUbicacion(ubicacion);
        return Optional.of(mapperProperty.toProperty(inmueble));
    }

    @Override
    public Optional<List<Property>> getByOwner(Owner owner) {

        Propietario propietario = mapperOwner.toPropietario(owner);

        List<Inmueble> inmuebles = inmuebleJpaRepository.findByPropietario(propietario);

        return Optional.of(inmuebles
                .stream()
                .map(inmueble -> mapperProperty.toProperty(inmueble))
                .toList());
    }

    @Override
    public List<Property> getAll() {
        List<Inmueble> inmuebles = inmuebleJpaRepository.findAll();
        return mapperProperty.toProperties(inmuebles);
    }

    @Override
    public Optional<List<Property>> getByFree() {
        List<Inmueble> inmuebles = inmuebleJpaRepository.findByDisponibilidad(true);
        return Optional.of(mapperProperty.toProperties(inmuebles));
    }
}
