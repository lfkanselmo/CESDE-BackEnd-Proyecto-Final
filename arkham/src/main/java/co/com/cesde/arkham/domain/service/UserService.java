package co.com.cesde.arkham.domain.service;

import co.com.cesde.arkham.domain.User;
import co.com.cesde.arkham.domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public Optional<User> save(User user){
        return userRepository.save(user);
    }

    public Optional<User> getById(Integer id){
        return userRepository.getByUserId(id);
    }

    public Boolean delete(Integer id){
        return getById(id).map(user -> {
            userRepository.delete(id);
            return true;
        }).orElse(false);
    }

    public Optional<User> getByUser(String user){
        return userRepository.getByUser(user);
    }

}
