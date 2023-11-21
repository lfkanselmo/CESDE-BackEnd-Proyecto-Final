package co.com.cesde.arkham.domain.controller;

import co.com.cesde.arkham.domain.dto.user.UserListRecord;
import co.com.cesde.arkham.domain.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("auth/admin/users")
public class UserController {
    @Autowired
    private UserService userService;


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
