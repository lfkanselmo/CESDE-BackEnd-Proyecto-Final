package co.com.inmobiliariaapp.entidades;

public class Administrador extends Actor{

    // Constructores

    public Administrador(Long id, String nombre, String apellido, String telefono, String email) {
        super(id, nombre, apellido, telefono, email);
    }

    public Administrador(String nombre, String apellido, String telefono, String email) {
        super(nombre, apellido, telefono, email);
    }
}
