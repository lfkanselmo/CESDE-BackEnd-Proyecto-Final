package co.com.cesde.arkham.controller;

import co.com.cesde.arkham.dto.user.UserListRecord;
import co.com.cesde.arkham.dto.user.UserSearchRecord;
import co.com.cesde.arkham.dto.user.UserUpdateRecord;
import co.com.cesde.arkham.entity.Appointment;
import co.com.cesde.arkham.entity.Role;
import co.com.cesde.arkham.entity.User;
import co.com.cesde.arkham.infra.exception.NotFoundValidation;
import co.com.cesde.arkham.repository.AppointmentRepository;
import co.com.cesde.arkham.repository.UserRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/auth/user")
@CrossOrigin(origins = "*")
public class UserController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AppointmentRepository appointmentRepository;


    @PutMapping("/update")
    @Transactional
    public ResponseEntity<UserListRecord> update(UserUpdateRecord userUpdateRecord) {
        User user = userRepository.getReferenceById(userUpdateRecord.userId());

        if (user != null) {
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

    @GetMapping("/{id}")
    public ResponseEntity<UserListRecord> getById(@PathVariable("id") Long userId) throws NotFoundValidation {
        User user = userRepository.getReferenceById(userId);
        if(user == null){
            System.out.printf("No existe usuario con id: " + userId);
            throw new NotFoundValidation("No existe usuario con id: " + userId);
        }

        return ResponseEntity.ok(new UserListRecord(user));
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<UserListRecord> getByEmail(@PathVariable("email") String email) throws NotFoundValidation {
        User user = userRepository.findByUsername(email)
                .orElseThrow(() -> new NotFoundValidation("Usuario con email " + email + " no encontrado"));
        return ResponseEntity.ok(new UserListRecord(user));
    }

    @DeleteMapping("/delete/{id}")
    @Transactional
    public ResponseEntity<UserListRecord> delete(Long userId) {
        User user = userRepository.getReferenceById(userId);
        if (user != null && user.getActive()) {
            user.setActive(false);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<UserListRecord>> getAll(){
        List<User> all = userRepository.findAll();
        List<UserListRecord> list = all.stream().map(UserListRecord::new).toList();
        return ResponseEntity.ok(list);
    }

    @PostMapping("/search")
    public ResponseEntity<List<UserListRecord>> search(@RequestBody UserSearchRecord userSearchRecord) {
        List<User> allUser = userRepository.findAll();
        List<User> usersFounded = new ArrayList<User>();
        if(!allUser.isEmpty()){
            for (User user : allUser) {
                Appointment appointment = appointmentRepository.findAppointmentByDateAndStartTimeAndUserId(userSearchRecord.date(), userSearchRecord.startTime(), user.getUserId());
            }
            List<UserListRecord> list = usersFounded.stream().map(UserListRecord::new).toList();
            return ResponseEntity.ok(list);
        }

        throw new ValidationException("No hay administrador disponible para esta fecha y hora");
    }
}
