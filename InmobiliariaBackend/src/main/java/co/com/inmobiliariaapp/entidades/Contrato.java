package co.com.inmobiliariaapp.entidades;

public class Contrato {
    private Long idContrato;
    private Cliente cliente;
    private Inmueble inmueble;

    public Long getIdContrato() {
        return idContrato;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Inmueble getInmueble() {
        return inmueble;
    }

    public void setInmueble(Inmueble inmueble) {
        this.inmueble = inmueble;
    }
}
