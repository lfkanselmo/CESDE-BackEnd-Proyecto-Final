package co.com.cesde.arkham.persistence;

import co.com.cesde.arkham.domain.Administrator;
import co.com.cesde.arkham.domain.repository.AdministratorRepository;
import co.com.cesde.arkham.persistence.crud.AdministradorJpaRepository;
import co.com.cesde.arkham.persistence.entity.Administrador;
import co.com.cesde.arkham.persistence.mapper.AdministratorMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class AdministradorRepository implements AdministratorRepository {
    @Autowired
    private AdministradorJpaRepository administradorJpaRepository;
    @Autowired
    private AdministratorMapper mapper;


    @Override
    public Administrator save(Administrator administrator) {
        Administrador administrador = mapper.toAdministrador(administrator);
        return mapper.toAdministrator(administradorJpaRepository.save(administrador));
    }

    @Override
    public void delete(Integer administratorId) {
        administradorJpaRepository.deleteById(administratorId);
    }

    @Override
    public Optional<List<Administrator>> getByAdministratorFirstName(String administratorFirstName) {
        List<Administrador> administradores = administradorJpaRepository.findByNombreAdministrador(administratorFirstName);
        return Optional.of(mapper.toAdministrators(administradores));
    }

    @Override
    public Optional<Administrator> getById(Integer id) {
        Optional<Administrador> administrador = administradorJpaRepository.findById(id);
        return administrador.map(administradorOptional -> mapper.toAdministrator(administradorOptional));
    }
}
