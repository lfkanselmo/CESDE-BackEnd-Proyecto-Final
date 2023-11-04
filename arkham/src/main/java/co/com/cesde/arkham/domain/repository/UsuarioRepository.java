package co.com.cesde.arkham.domain.repository;

import co.com.cesde.arkham.persistence.crud.UsuarioJpaRepository;
import co.com.cesde.arkham.persistence.entity.Usuario;

import java.util.Optional;

public class UsuarioRepository {
    private UsuarioJpaRepository usuarioJpaRepository;

    public void create(Usuario usuario){
        usuarioJpaRepository.save(usuario);
    }

    public void update(Usuario usuario){
        usuarioJpaRepository.save(usuario);
    }

    public void delete(Long id){
        usuarioJpaRepository.deleteById(id);
    }

    public Optional<Usuario> getByUsuario(String usuario){
        return usuarioJpaRepository.findByUsuario(usuario);
    }
}
