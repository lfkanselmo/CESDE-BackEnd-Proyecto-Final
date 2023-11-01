package co.com.inmobiliariaapp.dao;

import co.com.inmobiliariaapp.entidades.Cliente;

import java.util.List;

public class ClienteDao extends DaoAbstract<Cliente>{
    @Override
    public List<Cliente> listarTodos() {
        jpql = "SELECT C FROM Cliente AS C";
        abrir();
        List<Cliente> clientes = this.em.createQuery(jpql).getResultList();
        cerrar();
        return clientes;
    }

    @Override
    public Cliente listarPorId(Long id) {
        abrir();
        Cliente cliente = this.em.find(Cliente.class, id);
        cerrar();
        return cliente;
    }
}
