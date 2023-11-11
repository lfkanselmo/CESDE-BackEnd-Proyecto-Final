package co.com.cesde.arkham.persistence;

import co.com.cesde.arkham.domain.User;
import co.com.cesde.arkham.domain.repository.UserRepository;
import co.com.cesde.arkham.persistence.crud.UsuarioJpaRepository;
import co.com.cesde.arkham.persistence.entity.Usuario;
import co.com.cesde.arkham.persistence.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public class UsuarioRepository implements UserRepository {
    @Autowired
    private UsuarioJpaRepository usuarioJpaRepository;
    @Autowired
    private UserMapper mapper;

    @Override
    public Optional<User> save(User user) {
        Usuario usuario = mapper.toUsuario(user);
        return Optional.of(mapper.toUser(usuarioJpaRepository.save(usuario)));
    }

    @Override
    public Optional<User> getByUserId(Integer id) {
        Optional<Usuario> usuarioOptional = usuarioJpaRepository.getByIdUsuario(id);
        return usuarioOptional.map(usuario -> mapper.toUser(usuario));
    }

    @Override
    public void delete(Integer userId) {
        usuarioJpaRepository.deleteById(userId);
    }

    @Override
    public Optional<User> getByUser(String user) {
        Optional<Usuario> usuario = usuarioJpaRepository.findByUsuario(user);
        return usuario.map(usuarioOpcional -> mapper.toUser(usuarioOpcional));
    }
}
