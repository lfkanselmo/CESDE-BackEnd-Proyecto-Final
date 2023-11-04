package co.com.cesde.arkham.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Contrato {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_contrato")
    private Long idContrato;
    @ManyToOne
    @JoinColumn(name = "id_cliente",insertable = false,updatable = false)
    private Cliente cliente;
    @ManyToOne
    @JoinColumn(name = "id_inmueble", insertable = false, updatable = false)
    private Inmueble inmueble;

    private Boolean activo;
}
