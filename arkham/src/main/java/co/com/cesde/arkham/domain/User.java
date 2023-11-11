package co.com.cesde.arkham.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class User {
    private Integer userId;
    private String user;
    private String password;
    private String rol;
    private String FirstName;
    private String LastName;
    private String Phone;
    private Boolean active;
}
