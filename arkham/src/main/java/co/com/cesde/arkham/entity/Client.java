package co.com.cesde.arkham.entity;

import co.com.cesde.arkham.dto.client.ClientRegisterRecord;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "cliente")
public class Client {
    @Id
    @Column(name = "id_cliente")
    private Long clientId;
    @Column(name = "nombre")
    private String clientFirstName;
    @Column(name = "apellido")
    private String clientLastName;
    @Column(name = "telefono")
    private String clientPhone;
    @Column(name = "email")
    private String clientEmail;
    @Column(name = "activo")
    private Boolean active;

    @OneToMany(mappedBy = "client")
    private List<Appointment> appointments;

    public Client(ClientRegisterRecord clientRegisterRecord) {
        this.clientId = clientRegisterRecord.clientId();
        this.clientFirstName = clientRegisterRecord.firstName();
        this.clientLastName = clientRegisterRecord.lastName();
        this.clientPhone = clientRegisterRecord.phone();
        this.clientEmail = clientRegisterRecord.email();
        this.active = true;
    }
}
