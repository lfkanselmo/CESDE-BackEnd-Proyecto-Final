package co.com.cesde.arkham.domain.repository;

import co.com.cesde.arkham.persistence.crud.AdministradorJpaRepository;
import co.com.cesde.arkham.persistence.entity.Administrador;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AdministradorRepository {
    private AdministradorJpaRepository administradorJpaRepository;

    public void create(Administrador administrador){
        administradorJpaRepository.save(administrador);
    }

    public void update(Administrador administrador){
        administradorJpaRepository.save(administrador);
    }

    public void delete(Long id){
        administradorJpaRepository.deleteById(id);
    }

    public List<Administrador> getByNombreAdm(String nombre){
        return administradorJpaRepository.findByNombreAdministrador(nombre);
    }
}
