package co.com.inmobiliariaapp.entidades;

public class Oferta {
    private Long id;
    private String nombre;

    public Oferta(String nombre) {
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

