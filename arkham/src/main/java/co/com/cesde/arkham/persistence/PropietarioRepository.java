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
    public Optional<Owner> save(Owner owner) {
        Propietario propietario = mapper.toPropietario(owner);
        return Optional.of(mapper.toOwner(propietarioJpaRepository.save(propietario)));
    }

    @Override
    public void delete(Integer ownerId) {
        propietarioJpaRepository.deleteById(ownerId);
    }

    @Override
    public Optional<Owner> getByOwnerId(Integer id) {
        Optional<Propietario> propietario = propietarioJpaRepository.findById(id);
        return propietario.map(propietarioOpcional -> mapper.toOwner(propietarioOpcional));
    }

    @Override
    public Optional<List<Owner>> getByOwnerFirstName(String ownerFirstName) {
        Optional<List<Propietario>> propietariosOptional = propietarioJpaRepository.findByNombrePropietario(ownerFirstName);
        return propietariosOptional.map(propietarios -> mapper.toOwners(propietarios));
    }

    @Override
    public Optional<Page<Owner>> getAll(Pageable pagination) {
        Page<Propietario> propietarios = propietarioJpaRepository.findAll(pagination);
        return Optional.of(propietarios
                .map(propietario -> mapper.toOwner(propietario)));
    }
}
