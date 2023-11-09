package co.com.cesde.arkham.domain.service;

import co.com.cesde.arkham.domain.Location;
import co.com.cesde.arkham.domain.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LocationService {
    @Autowired
    private LocationRepository locationRepository;

    public Location save(Location location){
        return locationRepository.save(location);
    }

    public Optional<Location> getById(Integer id){
        return locationRepository.getById(id);
    }

    public Optional<List<Location>> getByDistrict(String district){
        return locationRepository.getByDistrict(district);
    }
}
