package co.com.cesde.arkham.domain.repository;

import co.com.cesde.arkham.persistence.crud.PropietarioJpaRepository;
import co.com.cesde.arkham.persistence.entity.Propietario;

import java.util.List;
import java.util.Optional;

public class PropietarioRepository {
    private PropietarioJpaRepository propietarioJpaRepository;

    public void create(Propietario propietario){
        propietarioJpaRepository.save(propietario);
    }

    public void update(Propietario propietario){
        propietarioJpaRepository.save(propietario);
    }

    public void delete(Long id){
        propietarioJpaRepository.deleteById(id);
    }

    public Optional<Propietario> getById(Long id){
        return propietarioJpaRepository.findById(id);
    }

    public Optional<List<Propietario>> getByNombre(String nombre){
        return propietarioJpaRepository.findByNombrePropietario(nombre);
    }
}
