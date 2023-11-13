package co.com.cesde.arkham.domain;

import co.com.cesde.arkham.domain.dto.user.UserRegisterRecord;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class User {
    private Integer userId;
    private String userEmail;
    private String password;
    private String rol;
    private String firstName;
    private String lastName;
    private String phone;
    private Boolean active;

    public User(UserRegisterRecord userRegisterRecord) {
        this.userEmail = userRegisterRecord.user();
        this.password = userRegisterRecord.password();
        this.rol = userRegisterRecord.rol();
        this.firstName = userRegisterRecord.firstName();
        this.lastName = userRegisterRecord.lastName();
        this.phone = userRegisterRecord.phone();
        this.active = true;

    }
}
