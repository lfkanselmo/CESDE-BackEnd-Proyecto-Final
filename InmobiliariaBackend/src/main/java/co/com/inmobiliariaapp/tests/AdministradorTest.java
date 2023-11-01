package co.com.inmobiliariaapp.tests;

import co.com.inmobiliariaapp.dao.AdministradorDao;
import co.com.inmobiliariaapp.dao.DaoInterface;
import co.com.inmobiliariaapp.entidades.Administrador;

public class AdministradorTest {
    public static void main(String[] args) {
        Administrador admi = new Administrador("Alberto", "Castaño", "3456789032","alber@mail.com");
        DaoInterface dao = new AdministradorDao();
        dao.crear(admi);
        dao.listarPorId(1L);
    }
}
