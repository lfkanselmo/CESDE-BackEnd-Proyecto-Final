package co.com.inmobiliariaapp.dao;

import co.com.inmobiliariaapp.entidades.Administrador;
import jakarta.persistence.EntityManager;

import java.util.List;

public class AdministradorDao extends DaoActores<Administrador>{

    public AdministradorDao(EntityManager em) {
        super(em);
    }

    @Override
    public List<Administrador> listarTodos() {
        return null;  // TO-DO
    }

    @Override
    public Administrador listarPorId(Long id) {
        return this.em.find(Administrador.class, id);
    }
}
