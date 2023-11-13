package co.com.cesde.arkham.web.controller;

import co.com.cesde.arkham.domain.Owner;
import co.com.cesde.arkham.domain.dto.owner.OwnerListRecord;
import co.com.cesde.arkham.domain.dto.owner.OwnerRegisterRecord;
import co.com.cesde.arkham.domain.service.OwnerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/owner")
public class OwnerController {
    @Autowired
    private OwnerService ownerService;

    @PostMapping("/save")
    public ResponseEntity save(@RequestBody @Valid OwnerRegisterRecord ownerRegisterRecord) {
        if(ownerService.save(new Owner(ownerRegisterRecord)).isPresent()){
            return new ResponseEntity<>(HttpStatus.CREATED);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @GetMapping
    public ResponseEntity<Page<Owner>> getAll(@PageableDefault(size = 10) Pageable pagination){
        return ownerService.getAll(pagination)
                .map(owners -> new ResponseEntity<>(owners,HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/{id}")
    public ResponseEntity<OwnerListRecord> getById(@PathVariable("id") Integer id) {
        return ownerService.getById(id)
                .map(owner -> new ResponseEntity<>(new OwnerListRecord(owner), HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable("id") Integer id) {
        if (ownerService.delete(id)) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/clientFirstName/{name}")
    public ResponseEntity<List<OwnerListRecord>> getByFirstName(@PathVariable("name") String name) {
        Optional<List<Owner>> ownersOptional = ownerService.getByFirstName(name);
        if (ownersOptional.isPresent()) {
            List<Owner> ownerList = ownersOptional.get();
            List<OwnerListRecord> ownerRecordList = ownerList
                    .stream()
                    .map(OwnerListRecord::new)
                    .toList();
            return new ResponseEntity<>(ownerRecordList, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
