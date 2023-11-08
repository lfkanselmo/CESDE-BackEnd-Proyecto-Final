package co.com.cesde.arkham.persistence;

import co.com.cesde.arkham.domain.Owner;
import co.com.cesde.arkham.domain.repository.OwnerRepository;
import co.com.cesde.arkham.persistence.crud.PropietarioJpaRepository;
import co.com.cesde.arkham.persistence.entity.Propietario;
import co.com.cesde.arkham.persistence.mapper.OwnerMapper;
import org.springframework.beans.factory.annotation.Autowired;
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
        propietarioJpaRepository.save(propietario);
        return owner;
    }

    @Override
    public void delete(Long ownerId) {
        propietarioJpaRepository.deleteById(ownerId);
    }

    @Override
    public Optional<Owner> getById(Long id) {
        Optional<Propietario> propietario = propietarioJpaRepository.findById(id);
        return propietario.map(propietarioOpcional -> mapper.toOwner(propietarioOpcional));
    }

    @Override
    public Optional<List<Owner>> getByOwnerFirstName(String ownerFirstName) {
        List<Propietario> propietarios = propietarioJpaRepository.findByNombrePropietario(ownerFirstName);
        return Optional.of(propietarios
                .stream()
                .map(propietario -> mapper.toOwner(propietario))
                .toList());
    }
}
