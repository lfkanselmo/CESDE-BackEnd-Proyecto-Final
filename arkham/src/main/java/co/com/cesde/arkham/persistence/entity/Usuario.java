package co.com.cesde.arkham.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private Integer idUsuario;
    private String usuario;
    private String contrasenha;
    @Enumerated(value = EnumType.STRING)
    private Rol rol;

    private Boolean activo;
}
