package co.com.inmobiliariaapp.entidades;

public abstract class Actor {

    protected Long id;
    protected String nombre;
    protected String apellido;
    protected String telefono;
    protected String email;

    // Constructores

    // para la persistencia
    public Actor(Long id, String nombre, String apellido, String telefono, String email) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
        this.email = email;
    }

    // para los services
    public Actor(String nombre, String apellido, String telefono, String email) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
        this.email = email;
    }

    // Getters & Setters

    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    @Override
    public String toString() {
        return " {" +
                "ID = " + id + "\n" +
                ", Nombre = '" + nombre + "\n" +
                ", Apellido = '" + apellido + "\n" +
                ", Telefono = '" + telefono + "\n" +
                ", Email = '" + email + "\n" +
                '}';
    }
}
