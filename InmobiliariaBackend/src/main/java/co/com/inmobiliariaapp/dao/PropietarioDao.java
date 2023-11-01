package co.com.inmobiliariaapp.dao;

import co.com.inmobiliariaapp.entidades.Propietario;

import java.util.List;

public class PropietarioDao extends DaoAbstract<Propietario>{
    @Override
    public List<Propietario> listarTodos() {
        jpql = "SELECT P FROM Propietario AS P";
        abrir();
        List<Propietario> propietarios = this.em.createQuery(jpql).getResultList();
        cerrar();
        return propietarios;
    }

    @Override
    public Propietario listarPorId(Long id) {
        abrir();
        Propietario propietario = this.em.find(Propietario.class, id);
        cerrar();
        return propietario;
    }
}
