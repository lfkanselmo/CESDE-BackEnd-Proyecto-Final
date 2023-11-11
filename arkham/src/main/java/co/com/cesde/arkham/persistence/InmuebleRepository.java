package co.com.cesde.arkham.persistence;

import co.com.cesde.arkham.domain.Property;
import co.com.cesde.arkham.domain.repository.PropertyRepository;
import co.com.cesde.arkham.persistence.crud.InmuebleJpaRepository;
import co.com.cesde.arkham.persistence.entity.Inmueble;
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
    private PropertyMapper mapper;

    @Override
    public Optional<Property> save(Property property) {
        Inmueble inmueble = mapper.toInmueble(property);
        return Optional.of(mapper.toProperty(inmuebleJpaRepository.save(inmueble)));
    }

    @Override
    public void delete(Integer propertyId) {
        inmuebleJpaRepository.deleteById(propertyId);
    }

    @Override
    public Optional<Property> getByPropertyId(Integer propertyId) {
        Optional<Inmueble> inmuebleOptional = inmuebleJpaRepository.getByIdInmueble(propertyId);
        return inmuebleOptional.map(inmueble -> mapper.toProperty(inmueble));
    }

    @Override
    public Optional<List<Property>> getByDistrict(String district) {
        Optional<List<Inmueble>> inmueblesOptional = inmuebleJpaRepository.findByBarrio(district);
        return inmueblesOptional.map(inmuebles -> mapper.toProperties(inmuebles));
    }

    @Override
    public Optional<List<Property>> getByOwner(Integer ownerId) {
        Optional<List<Inmueble>> inmueblesOptional = inmuebleJpaRepository.getByIdPropietario(ownerId);
        return inmueblesOptional.map(inmuebles -> mapper.toProperties(inmuebles));
    }

    @Override
    public List<Property> getAll() {
        List<Inmueble> inmuebles = inmuebleJpaRepository.findAll();
        return mapper.toProperties(inmuebles);
    }

    @Override
    public Optional<List<Property>> getByFree() {
        Optional<List<Inmueble>> inmueblesOptional = inmuebleJpaRepository.findByDisponibilidad(true);
        return inmueblesOptional.map(inmuebles -> mapper.toProperties(inmuebles));
    }
}
