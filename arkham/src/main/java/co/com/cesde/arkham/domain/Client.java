package co.com.cesde.arkham.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Client {
    private Integer clientId;
    private String clientFirstName;
    private String clientLastName;
    private String clientPhone;
    private String clientEmail;
    private Boolean active;
}
