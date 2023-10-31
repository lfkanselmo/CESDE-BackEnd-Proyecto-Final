package co.com.inmobiliariaapp.entidades;

import jakarta.persistence.*;

@Entity
public class Inmueble {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private  Long idInmueble;
    private String direccionInmueble;
    private double precioInmueble;
    private boolean disponibildad;
    @ManyToOne
    private Ubicacion ubicacion;
    @ManyToOne
    private Propietario propietario;
    @ManyToOne
    private Oferta oferta;
    @ManyToOne
    private TipoInmueble tipoInmueble;
    @ManyToOne
    private Informacion informacion;

    public Long getIdInmueble() {
        return idInmueble;
    }

    public String getDireccionInmueble() {
        return direccionInmueble;
    }

    public void setDireccionInmueble(String direccionInmueble) {
        this.direccionInmueble = direccionInmueble;
    }

    public double getPrecioInmueble() {
        return precioInmueble;
    }

    public void setPrecioInmueble(double precioInmueble) {
        this.precioInmueble = precioInmueble;
    }

    public boolean isDisponibildad() {
        return disponibildad;
    }

    public void setDisponibildad(boolean disponibildad) {
        this.disponibildad = disponibildad;
    }

    public Ubicacion getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(Ubicacion ubicacion) {
        this.ubicacion = ubicacion;
    }

    public Propietario getPropietario() {
        return propietario;
    }

    public void setPropietario(Propietario propietario) {
        this.propietario = propietario;
    }

    public Oferta getOferta() {
        return oferta;
    }

    public void setOferta(Oferta oferta) {
        this.oferta = oferta;
    }

    public TipoInmueble getTipoInmueble() {
        return tipoInmueble;
    }

    public void setTipoInmueble(TipoInmueble tipoInmueble) {
        this.tipoInmueble = tipoInmueble;
    }

    public Informacion getInformacion() {
        return informacion;
    }

    public void setInformacion(Informacion informacion) {
        this.informacion = informacion;
    }
}
