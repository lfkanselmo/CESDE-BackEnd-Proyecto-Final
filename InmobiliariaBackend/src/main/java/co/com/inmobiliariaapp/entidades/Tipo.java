package co.com.inmobiliariaapp.entidades;

public abstract class Tipo {

    protected Long id;
    protected String nombre;

    // Constructores

    public Tipo(Long id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public Tipo(String nombre) {
        this.nombre = nombre;
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

}
