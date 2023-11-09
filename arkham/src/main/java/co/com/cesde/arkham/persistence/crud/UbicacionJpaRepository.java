package co.com.cesde.arkham.persistence.crud;

import co.com.cesde.arkham.persistence.entity.Ubicacion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UbicacionJpaRepository extends JpaRepository<Ubicacion,Integer> {

    Ubicacion findByIdUbicacion(Long idUbicacion);
    List<Ubicacion> findByBarrio(String barrio);

}
