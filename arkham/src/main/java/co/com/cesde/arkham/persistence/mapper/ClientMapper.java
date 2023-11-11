package co.com.cesde.arkham.persistence.mapper;

import co.com.cesde.arkham.domain.Client;
import co.com.cesde.arkham.persistence.entity.Cliente;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ClientMapper {
    @Mappings({
            @Mapping(source = "idCliente", target = "clientId"),
            @Mapping(source = "nombreCliente", target = "clientFirstName"),
            @Mapping(source = "apellidoCliente", target = "clientLastName"),
            @Mapping(source = "telefonoCliente", target = "clientPhone"),
            @Mapping(source = "emailCliente", target = "clientEmail"),
            @Mapping(source = "activo", target = "active")
    })
    Client toclient(Cliente cliente);
    List<Client> toclients(List<Cliente> clientes);

    @InheritInverseConfiguration
    Cliente toCliente(Client client);

}
