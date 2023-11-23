package co.com.cesde.arkham.controller;

import co.com.cesde.arkham.dto.client.ClientListRecord;
import co.com.cesde.arkham.dto.client.ClientRegisterRecord;
import co.com.cesde.arkham.dto.client.ClientUpdateRecord;
import co.com.cesde.arkham.entity.Client;
import co.com.cesde.arkham.repository.ClientRepository;
import jakarta.validation.Valid;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.sql.SQLIntegrityConstraintViolationException;

@RestController
@RequestMapping("/client")
public class ClientController {

    @Autowired
    private ClientRepository clientRepository;

    @PostMapping("/save")
    private ResponseEntity<ClientListRecord> save(@RequestBody @Valid ClientRegisterRecord clientRegisterRecord,
                                                  UriComponentsBuilder uriComponentsBuilder) {
        if (clientRepository.existsById(clientRegisterRecord.clientId())) {
            throw new ValidationException("Client already exist");
        }
        Client saved = clientRepository.save(new Client(clientRegisterRecord));
        URI url = uriComponentsBuilder.path("/client/{id}").buildAndExpand(saved.getClientId()).toUri();
        return ResponseEntity.created(url).body(new ClientListRecord(saved));
    }


    @PutMapping("/update")
    public ResponseEntity<ClientListRecord> update(@RequestBody @Valid ClientUpdateRecord clientUpdateRecord) {
        Client client = clientRepository.getReferenceById(clientUpdateRecord.clientId());

        if (clientUpdateRecord.firstName() != null && !clientUpdateRecord.firstName().isBlank()) {
            client.setClientFirstName(clientUpdateRecord.firstName());
        }

        if (clientUpdateRecord.lastName() != null && !clientUpdateRecord.lastName().isBlank()) {
            client.setClientLastName(clientUpdateRecord.lastName());
        }

        if (clientUpdateRecord.phone() != null && !clientUpdateRecord.phone().isBlank()) {
            client.setClientPhone(clientUpdateRecord.phone());
        }

        if (clientUpdateRecord.email() != null && !clientUpdateRecord.email().isBlank()) {
            client.setClientEmail(clientUpdateRecord.email());
        }

        Client updated = clientRepository.save(client);
        return ResponseEntity.ok(new ClientListRecord(updated));

    }

    @GetMapping
    public ResponseEntity<Page<ClientListRecord>> getAll(@PageableDefault() Pageable pagination) {
        Page<Client> all = clientRepository.findAll(pagination);
        Page<ClientListRecord> allPage = all.map(ClientListRecord::new);
        return ResponseEntity.ok(allPage);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClientListRecord> getById(@PathVariable("id") Long userId) {
        return ResponseEntity.ok(new ClientListRecord(clientRepository.getReferenceById(userId)));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ClientListRecord> delete(@PathVariable("id") Long clientId) {
        if (clientRepository.existsById(clientId)) {
            clientRepository.deleteById(clientId);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
