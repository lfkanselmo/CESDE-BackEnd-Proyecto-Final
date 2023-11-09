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
    private Integer idUbicacion;
    @OneToOne
    @JoinColumn(name = "id_inmueble",insertable = false, updatable = false)
    private Inmueble inmueble;

    private String direccion;
    private String barrio;
    private String ciudad;
}
