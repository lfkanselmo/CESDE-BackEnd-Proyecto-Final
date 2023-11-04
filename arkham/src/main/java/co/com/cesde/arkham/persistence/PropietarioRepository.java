package co.com.cesde.arkham.persistence;

import co.com.cesde.arkham.persistence.crud.PropietarioJpaRepository;
import co.com.cesde.arkham.persistence.entity.Propietario;

import java.util.List;
import java.util.Optional;

public class PropietarioRepository {
    private PropietarioJpaRepository propietarioJpaRepository;

    void create(Propietario propietario){
        propietarioJpaRepository.save(propietario);
    }

    void update(Propietario propietario){
        propietarioJpaRepository.save(propietario);
    }

    void delete(Long id){
        propietarioJpaRepository.deleteById(id);
    }

    Optional<Propietario> getById(Long id){
        return propietarioJpaRepository.findById(id);
    }

    Optional<List<Propietario>> getByNombre(String nombre){
        return propietarioJpaRepository.findByNombrePropietario(nombre);
    }
}
