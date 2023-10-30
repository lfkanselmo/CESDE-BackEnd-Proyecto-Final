package co.com.inmobiliariaapp.entidades;

public class TipoInmueble extends Tipo{
    public TipoInmueble(Long id, String nombre) {
        super(id, nombre);
    }

    public TipoInmueble(String nombre) {
        super(nombre);
    }
}
