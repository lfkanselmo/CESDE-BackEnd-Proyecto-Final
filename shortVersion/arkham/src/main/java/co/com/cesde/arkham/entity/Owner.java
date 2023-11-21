package co.com.cesde.arkham.entity;

import co.com.cesde.arkham.dto.owner.OwnerRegisterRecord;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "propietario")
public class Owner {
    @Id
    @Column(name = "id_propietario")
    private Long ownerId;
    @Column(name = "nombre")
    private String ownerFirstName;
    @Column(name = "apellido")
    private String ownerLastName;
    @Column(name = "telefono")
    private String ownerPhone;
    @Column(name = "email")
    private String ownerEmail;
    @Column(name = "activo")
    private Boolean active;
    @OneToMany(mappedBy = "owner")
    private List<Property> properties;

    public Owner(OwnerRegisterRecord ownerRegisterRecord) {
        this.ownerId = ownerRegisterRecord.ownerId();
        this.ownerFirstName = ownerRegisterRecord.firstName();
        this.ownerLastName = ownerRegisterRecord.lastName();
        this.ownerPhone = ownerRegisterRecord.phone();
        this.ownerEmail = ownerRegisterRecord.email();
        this.active = true;
    }
}
