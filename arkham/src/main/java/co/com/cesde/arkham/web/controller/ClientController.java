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

import java.util.Optional;

@RestController
@RequestMapping("/client")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @PostMapping("/save")
    private ResponseEntity save(@RequestBody @Valid ClientRegisterRecord clientRegisterRecord) {

        if (clientService.getById(clientRegisterRecord.clientId()).isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else{
            if(clientService.save(new Client(clientRegisterRecord)).isPresent()){
                return new ResponseEntity<>(HttpStatus.CREATED);
            }else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }


    }

    @PutMapping("/update")
    public ResponseEntity update(@RequestBody @Valid ClientUpdateRecord clientUpdateRecord){
        Optional<Client> clientOptional = clientService.getById(clientUpdateRecord.clientId());

        if(clientOptional.isPresent()){
           Client client = clientOptional.get();
           if(clientUpdateRecord.clientFirstName() != null){
                client.setClientFirstName(clientUpdateRecord.clientFirstName());
           }

            if(clientUpdateRecord.clientLastName() != null){
                client.setClientLastName(clientUpdateRecord.clientLastName());
            }

            if(clientUpdateRecord.clientPhone() != null){
                client.setClientPhone(clientUpdateRecord.clientPhone());
            }

            if(clientUpdateRecord.clientEmail() != null){
                client.setClientEmail(clientUpdateRecord.clientEmail());
            }

            if(clientService.update(client).isPresent()){
                return new ResponseEntity(HttpStatus.OK);
            }else{
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

        }else{
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

    }

    @GetMapping
    public ResponseEntity<Page<Client>> getAll(@PageableDefault(size = 10) Pageable pagination){
        return clientService.getAll(pagination)
                .map(clients -> new ResponseEntity<>(clients, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClientListRecord> getById(@PathVariable("id") Integer id){
        return clientService.getById(id)
                .map(client -> new ResponseEntity<>(new ClientListRecord(client),HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable("id") Integer id){
        if(clientService.delete(id)){
            return new ResponseEntity(HttpStatus.OK);
        }else{
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

}
