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
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cliente")
    private Long idCliente;
    @Column(name = "nombre")
    private String nombreCliente;
    @Column(name = "apellido")
    private String apellidoCliente;
    @Column(name = "telefono")
    private String telefonoCliente;
    @Column(name = "email")
    private String emailCliente;
    private Boolean activo;
}
