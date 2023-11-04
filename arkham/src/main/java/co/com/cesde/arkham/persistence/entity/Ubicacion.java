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
    private String calle;
    private String numero;
    private String complemento;
    @ManyToOne
    @JoinColumn(name = "id_barrio",insertable = false,updatable = false)
    private Barrio barrio;
}
