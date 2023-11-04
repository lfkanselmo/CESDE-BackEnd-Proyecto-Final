package co.com.cesde.arkham.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Inmueble {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_inmueble")
    private Long idInmueble;
    @OneToOne
    @JoinColumn(name = "id_ubicacion",insertable = false, updatable = false)
    private Ubicacion ubicacion;
    private Double precio;
    private Boolean disponibilidad;
    @ManyToOne
    @JoinColumn(name = "id_propietario",insertable = false, updatable = false)
    private Propietario propietario;
    @Enumerated(EnumType.STRING)
    private Oferta oferta;
    @Enumerated(value = EnumType.STRING)
    @Column(name = "tipo_inmueble")
    private TipoInmueble tipoInmueble;
    @Column(name = "numero_habitaciones")
    private Integer habitaciones;
    @Column(name = "numero_banhos")
    private Integer banhos;
    private Boolean patio;
    private Integer nivel;
    private Double area;
    @Column(name = "gas_natural")
    private Boolean gasNatural;
    @Column(name = "zona_de_ropas")
    private Boolean zonaRopa;
    private Boolean activo;
    @OneToMany(mappedBy = "inmueble")
    private List<Contrato> contratos;

}

