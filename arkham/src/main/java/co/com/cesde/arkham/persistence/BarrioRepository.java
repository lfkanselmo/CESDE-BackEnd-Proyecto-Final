package co.com.cesde.arkham.persistence;

import co.com.cesde.arkham.persistence.crud.BarrioJpaRepository;
import co.com.cesde.arkham.persistence.entity.Barrio;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class BarrioRepository {
    private BarrioJpaRepository barrioJpaRepository;

    void create(Barrio barrio){
        barrioJpaRepository.save(barrio);
    }

    void delete(Long id){
        barrioJpaRepository.deleteById(id);
    }

    Optional<Barrio> getBarrioById(Long id){
        return barrioJpaRepository.findById(id);
    }

    List<Barrio> getAll(){
        return (List<Barrio>) barrioJpaRepository.findAll();
    }

    List<Barrio> getByNombre(String nombreBarrio){
        return barrioJpaRepository.findByNombreBarrio(nombreBarrio);
    }
}
