package co.com.cesde.arkham.domain.repository;


import co.com.cesde.arkham.domain.Administrator;

import java.util.List;
import java.util.Optional;

public interface AdministratorRepository {
    Administrator create(Administrator administrator);

    Administrator update(Administrator administrator);

    void delete(Long administratorId);

    Optional<List<Administrator>> getByAdministratorFirstName(String administratorFirstName);
}
