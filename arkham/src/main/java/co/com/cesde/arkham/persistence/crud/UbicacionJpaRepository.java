package co.com.cesde.arkham.persistence.crud;

import co.com.cesde.arkham.persistence.entity.Ubicacion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UbicacionJpaRepository extends JpaRepository<Ubicacion,Long> {

    Ubicacion findByIdUbicacion(Long idUbicacion);

}
