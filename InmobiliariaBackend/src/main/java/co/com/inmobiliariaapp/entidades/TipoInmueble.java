package co.com.inmobiliariaapp.entidades;

public class TipoInmueble{
    private Long id;
    private String nombre;

    public TipoInmueble(String nombre) {
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
