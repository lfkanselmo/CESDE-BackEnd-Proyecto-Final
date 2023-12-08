package co.com.cesde.arkham.controller;

import co.com.cesde.arkham.dto.owner.OwnerListRecord;
import co.com.cesde.arkham.dto.owner.OwnerRegisterRecord;
import co.com.cesde.arkham.dto.owner.OwnerUpdateRecord;
import co.com.cesde.arkham.entity.Owner;
import co.com.cesde.arkham.repository.OwnerRepository;
import jakarta.transaction.Transactional;
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
import java.util.List;

@RestController
@RequestMapping("/owner")
@CrossOrigin(origins = "*")
public class OwnerController {
    @Autowired
    private OwnerRepository ownerRepository;

    @PostMapping("/save")
    @Transactional
    public ResponseEntity<OwnerListRecord> save(@RequestBody @Valid OwnerRegisterRecord ownerRegisterRecord,
                                                UriComponentsBuilder uriComponentsBuilder) {
        if (ownerRepository.existsById(ownerRegisterRecord.ownerId())) {
            Owner owner = ownerRepository.getReferenceById(ownerRegisterRecord.ownerId());

            if(owner.getActive()) {
                throw new ValidationException("El propietario ya existe");
            }
        }

        Owner saved = ownerRepository.save(new Owner(ownerRegisterRecord));
        URI url = uriComponentsBuilder.path("/owner/{id}").buildAndExpand(saved.getOwnerId()).toUri();
        return ResponseEntity.created(url).body(new OwnerListRecord(saved));
    }


    @PutMapping("/update")
    @Transactional
    public ResponseEntity<OwnerListRecord> update(@RequestBody @Valid OwnerUpdateRecord ownerUpdateRecord) {
        Owner owner = ownerRepository.getReferenceById(ownerUpdateRecord.ownerId());

        if (owner != null && owner.getActive()) {
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
        }
        throw new ValidationException("No se encuentran los datos del propietario que desea actualizar");
    }

    @GetMapping("/all")
    public ResponseEntity<Page<OwnerListRecord>> getAll(@PageableDefault() Pageable pagination) {
        Page<Owner> all = ownerRepository.findAll(pagination);
        Page<OwnerListRecord> allPage = all.map(OwnerListRecord::new);
        return ResponseEntity.ok(allPage);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OwnerListRecord> getById(@PathVariable("id") Long ownerId) {
        Owner owner = ownerRepository.getReferenceById(ownerId);
        if(owner == null || !owner.getActive()){
            return ResponseEntity.notFound().build();
        }else {
            return ResponseEntity.ok(new OwnerListRecord(owner));
        }

    }

    @DeleteMapping("/delete/{id}")
    @Transactional
    public ResponseEntity<OwnerListRecord> delete(@PathVariable("id") Long ownerId) {
        Owner owner = ownerRepository.getReferenceById(ownerId);
        if (owner != null && owner.getActive()) {
            owner.setActive(false);
            ownerRepository.save(owner);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/ownerFirstName/{name}")
    public ResponseEntity<List<OwnerListRecord>> getByFirstName(@PathVariable("name") String ownerName) {
        List<Owner> owners = ownerRepository.getByOwnerFirstName(ownerName);
        if (!owners.isEmpty()) {
            List<OwnerListRecord> ownerRecordList = owners
                    .stream()
                    .map(OwnerListRecord::new)
                    .toList();
            return ResponseEntity.ok(ownerRecordList);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
