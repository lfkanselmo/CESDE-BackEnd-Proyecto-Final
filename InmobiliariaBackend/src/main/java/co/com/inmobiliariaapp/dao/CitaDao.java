package co.com.inmobiliariaapp.dao;

import co.com.inmobiliariaapp.entidades.Cita;

import java.util.List;

public class CitaDao extends DaoAbstract<Cita>{
    @Override
    public List<Cita> listarTodos() {
        jpql = "SELECT C FROM Cita AS C";
        abrir();
        List<Cita> citas = this.em.createQuery(jpql).getResultList();
        cerrar();
        return citas;
    }

    @Override
    public Cita listarPorId(Long id) {
        return null;
    }
}
