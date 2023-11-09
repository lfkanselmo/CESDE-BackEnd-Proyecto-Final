package co.com.cesde.arkham.web.controller;

import co.com.cesde.arkham.domain.Administrator;
import co.com.cesde.arkham.domain.service.AdministratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/administrators")
public class AdministratorController {
    @Autowired
    private AdministratorService administratorService;

    @PostMapping("/save")
    public ResponseEntity<Administrator> save(@RequestBody Administrator administrator) {
        return new ResponseEntity<>(administratorService.save(administrator), HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable("id") Integer id) {
        if(administratorService.delete(id)){
            return new ResponseEntity<>(HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Administrator> getById(@PathVariable("id") Integer id) {
        return administratorService.getById(id)
                .map(administrator -> new ResponseEntity<>(administrator, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/firstName/{name}")
    public ResponseEntity<List<Administrator>> getByFirstName(@PathVariable("name") String name) {
        return administratorService.getByAdministratorFirstName(name)
                .map(administrator -> new ResponseEntity<>(administrator, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
