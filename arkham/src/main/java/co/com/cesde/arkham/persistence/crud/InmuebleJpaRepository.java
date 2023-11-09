package co.com.cesde.arkham.persistence.crud;

import co.com.cesde.arkham.persistence.entity.Inmueble;
import co.com.cesde.arkham.persistence.entity.Propietario;
import co.com.cesde.arkham.persistence.entity.Ubicacion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InmuebleJpaRepository extends JpaRepository<Inmueble,Integer> {
    Inmueble findByUbicacion(Ubicacion ubicacion);

    List<Inmueble> findByPropietario(Propietario propietario);

    List<Inmueble> findByDisponibilidad(boolean b);
}
