package co.com.cesde.arkham.entity;

import co.com.cesde.arkham.dto.property.PropertyRegisterRecord;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "inmueble")
public class Property {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_inmueble")
    private Long propertyId;
    @Column(name = "precio")
    private Double price;
    @Column(name = "disponibilidad")
    private Boolean availability;
    @Column(name = "id_propietario")
    private Long ownerId;
    @Enumerated(EnumType.STRING)
    @Column(name = "oferta")
    private Offer offer;
    @Enumerated(value = EnumType.STRING)
    @Column(name = "tipo_inmueble")
    private PropertyType propertyType;
    @Column(name = "numero_habitaciones")
    private Integer rooms;
    @Column(name = "numero_banhos")
    private Integer bathrooms;
    @Column(name = "patio")
    private Boolean courtyard;
    @Column(name = "nivel")
    private Integer level;
    private Double area;
    @Column(name = "gas_natural")
    private Boolean naturalGas;
    @Column(name = "zona_de_ropas")
    private Boolean laundryArea;
    @Column(name = "direccion")
    private String address;
    @Column(name = "barrio")
    private String district;
    @Column(name = "ciudad")
    private String city;
    @Column(name = "activo")
    private Boolean active;

    @ManyToOne
    @JoinColumn(name = "id_propietario",insertable = false, updatable = false)
    private Owner owner;

    @OneToMany(mappedBy = "property")
    private List<Appointment> appointments;

    public Property(PropertyRegisterRecord propertyRegisterRecord) {
        this.price = propertyRegisterRecord.price();
        this.availability = propertyRegisterRecord.availability();
        this.ownerId = propertyRegisterRecord.ownerId();
        this.offer = Offer.valueOf(propertyRegisterRecord.offer());
        this.propertyType = PropertyType.valueOf(propertyRegisterRecord.propertyType());
        this.rooms = propertyRegisterRecord.rooms();
        this.bathrooms = propertyRegisterRecord.bathrooms();
        this.courtyard = propertyRegisterRecord.courtyard();
        this.level = propertyRegisterRecord.level();
        this.area = propertyRegisterRecord.area();
        this.naturalGas = propertyRegisterRecord.naturalGas();
        this.laundryArea = propertyRegisterRecord.laundryArea();
        this.address = propertyRegisterRecord.address();
        this.district = propertyRegisterRecord.district();
        this.city = propertyRegisterRecord.city();
        this.active = true;
    }
}

