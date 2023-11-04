package co.com.cesde.arkham.persistence;

import co.com.cesde.arkham.persistence.crud.AdministradorJpaRepository;
import co.com.cesde.arkham.persistence.entity.Administrador;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AdministradorRepository {
    private AdministradorJpaRepository administradorJpaRepository;

    void create(Administrador administrador){
        administradorJpaRepository.save(administrador);
    }

    void update(Administrador administrador){
        administradorJpaRepository.save(administrador);
    }

    void delete(Long id){
        administradorJpaRepository.deleteById(id);
    }

    List<Administrador> getByNombre(String nombre){
        return administradorJpaRepository.findByNombreAdministrador(nombre);
    }
}
