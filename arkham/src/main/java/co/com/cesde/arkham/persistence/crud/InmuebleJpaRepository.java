package co.com.cesde.arkham.persistence.crud;

import co.com.cesde.arkham.persistence.entity.Inmueble;
import co.com.cesde.arkham.persistence.entity.Propietario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface InmuebleJpaRepository extends JpaRepository<Inmueble,Long> {

    List<Inmueble> findByDisponibilidadAndActivoTrue(Boolean b);

    List<Inmueble> findByBarrioAndActivoTrue(String barrio);

    Optional<Inmueble> getByIdInmuebleAndActivoTrue(Long inmuebleId);

    List<Inmueble> getByIdPropietarioAndActivoTrue(Long propietarioId);
}
