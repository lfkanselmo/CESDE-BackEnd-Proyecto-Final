package co.com.inmobiliariaapp.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public abstract class DaoAbstract<T> implements DaoInterface<T>{
    protected EntityManager em;
    protected EntityManagerFactory factory;
    protected String jpql;

    public DaoAbstract() {
    }

    protected void abrir(){
        this.factory = Persistence.createEntityManagerFactory("arkham");
        this.em = this.factory.createEntityManager();
    }

    protected void cerrar(){
        this.em.close();
        this.factory.close();
    }

    @Override
    public void crear(T t) {
        abrir();
        this.em.getTransaction().begin();
        em.persist(t);
        this.em.getTransaction().commit();
        cerrar();
    }

    @Override
    public void modificar(T t) {
        abrir();
        this.em.getTransaction().begin();
        em.merge(t);
        this.em.getTransaction().commit();
        cerrar();
    }

    @Override
    public void eliminar(T t) {
        abrir();
        this.em.getTransaction().begin();
        em.merge(t);
        this.em.getTransaction().commit();
        cerrar();
    }
}
