package co.com.cesde.arkham.persistence;

import co.com.cesde.arkham.persistence.crud.UbicacionJpaRepository;
import co.com.cesde.arkham.persistence.entity.Ubicacion;

public class UbicacionRepository {
    private UbicacionJpaRepository ubicacionJpaRepository;

    void create(Ubicacion ubicacion){
        ubicacionJpaRepository.save(ubicacion);
    }

    void update(Ubicacion ubicacion){
        ubicacionJpaRepository.save(ubicacion);
    }

    void delete(Long id){
        ubicacionJpaRepository.deleteById(id);
    }
}
