package co.com.cesde.arkham.domain.service;

import co.com.cesde.arkham.domain.User;
import co.com.cesde.arkham.domain.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Transactional
    public User save(User user) {
        return userRepository.save(user);
    }

    public Optional<User> getById(Long userId){
        return userRepository.getByUserId(userId);
    }

    @Transactional
    public void delete(Long userId){
        Optional<User> userOptional = userRepository.getByUserId(userId);
        if(userOptional.isPresent()){
            User user = userOptional.get();
            user.setActive(false);
            userRepository.save(user);
        }
    }

    public Optional<User> getByUser(String userEmail){
        return userRepository.getByUsername(userEmail);
    }

    @Transactional
    public User update(User user) {
        return userRepository.save(user);
    }

    public boolean existsById(Long userId) {
        return userRepository.existsById(userId);
    }
}
