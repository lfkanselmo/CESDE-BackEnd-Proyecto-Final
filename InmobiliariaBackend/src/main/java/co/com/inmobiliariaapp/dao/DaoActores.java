package co.com.inmobiliariaapp.dao;

import jakarta.persistence.EntityManager;

import java.util.List;

public abstract class DaoActores<T> implements DaoInterface<T>{
    protected EntityManager em;
    protected String jpql;

    public DaoActores(EntityManager em) {
        this.em = em;
    }

    @Override
    public void crear(T t) {
        this.em.getTransaction().begin();
        em.persist(t);
        this.em.getTransaction().commit();
    }

    @Override
    public void modificar(T t) {
        this.em.getTransaction().begin();
        em.merge(t);
        this.em.getTransaction().commit();
    }

    @Override
    public void eliminar(T t) {
        this.em.getTransaction().begin();
        em.merge(t);
        this.em.getTransaction().commit();
    }
}
