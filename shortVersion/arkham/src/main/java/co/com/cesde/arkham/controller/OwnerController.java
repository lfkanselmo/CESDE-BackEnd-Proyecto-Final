package co.com.cesde.arkham.controller;

import co.com.cesde.arkham.dto.owner.OwnerListRecord;
import co.com.cesde.arkham.dto.owner.OwnerRegisterRecord;
import co.com.cesde.arkham.dto.owner.OwnerUpdateRecord;
import co.com.cesde.arkham.entity.Owner;
import co.com.cesde.arkham.repository.OwnerRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/owner")
public class OwnerController {
    @Autowired
    private OwnerRepository ownerRepository;

    @PostMapping("/save")
    public ResponseEntity<OwnerListRecord> save(@RequestBody @Valid OwnerRegisterRecord ownerRegisterRecord,
                                                UriComponentsBuilder uriComponentsBuilder) {
        Owner saved = ownerRepository.save(new Owner(ownerRegisterRecord));
        URI url = uriComponentsBuilder.path("/owner/{id}").buildAndExpand(saved.getOwnerId()).toUri();
        return ResponseEntity.created(url).body(new OwnerListRecord(saved));
    }


    @PutMapping
    public ResponseEntity<OwnerListRecord> update(@RequestBody @Valid OwnerUpdateRecord ownerUpdateRecord) {

        if (ownerRepository.existsById(ownerUpdateRecord.ownerId())) {
            Owner owner = ownerRepository.getReferenceById(ownerUpdateRecord.ownerId());
            if (ownerUpdateRecord.firstName() != null && !ownerUpdateRecord.firstName().isBlank()) {
                owner.setOwnerFirstName(ownerUpdateRecord.firstName());
            }

            if (ownerUpdateRecord.lastName() != null && !ownerUpdateRecord.lastName().isBlank()) {
                owner.setOwnerLastName(ownerUpdateRecord.lastName());
            }

            if (ownerUpdateRecord.phone() != null && !ownerUpdateRecord.phone().isBlank()) {
                owner.setOwnerPhone(ownerUpdateRecord.phone());
            }

            if (ownerUpdateRecord.email() != null && !ownerUpdateRecord.email().isBlank()) {
                owner.setOwnerEmail(ownerUpdateRecord.email());
            }

            Owner updated = ownerRepository.save(owner);

            return ResponseEntity.ok(new OwnerListRecord(updated));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<Page<OwnerListRecord>> getAll(@PageableDefault() Pageable pagination) {
        Page<Owner> all = ownerRepository.findAll(pagination);
        Page<OwnerListRecord> allPage = all.map(OwnerListRecord::new);
        return ResponseEntity.ok(allPage);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OwnerListRecord> getById(@PathVariable("id") Long ownerId) {
        return ResponseEntity.ok(new OwnerListRecord(ownerRepository.getReferenceById(ownerId)));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<OwnerListRecord> delete(@PathVariable("id") Long ownerId) {
        if (ownerRepository.existsById(ownerId)) {
            ownerRepository.deleteById(ownerId);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/clientFirstName/{name}")
    public ResponseEntity<List<OwnerListRecord>> getByFirstName(@PathVariable("name") String ownerName) {
        List<Owner> owners = ownerRepository.getByOwnerFirstName(ownerName);
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
