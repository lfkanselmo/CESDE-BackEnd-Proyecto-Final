package co.com.cesde.arkham.domain.repository;

import co.com.cesde.arkham.persistence.crud.BarrioJpaRepository;
import co.com.cesde.arkham.persistence.entity.Barrio;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class BarrioRepository {
    private BarrioJpaRepository barrioJpaRepository;

    public void create(Barrio barrio){
        barrioJpaRepository.save(barrio);
    }

    public void delete(Long id){
        barrioJpaRepository.deleteById(id);
    }

    public Optional<Barrio> getBarrioById(Long id){
        return barrioJpaRepository.findById(id);
    }

    public List<Barrio> getAll(){
        return (List<Barrio>) barrioJpaRepository.findAll();
    }

    public List<Barrio> getByNombre(String nombreBarrio){
        return barrioJpaRepository.findByNombreBarrio(nombreBarrio);
    }
}
