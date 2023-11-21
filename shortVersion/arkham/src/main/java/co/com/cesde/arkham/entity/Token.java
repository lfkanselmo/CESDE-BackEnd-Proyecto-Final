package co.com.cesde.arkham.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Table(name = "token")
public class Token {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_token")
    private Long tokenId;
    private String token;
    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_token")
    private TokenType tokenType;
    @Column(name = "expirado")
    private boolean expired;
    @Column(name = "revocado")
    private boolean revoked;
    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private User user;
}
