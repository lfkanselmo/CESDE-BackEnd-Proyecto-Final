package co.com.cesde.arkham.web.controller;

import co.com.cesde.arkham.domain.User;
import co.com.cesde.arkham.domain.service.UserService;
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
    public ResponseEntity<User> save(@RequestBody User user){
        return userService.save(user)
                .map(userOptional -> new ResponseEntity<>(userOptional,HttpStatus.CREATED))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(Integer id){
       if(userService.delete(id)){
            return new ResponseEntity<>(HttpStatus.OK);
        } else{
           return new ResponseEntity<>(HttpStatus.NOT_FOUND);
       }
    }
}
