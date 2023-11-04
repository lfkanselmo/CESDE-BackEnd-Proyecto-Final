package co.com.cesde.arkham.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Ubicacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_ubicacion")
    private Long idUbicacion;
    @OneToOne
    @JoinColumn(name = "id_inmueble",insertable = false, updatable = false)
    private Inmueble inmueble;
    @Column(name = "tipo_via")
    private String tipoVia;
    @Column(name = "numero_via")
    private String numeroVia;
    private String numero;
    private String complemento;
    @ManyToOne
    @JoinColumn(name = "id_barrio",insertable = false,updatable = false)
    private Barrio barrio;
}
