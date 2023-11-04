package co.com.cesde.arkham.persistence.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Barrio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_barrio")
    private Long idBarrio;
    @Column(name = "nombre")
    private String nombreBarrio;
    private Boolean activo;
    @OneToMany(mappedBy = "barrio")
    private List<Ubicacion> ubicaciones;
}
