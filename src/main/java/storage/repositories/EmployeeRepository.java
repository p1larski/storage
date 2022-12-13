package storage.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import storage.models.Employee;

import java.util.Optional;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Long> {
    Employee findEmployeeByNameAndSurname(String name, String surname);
    Employee findEmployeeByUsername(String username);
}
