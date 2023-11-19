package co.com.cesde.arkham.persistence;

import co.com.cesde.arkham.domain.Owner;
import co.com.cesde.arkham.domain.repository.OwnerRepository;
import co.com.cesde.arkham.persistence.crud.PropietarioJpaRepository;
import co.com.cesde.arkham.persistence.entity.Propietario;
import co.com.cesde.arkham.persistence.mapper.OwnerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public class PropietarioRepository implements OwnerRepository {
    @Autowired
    private PropietarioJpaRepository propietarioJpaRepository;
    @Autowired
    private OwnerMapper mapper;

    @Override
    public Owner save(Owner owner) {
        Propietario propietario = mapper.toPropietario(owner);
        return mapper.toOwner(propietarioJpaRepository.save(propietario));
    }

    @Override
    public void delete(Long ownerId) {
        propietarioJpaRepository.deleteById(ownerId);
    }

    @Override
    public Optional<Owner> getByOwnerId(Long ownerId) {
        Optional<Propietario> propietario = propietarioJpaRepository.findById(ownerId);
        return propietario.map(propietarioOpcional -> mapper.toOwner(propietarioOpcional));
    }

    @Override
    public List<Owner> getByOwnerFirstName(String ownerFirstName) {
        List<Propietario> propietarios = propietarioJpaRepository.findByNombrePropietarioAndActivoTrue(ownerFirstName);
        return mapper.toOwners(propietarios);
    }

    @Override
    public Page<Owner> getAll(Pageable pagination) {
        Page<Propietario> propietarios = propietarioJpaRepository.findAll(pagination);
        return propietarios
                .map(propietario -> mapper.toOwner(propietario));
    }

    @Override
    public Boolean existsById(Long ownerId){
        return propietarioJpaRepository.existsById(ownerId);
    }
}
