package co.com.cesde.arkham.controller;

import co.com.cesde.arkham.dto.user.UserListRecord;
import co.com.cesde.arkham.dto.user.UserUpdateRecord;
import co.com.cesde.arkham.entity.Role;
import co.com.cesde.arkham.entity.User;
import co.com.cesde.arkham.repository.UserRepository;
import jakarta.validation.Valid;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/auth/user")
public class UserController {
    @Autowired
    private UserRepository userRepository;


    @PutMapping
    public ResponseEntity<UserListRecord> update(UserUpdateRecord userUpdateRecord) {
        User user = userRepository.getReferenceById(userUpdateRecord.userId());

        if(user != null){
            if (userUpdateRecord.role() != null &&
                    !userUpdateRecord.role().isBlank()) {
                user.setRole(Role.valueOf(userUpdateRecord.role()));
            }

            if (userUpdateRecord.firstName() != null &&
                    !userUpdateRecord.firstName().isBlank()) {
                user.setUserFirstName(userUpdateRecord.firstName());
            }

            if (userUpdateRecord.lastName() != null &&
                    !userUpdateRecord.lastName().isBlank()) {
                user.setUserLastName(userUpdateRecord.lastName());
            }

            if (userUpdateRecord.phone() != null &&
                    !userUpdateRecord.phone().isBlank()) {
                user.setUserPhone(userUpdateRecord.phone());
            }

            User userUpdated = userRepository.save(user);
            return ResponseEntity.ok(new UserListRecord(userUpdated));
        }

        throw new ValidationException("No existe el usuario");

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<UserListRecord> delete(Long userId) {
        User user = userRepository.getReferenceById(userId);
        if (user != null && user.getActive()) {
            user.setActive(false);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
