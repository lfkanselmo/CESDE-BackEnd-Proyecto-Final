package co.com.cesde.arkham.persistence.crud;

import co.com.cesde.arkham.persistence.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioJpaRepository extends JpaRepository<Usuario,Integer> {
    Optional<Usuario> findByUsuario(String usuario);

    Optional<Usuario> getByIdUsuario(Integer id);
}
