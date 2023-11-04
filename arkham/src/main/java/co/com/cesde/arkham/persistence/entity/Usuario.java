package co.com.cesde.arkham.persistence.entity;

import jakarta.persistence.*;

@Entity
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private Long idUsuario;
    private String usuario;
    private String contrasenha;
    @Enumerated(value = EnumType.STRING)
    private Rol rol;

    private Boolean activo;
}
