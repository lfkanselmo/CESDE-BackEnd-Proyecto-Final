package co.com.cesde.arkham.domain;

import co.com.cesde.arkham.domain.dto.client.ClientRegisterRecord;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Client {
    private Long clientId;
    private String clientFirstName;
    private String clientLastName;
    private String clientPhone;
    private String clientEmail;
    private Boolean active;

    public Client(ClientRegisterRecord clientRegisterRecord) {
        this.clientId = clientRegisterRecord.clientId();
        this.clientFirstName = clientRegisterRecord.clientFirstName();
        this.clientLastName = clientRegisterRecord.clientLastName();
        this.clientPhone = clientRegisterRecord.clientPhone();
        this.clientEmail = clientRegisterRecord.clientEmail();
        this.active = true;
    }
}
