package co.com.inmobiliariaapp.dao;

import java.util.List;

public interface DaoInterface<T> {

    void crear(T t);
    void modificar(T t);
    void eliminar(T t);
    List<T> listarTodos();
    T listarPorId(Long id);
}
