package co.com.cesde.arkham.persistence;

import co.com.cesde.arkham.domain.District;
import co.com.cesde.arkham.domain.repository.DistrictRepository;
import co.com.cesde.arkham.persistence.crud.BarrioJpaRepository;
import co.com.cesde.arkham.persistence.entity.Barrio;
import co.com.cesde.arkham.persistence.mapper.DistrictMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class BarrioRepository implements DistrictRepository {
    private BarrioJpaRepository barrioJpaRepository;
    private DistrictMapper mapper;

    @Override
    public District create(District district) {
        Barrio barrio = mapper.toBarrio(district);
        barrioJpaRepository.save(barrio);
        return district;
    }

    @Override
    public void delete(Long districtId) {
       barrioJpaRepository.deleteById(districtId);
    }

    @Override
    public Optional<District> getById(Long districtId) {
        Optional<Barrio> barrio = barrioJpaRepository.findById(districtId);
        return barrio.map(b -> mapper.toDistrict(b));
    }

    @Override
    public List<District> getAll() {
        List<Barrio> barrios = barrioJpaRepository.findAll();
        return mapper.toDistricts(barrios);
    }

    @Override
    public Optional<List<District>> getByDistrictName(String districtName) {
        List<Barrio> barrios = barrioJpaRepository.findByNombreBarrio(districtName);
        return Optional.of(mapper.toDistricts(barrios));
    }
}
