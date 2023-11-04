package co.com.cesde.arkham.domain.repository;

import co.com.cesde.arkham.persistence.crud.UbicacionJpaRepository;
import co.com.cesde.arkham.persistence.entity.Ubicacion;

public class UbicacionRepository {
    private UbicacionJpaRepository ubicacionJpaRepository;

    public void create(Ubicacion ubicacion){
        ubicacionJpaRepository.save(ubicacion);
    }

    public void update(Ubicacion ubicacion){
        ubicacionJpaRepository.save(ubicacion);
    }

    public void delete(Long id){
        ubicacionJpaRepository.deleteById(id);
    }
}
