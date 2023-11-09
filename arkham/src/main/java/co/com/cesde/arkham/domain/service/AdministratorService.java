package co.com.cesde.arkham.domain.service;

import co.com.cesde.arkham.domain.Administrator;
import co.com.cesde.arkham.domain.repository.AdministratorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdministratorService {
    @Autowired
    private AdministratorRepository administratorRepository;

    public Administrator save(Administrator administrator) {
        return administratorRepository.save(administrator);
    }

    public Optional<Administrator> getById(Integer id) {
        return administratorRepository.getById(id);
    }

    public Boolean delete(Integer id) {
        return getById(id).map(administrator -> {
            administratorRepository.delete(id);
            return true;
        }).orElse(false);
    }

    public Optional<List<Administrator>> getByAdministratorFirstName(String administratorFirstName){
        return administratorRepository.getByAdministratorFirstName(administratorFirstName);
    }
}
