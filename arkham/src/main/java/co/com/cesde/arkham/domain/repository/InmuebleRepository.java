package co.com.cesde.arkham.domain.repository;

import co.com.cesde.arkham.persistence.crud.InmuebleJpaRepository;
import co.com.cesde.arkham.persistence.entity.Inmueble;

import java.util.List;
import java.util.Optional;

public class InmuebleRepository {
    private InmuebleJpaRepository inmuebleJpaRepository;

    public void create(Inmueble inmueble){
        inmuebleJpaRepository.save(inmueble);
    }

    public void update(Inmueble inmueble){
        inmuebleJpaRepository.save(inmueble);
    }

    public void delete(Long id){
        inmuebleJpaRepository.deleteById(id);
    }

    public Optional<List<Inmueble>> getByBarrio(String nombreBarrio){
        return inmuebleJpaRepository.findByNombreBarrio(nombreBarrio);
    }

    public Optional<List<Inmueble>> getByPropietario(Long id){
        return inmuebleJpaRepository.findByIdPropietario(id);
    }

    public List<Inmueble> getAll(){
        return inmuebleJpaRepository.findAll();
    }

    public Optional<List<Inmueble>> getByDisponibilidad(){
        return inmuebleJpaRepository.findByDisponibilidad(true);
    }
}
