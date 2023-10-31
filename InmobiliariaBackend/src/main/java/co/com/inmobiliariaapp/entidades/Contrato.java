package co.com.inmobiliariaapp.entidades;

import jakarta.persistence.*;

@Entity
public class Contrato {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idContrato;
    @ManyToOne
    private Cliente cliente;
    @ManyToOne
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
