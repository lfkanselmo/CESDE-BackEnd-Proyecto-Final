package co.com.cesde.arkham.web.controller;

import co.com.cesde.arkham.domain.Owner;
import co.com.cesde.arkham.domain.dto.client.ClientUpdateRecord;
import co.com.cesde.arkham.domain.dto.owner.OwnerListRecord;
import co.com.cesde.arkham.domain.dto.owner.OwnerRegisterRecord;
import co.com.cesde.arkham.domain.dto.owner.OwnerUpdateRecord;
import co.com.cesde.arkham.domain.service.OwnerService;
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
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/owner")
public class OwnerController {
    @Autowired
    private OwnerService ownerService;

    @PostMapping("/save")
    public ResponseEntity<OwnerListRecord> save(@RequestBody @Valid OwnerRegisterRecord ownerRegisterRecord,
                                                UriComponentsBuilder uriComponentsBuilder) {
        Owner saved = ownerService.save(new Owner(ownerRegisterRecord));
        URI url = uriComponentsBuilder.path("/owner/{id}").buildAndExpand(saved.getOwnerId()).toUri();
        return ResponseEntity.created(url).body(new OwnerListRecord(saved));
    }


    @PutMapping
    public ResponseEntity<OwnerListRecord> update(@RequestBody @Valid OwnerUpdateRecord ownerUpdateRecord) {
        Optional<Owner> ownerOptional = ownerService.getById(ownerUpdateRecord.ownerId());
        if (ownerOptional.isPresent()) {
            Owner owner = ownerOptional.get();
            if (ownerUpdateRecord.ownerFirstName() != null && !ownerUpdateRecord.ownerFirstName().isBlank()) {
                owner.setOwnerFirstName(ownerUpdateRecord.ownerFirstName());
            }

            if (ownerUpdateRecord.ownerLastName() != null && !ownerUpdateRecord.ownerLastName().isBlank()) {
                owner.setOwnerLastName(ownerUpdateRecord.ownerLastName());
            }

            if (ownerUpdateRecord.ownerPhone() != null && !ownerUpdateRecord.ownerPhone().isBlank()) {
                owner.setOwnerPhone(ownerUpdateRecord.ownerPhone());
            }

            if (ownerUpdateRecord.ownerEmail() != null && !ownerUpdateRecord.ownerEmail().isBlank()) {
                owner.setOwnerEmail(ownerUpdateRecord.ownerEmail());
            }

            Owner updated = ownerService.update(owner);

            return ResponseEntity.ok(new OwnerListRecord(updated));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<Page<OwnerListRecord>> getAll(@PageableDefault() Pageable pagination) {
        Page<Owner> all = ownerService.getAll(pagination);
        Page<OwnerListRecord> allPage = all.map(OwnerListRecord::new);
        return ResponseEntity.ok(allPage);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OwnerListRecord> getById(@PathVariable("id") Long ownerId) {
        return ownerService.getById(ownerId)
                .map(owner -> ResponseEntity.ok(new OwnerListRecord(owner)))
                .orElse(ResponseEntity.noContent().build());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<OwnerListRecord> delete(@PathVariable("id") Long ownerId) {
        if (ownerService.existsById(ownerId)) {
            ownerService.delete(ownerId);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/clientFirstName/{name}")
    public ResponseEntity<List<OwnerListRecord>> getByFirstName(@PathVariable("name") String ownerName) {
        List<Owner> owners = ownerService.getByFirstName(ownerName);
        if (!owners.isEmpty()) {
            List<OwnerListRecord> ownerRecordList = owners
                    .stream()
                    .map(OwnerListRecord::new)
                    .toList();
            return ResponseEntity.ok(ownerRecordList);
        } else {
            return ResponseEntity.noContent().build();
        }
    }
}
