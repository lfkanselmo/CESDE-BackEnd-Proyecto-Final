package co.com.cesde.arkham.persistence;

import co.com.cesde.arkham.domain.Property;
import co.com.cesde.arkham.domain.repository.PropertyRepository;
import co.com.cesde.arkham.persistence.crud.InmuebleJpaRepository;
import co.com.cesde.arkham.persistence.entity.Inmueble;
import co.com.cesde.arkham.persistence.mapper.PropertyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    public Property save(Property property) {
        Inmueble inmueble = mapper.toInmueble(property);
        return mapper.toProperty(inmuebleJpaRepository.save(inmueble));
    }

    @Override
    public void delete(Long propertyId) {
        inmuebleJpaRepository.deleteById(propertyId);
    }

    @Override
    public Optional<Property> getByPropertyId(Long propertyId) {
        Optional<Inmueble> inmuebleOptional = inmuebleJpaRepository.getByIdInmuebleAndActivoTrue(propertyId);
        return inmuebleOptional.map(inmueble -> mapper.toProperty(inmueble));
    }

    @Override
    public List<Property> getByDistrict(String district) {
        List<Inmueble> inmuebles = inmuebleJpaRepository.findByBarrioAndActivoTrue(district);
        return mapper.toProperties(inmuebles);
    }

    @Override
    public List<Property> getByOwner(Long ownerId) {
        List<Inmueble> inmuebles = inmuebleJpaRepository.getByIdPropietarioAndActivoTrue(ownerId);
        return mapper.toProperties(inmuebles);
    }


    @Override
    public Page<Property> getAll(Pageable pagination) {
        Page<Inmueble> inmuebles = inmuebleJpaRepository.findAll(pagination);
        return inmuebles.map(inmueble -> mapper.toProperty(inmueble));
    }

    @Override
    public List<Property> getByFree() {
        List<Inmueble> inmuebles = inmuebleJpaRepository.findByDisponibilidadAndActivoTrue(true);
        return mapper.toProperties(inmuebles);
    }

    @Override
    public Boolean existsById(Long ownerId) {
        return inmuebleJpaRepository.existsById(ownerId);
    }
}
