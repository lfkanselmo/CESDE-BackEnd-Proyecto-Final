package co.com.cesde.arkham.domain.repository;

import co.com.cesde.arkham.domain.District;

import java.util.List;
import java.util.Optional;

public interface DistrictRepository {
    void create(District district);

    void delete(Long districtId);

    Optional<District> getById(Long districtId);

    List<District> getAll();

    List<District> getByDistrictName(String districtName);
}
