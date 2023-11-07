package co.com.cesde.arkham.persistence.crud;

import co.com.cesde.arkham.persistence.entity.Barrio;
import co.com.cesde.arkham.persistence.entity.Inmueble;
import co.com.cesde.arkham.persistence.entity.Propietario;
import co.com.cesde.arkham.persistence.entity.Ubicacion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface InmuebleJpaRepository extends JpaRepository<Inmueble,Long> {
    List<Inmueble> findByUbicacion(List<Ubicacion> ubicaciones);

    List<Inmueble> findByPropietario(Propietario propietario);

    List<Inmueble> findByDisponibilidad(boolean b);
}
