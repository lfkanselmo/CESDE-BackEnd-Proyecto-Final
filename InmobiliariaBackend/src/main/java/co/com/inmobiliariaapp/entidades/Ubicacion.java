package co.com.inmobiliariaapp.entidades;


public class Ubicacion {
    private Long id;
    private String nombre;

    public Ubicacion(String nombre) {
        this.nombre = nombre;
    }

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
