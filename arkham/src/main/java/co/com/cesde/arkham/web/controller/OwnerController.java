package co.com.cesde.arkham.web.controller;

import co.com.cesde.arkham.domain.Owner;
import co.com.cesde.arkham.domain.service.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/owner")
public class OwnerController {
    @Autowired
    private OwnerService ownerService;

    @PostMapping("/save")
    public ResponseEntity<Owner> save(@RequestBody Owner owner){
        return ownerService.save(owner).map(ownerOptional -> new ResponseEntity<>(ownerOptional,HttpStatus.CREATED))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Owner> getById(@PathVariable("id") Integer id){
        return ownerService.getById(id)
                .map(owner -> new ResponseEntity(owner,HttpStatus.OK))
                .orElse(new ResponseEntity(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable("id") Integer id){
        if(ownerService.delete(id)){
            return new ResponseEntity<>(HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/firstName/{name}")
    public ResponseEntity<List<Owner>> getByFirstName(@PathVariable("name") String name){
        return ownerService.getByFirstName(name)
                .map(owners -> new ResponseEntity(owners,HttpStatus.OK))
                .orElse(new ResponseEntity(HttpStatus.NOT_FOUND));
    }
}
