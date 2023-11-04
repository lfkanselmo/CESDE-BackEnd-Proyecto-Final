package co.com.cesde.arkham.domain.repository;


import co.com.cesde.arkham.domain.Administrator;

import java.util.List;

public interface AdministratorRepository {
    void create(Administrator administrator);

    void update(Administrator administrator);

    void delete(Long administratorId);

    List<Administrator> getByAdministratorFirstName(String administratorFirstName);
}
