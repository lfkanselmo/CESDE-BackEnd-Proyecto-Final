package co.com.cesde.arkham.domain.dto.client;

import co.com.cesde.arkham.domain.Client;
import jakarta.validation.constraints.NotNull;

public record ClientUpdateRecord(
        @NotNull
        Integer clientId,
        String clientFirstName,
        String clientLastName,
        String clientPhone,
        String clientEmail
) {

    public ClientUpdateRecord(Client client) {
        this(client.getClientId(), client.getClientFirstName(), client.getClientLastName(), client.getClientPhone(), client.getClientEmail());
    }
}
