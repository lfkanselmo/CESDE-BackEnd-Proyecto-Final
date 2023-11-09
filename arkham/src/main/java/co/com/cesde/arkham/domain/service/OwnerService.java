package co.com.cesde.arkham.domain.service;

import co.com.cesde.arkham.domain.Owner;
import co.com.cesde.arkham.domain.repository.OwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OwnerService {
    @Autowired
    private OwnerRepository ownerRepository;

    public Owner save(Owner owner){
        return ownerRepository.save(owner);
    }

    public Optional<Owner> getById(Integer id){
        return ownerRepository.getById(id);
    }

    public Boolean delete(Integer id){
        return getById(id).map(owner ->{
            ownerRepository.delete(id);
            return true;
        }).orElse(false);
    }

    public Optional<List<Owner>> getByFirstName(String firstName){
        return ownerRepository.getByOwnerFirstName(firstName);
    }
}
