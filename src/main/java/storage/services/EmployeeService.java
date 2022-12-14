package storage.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import storage.models.Employee;
import storage.repositories.EmployeeRepository;

import java.util.Optional;

@Service
public class EmployeeService implements UserDetailsService {

    private static EmployeeRepository employeeRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.employeeRepository = employeeRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return employeeRepository.findEmployeeByUsername(username);
    }

    public String newEmployee(Employee employee){
        String encodedPassword =  bCryptPasswordEncoder
                .encode(employee.getPassword());
        employee.setPassword(encodedPassword);
        employeeRepository.save(employee);
        return "New employee added successfully";
    }

    public Optional<Employee> findEmployeeByUserName(String username){
        return Optional.ofNullable(employeeRepository.findEmployeeByUsername(username));
    }

    public String deleteEmployee(String name, String surname){
        employeeRepository.delete(employeeRepository.findEmployeeByNameAndSurname(name,surname));
        return "Employee deleted successfully";
    }
}
