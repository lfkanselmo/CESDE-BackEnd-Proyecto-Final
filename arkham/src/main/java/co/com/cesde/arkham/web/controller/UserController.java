package co.com.cesde.arkham.web.controller;

import co.com.cesde.arkham.domain.User;
import co.com.cesde.arkham.domain.dto.user.UserRegisterRecord;
import co.com.cesde.arkham.domain.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/save")
    public ResponseEntity save(@RequestBody @Valid UserRegisterRecord userRegisterRecord) {
        if(userService.save(new User(userRegisterRecord)).isPresent() ){
            return new ResponseEntity<>(HttpStatus.CREATED);
        } else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(Integer id) {
        if (userService.delete(id)) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
