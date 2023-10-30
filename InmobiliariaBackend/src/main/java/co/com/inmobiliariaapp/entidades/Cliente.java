package co.com.inmobiliariaapp.entidades;

public class Cliente extends Actor{

    // Constructores

    public Cliente(Long id, String nombre, String apellido, String telefono, String email) {
        super(id, nombre, apellido, telefono, email);
    }

    public Cliente(String nombre, String apellido, String telefono, String email) {
        super(nombre, apellido, telefono, email);
    }
}
