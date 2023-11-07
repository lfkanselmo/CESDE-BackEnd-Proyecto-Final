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

import java.util.List;
import java.util.Optional;

public class InmuebleRepository implements PropertyRepository {
    private InmuebleJpaRepository inmuebleJpaRepository;
    private PropertyMapper mapperProperty;
    private OwnerMapper mapperOwner;
    private LocationMapper mapperLocation;

    @Override
    public Property create(Property property) {
        Inmueble inmueble = mapperProperty.toInmueble(property);
        inmuebleJpaRepository.save(inmueble);
        return property;
    }

    @Override
    public Property update(Property property) {
        Inmueble inmueble = mapperProperty.toInmueble(property);
        inmuebleJpaRepository.save(inmueble);
        return property;
    }

    @Override
    public void delete(Long propertyId) {
        inmuebleJpaRepository.deleteById(propertyId);
    }

    @Override
    public Optional<List<Property>> getByDistrict(List<Location> locations) {
        List<Ubicacion> ubicaciones = locations
                .stream()
                .map(l -> mapperLocation.toUbicacion(l))
                .toList();

        List<Inmueble> inmuebles = inmuebleJpaRepository.findByUbicacion(ubicaciones);

        return Optional.of(inmuebles
                .stream()
                .map(i -> mapperProperty.toProperty(i))
                .toList());
    }

    @Override
    public Optional<List<Property>> getByOwner(Owner owner) {

        Propietario propietario = mapperOwner.toPropietario(owner);

        List<Inmueble> inmuebles = inmuebleJpaRepository.findByPropietario(propietario);

        return Optional.of(inmuebles
                .stream()
                .map(i -> mapperProperty.toProperty(i))
                .toList());
    }

    @Override
    public List<Property> getAll() {
        return null;
    }

    @Override
    public Optional<List<Property>> getByFree() {
        return Optional.empty();
    }
}
