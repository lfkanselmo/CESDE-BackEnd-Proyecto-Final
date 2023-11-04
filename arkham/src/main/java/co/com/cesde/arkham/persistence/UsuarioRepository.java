package co.com.cesde.arkham.persistence;

import co.com.cesde.arkham.persistence.crud.UsuarioJpaRepository;
import co.com.cesde.arkham.persistence.entity.Usuario;

import java.util.Optional;

public class UsuarioRepository {
    private UsuarioJpaRepository usuarioJpaRepository;

    void create(Usuario usuario){
        usuarioJpaRepository.save(usuario);
    }

    void update(Usuario usuario){
        usuarioJpaRepository.save(usuario);
    }

    void delete(Long id){
        usuarioJpaRepository.deleteById(id);
    }

    Optional<Usuario> getByUsuario(String usuario){
        return usuarioJpaRepository.findByUsuario(usuario);
    }
}
