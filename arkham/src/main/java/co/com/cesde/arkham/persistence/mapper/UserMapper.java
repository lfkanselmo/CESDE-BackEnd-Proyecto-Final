package co.com.cesde.arkham.persistence.mapper;

import co.com.cesde.arkham.domain.User;
import co.com.cesde.arkham.persistence.entity.Usuario;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "Spring")
public interface UserMapper {
    @Mappings({
            @Mapping(source = "idUsuario", target = "userId"),
            @Mapping(source = "usuario", target = "user"),
            @Mapping(source = "contrasenha", target = "password"),
            @Mapping(source = "rol", target = "rol"),
            @Mapping(source = "activo", target = "active")
    })
    User toUser(Usuario usuario);

    @InheritInverseConfiguration
    Usuario toUsuario(User user);
}
