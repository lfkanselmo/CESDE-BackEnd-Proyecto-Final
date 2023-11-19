package co.com.cesde.arkham.web.controller;

import co.com.cesde.arkham.domain.Client;
import co.com.cesde.arkham.domain.dto.client.ClientListRecord;
import co.com.cesde.arkham.domain.dto.client.ClientRegisterRecord;
import co.com.cesde.arkham.domain.dto.client.ClientUpdateRecord;
import co.com.cesde.arkham.domain.service.ClientService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/client")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @PostMapping("/save")
    private ResponseEntity<ClientListRecord> save(@RequestBody @Valid ClientRegisterRecord clientRegisterRecord,
                                                  UriComponentsBuilder uriComponentsBuilder) {

        Client saved = clientService.save(new Client(clientRegisterRecord));
        URI url = uriComponentsBuilder.path("/client/{id}").buildAndExpand(saved.getClientId()).toUri();
        return ResponseEntity.created(url).body(new ClientListRecord(saved));
    }


    @PutMapping("/update")
    public ResponseEntity<ClientListRecord> update(@RequestBody @Valid ClientUpdateRecord clientUpdateRecord) {
        Optional<Client> clientOptional = clientService.getById(clientUpdateRecord.clientId());

        if (clientOptional.isPresent()) {
            Client client = clientOptional.get();
            if (clientUpdateRecord.clientFirstName() != null && !clientUpdateRecord.clientFirstName().isBlank()) {
                client.setClientFirstName(clientUpdateRecord.clientFirstName());
            }

            if (clientUpdateRecord.clientLastName() != null && !clientUpdateRecord.clientLastName().isBlank()) {
                client.setClientLastName(clientUpdateRecord.clientLastName());
            }

            if (clientUpdateRecord.clientPhone() != null && !clientUpdateRecord.clientPhone().isBlank()) {
                client.setClientPhone(clientUpdateRecord.clientPhone());
            }

            if (clientUpdateRecord.clientEmail() != null && !clientUpdateRecord.clientEmail().isBlank()) {
                client.setClientEmail(clientUpdateRecord.clientEmail());
            }

            Client updated = clientService.update(client);
            return ResponseEntity.ok(new ClientListRecord(updated));

        } else {
            return ResponseEntity.notFound().build();
        }

    }

    @GetMapping
    public ResponseEntity<Page<ClientListRecord>> getAll(@PageableDefault() Pageable pagination) {
        Page<Client> all = clientService.getAll(pagination);
        Page<ClientListRecord> allPage = all.map(ClientListRecord::new);
        return ResponseEntity.ok(allPage);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClientListRecord> getById(@PathVariable("id") Long userId) {
        return clientService.getById(userId)
                .map(client -> new ResponseEntity<>(new ClientListRecord(client), HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NO_CONTENT));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ClientListRecord> delete(@PathVariable("id") Long clientId) {
        if (clientService.existsById(clientId)) {
            clientService.delete(clientId);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
