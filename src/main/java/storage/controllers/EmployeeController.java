package storage.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import storage.models.Employee;
import storage.services.EmployeeService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class EmployeeController {

    private EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }
    @PostMapping("/employee/new")
    public String newEmployee(@RequestBody Employee employee){
        employeeService.newEmployee(employee);
        return "Employee added successfully";
    }
}
