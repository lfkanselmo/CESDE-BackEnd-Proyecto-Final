package co.com.cesde.arkham.persistence;

import co.com.cesde.arkham.persistence.crud.InmuebleJpaRepository;
import co.com.cesde.arkham.persistence.entity.Inmueble;

import java.util.List;
import java.util.Optional;

public class InmuebleRepository {
    private InmuebleJpaRepository inmuebleJpaRepository;

    void create(Inmueble inmueble){
        inmuebleJpaRepository.save(inmueble);
    }

    void update(Inmueble inmueble){
        inmuebleJpaRepository.save(inmueble);
    }

    void delete(Long id){
        inmuebleJpaRepository.deleteById(id);
    }

    Optional<List<Inmueble>> getByBarrio(String nombreBarrio){
        return inmuebleJpaRepository.findByNombreBarrio(nombreBarrio);
    }

    Optional<List<Inmueble>> getByPropietario(Long id){
        return inmuebleJpaRepository.findByIdPropietario(id);
    }

    List<Inmueble> getAll(){
        return inmuebleJpaRepository.findAll();
    }

    Optional<List<Inmueble>> getByDisponibilidad(){
        return inmuebleJpaRepository.findByDisponibilidad(true);
    }
}
