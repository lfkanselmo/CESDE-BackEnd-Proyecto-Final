package co.com.cesde.arkham.domain.service;

import co.com.cesde.arkham.domain.Owner;
import co.com.cesde.arkham.domain.repository.OwnerRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OwnerService {
    @Autowired
    private OwnerRepository ownerRepository;

    @Transactional
    public Owner save(Owner owner) {
        return ownerRepository.save(owner);
    }

    public Optional<Owner> getById(Long ownerId){
        return ownerRepository.getByOwnerId(ownerId);
    }

    @Transactional
    public void delete(Long ownerId){
        Optional<Owner> ownerOptional = ownerRepository.getByOwnerId(ownerId);
        if(ownerOptional.isPresent()){
            Owner owner = ownerOptional.get();
            owner.setActive(false);
            ownerRepository.save(owner);
        }
    }

    public List<Owner> getByFirstName(String firstName){
        return ownerRepository.getByOwnerFirstName(firstName);
    }

    public Page<Owner> getAll(Pageable pagination) {
        return ownerRepository.getAll(pagination);
    }

    @Transactional
    public Owner update(Owner owner) {
        return ownerRepository.save(owner);
    }

    public boolean existsById(Long ownerId) {
        return ownerRepository.existsById(ownerId);
    }
}
