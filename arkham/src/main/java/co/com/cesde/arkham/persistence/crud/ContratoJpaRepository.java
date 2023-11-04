package co.com.cesde.arkham.persistence.crud;

import co.com.cesde.arkham.persistence.entity.Contrato;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContratoJpaRepository extends JpaRepository<Contrato,Long> {
}
