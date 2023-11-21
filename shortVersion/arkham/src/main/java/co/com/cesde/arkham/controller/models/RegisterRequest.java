package co.com.cesde.arkham.controller.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class RegisterRequest {
    private String email;
    private String password;
    private String firstName;
    private String LastName;
    private String phone;
    private String role;
}
