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
public class Propietario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_propietario")
    private Integer idPropietario;
    @Column(name = "nombre")
    private String nombrePropietario;
    @Column(name = "apellido")
    private String apellidoPropietario;
    @Column(name = "telefono")
    private String telefonoPropietario;
    @Column(name = "email")
    private String emailPropietario;
    private Boolean activo;
    @OneToMany(mappedBy = "propietario")
    private List<Inmueble> inmuebles;
}
