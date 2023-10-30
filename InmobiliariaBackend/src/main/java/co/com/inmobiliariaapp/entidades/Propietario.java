package co.com.inmobiliariaapp.entidades;

public class Propietario extends Actor{

    // Constructores

    public Propietario(Long id, String nombre, String apellido, String telefono, String email) {
        super(id, nombre, apellido, telefono, email);
    }

    public Propietario(String nombre, String apellido, String telefono, String email) {
        super(nombre, apellido, telefono, email);
    }
}
