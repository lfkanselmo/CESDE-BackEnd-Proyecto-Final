package co.com.inmobiliariaapp.tests;

import co.com.inmobiliariaapp.dao.AdministradorDao;
import co.com.inmobiliariaapp.dao.DaoInterface;
import co.com.inmobiliariaapp.entidades.Administrador;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class AdministradorTest {
    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("libreria");
        EntityManager em = factory.createEntityManager();
        Administrador admi = new Administrador("Alberto", "Castaño", "3456789032","alber@mail.com");
        DaoInterface dao = new AdministradorDao(em);
        dao.crear(admi);
        dao.listarPorId(1L);
        em.close();
        factory.close();
    }
}
