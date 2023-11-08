package co.com.cesde.arkham.domain.repository;

import co.com.cesde.arkham.domain.District;

import java.util.List;
import java.util.Optional;

public interface DistrictRepository {
    District save(District district);

    void delete(Long districtId);

    Optional<District> getById(Long districtId);

    List<District> getAll();

    Optional<List<District>> getByDistrictName(String districtName);
}
