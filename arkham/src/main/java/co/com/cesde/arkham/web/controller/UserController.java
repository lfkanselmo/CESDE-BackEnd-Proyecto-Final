package co.com.cesde.arkham.web.controller;

import co.com.cesde.arkham.domain.User;
import co.com.cesde.arkham.domain.dto.user.UserListRecord;
import co.com.cesde.arkham.domain.dto.user.UserRegisterRecord;
import co.com.cesde.arkham.domain.dto.user.UserUpdateRecord;
import co.com.cesde.arkham.domain.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/save")
    public ResponseEntity save(@RequestBody @Valid UserRegisterRecord userRegisterRecord,
                               UriComponentsBuilder uriComponentsBuilder) {
        User user = userService.save(new User(userRegisterRecord));
        URI url = uriComponentsBuilder.path("/user/{id}").buildAndExpand(user.getUserId()).toUri();
        return ResponseEntity.created(url).body(new UserListRecord(user));
    }


    @PutMapping
    public ResponseEntity<UserListRecord> update(UserUpdateRecord userUpdateRecord) {
        Optional<User> userOptional = userService.getById(userUpdateRecord.userId());
        if (userOptional.isPresent()) {
            User user = userOptional.get();

            if (userUpdateRecord.rol() != null &&
                    !userUpdateRecord.rol().isBlank()) {
                user.setRol(userUpdateRecord.rol());
            }

            if (userUpdateRecord.firstName() != null &&
                    !userUpdateRecord.firstName().isBlank()) {
                user.setFirstName(userUpdateRecord.firstName());
            }

            if (userUpdateRecord.lastName() != null &&
                    !userUpdateRecord.lastName().isBlank()) {
                user.setLastName(userUpdateRecord.lastName());
            }

            if (userUpdateRecord.phone() != null &&
                    !userUpdateRecord.phone().isBlank()) {
                user.setPhone(userUpdateRecord.phone());
            }

            User userUpdated = userService.update(user);
            return ResponseEntity.ok(new UserListRecord(userUpdated));

        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<UserListRecord> delete(Long userId) {
        if (userService.existsById(userId)) {
            userService.delete(userId);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.noContent().build();
        }
    }
}
