package co.com.cesde.arkham.domain.dto.client;

import co.com.cesde.arkham.domain.Client;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record ClientUpdateRecord(
        @NotNull
        Long clientId,
        String clientFirstName,
        String clientLastName,
        @Pattern(regexp = "\\d{7,11}")
        String clientPhone,
        @Email
        String clientEmail
) {

    public ClientUpdateRecord(Client client) {
        this(client.getClientId(), client.getClientFirstName(), client.getClientLastName(), client.getClientPhone(), client.getClientEmail());
    }
}
