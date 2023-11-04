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
public class Administrador {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_administrador")
    private Long idAdministrador;
    @Column(name = "nombre")
    private String nombreAdministrador;
    @Column(name = "apellido")
    private String apellidoAdministrador;
    @Column(name = "telefono")
    private String telefonoAdministrador;
    @Column(name = "email")
    private String emailAdministrador;
    private Boolean activo;
    @OneToMany(mappedBy = "administrador")
    private List<Cita> citas;

}
