package co.com.inmobiliariaapp.dao;

import co.com.inmobiliariaapp.entidades.Administrador;
import jakarta.persistence.EntityManager;

import java.util.List;

public class AdministradorDao extends DaoAbstract<Administrador>{

    public AdministradorDao() {

    }

    @Override
    public List<Administrador> listarTodos() {
        jpql = "SELECT A FROM Administrador AS A";
        abrir();
        List<Administrador> administradores = this.em.createQuery(jpql).getResultList();
        cerrar();
        return administradores;
    }

    @Override
    public Administrador listarPorId(Long id) {
        abrir();
        Administrador administrador = this.em.find(Administrador.class, id);
        cerrar();
        return administrador;
    }
}
