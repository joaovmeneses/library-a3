package com.library_a3.library_a3.Controllers;

import com.library_a3.library_a3.domains.Employee;
import com.library_a3.library_a3.repositories.EmployeeRepository;
import com.library_a3.library_a3.services.employees.CreateEmployeeService;
import com.library_a3.library_a3.shared.dtos.CreateEmployeeDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/employees")

public class EmployeeController {
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private CreateEmployeeService createEmployeeService;

    @PostMapping()
    public Employee create(@RequestBody CreateEmployeeDTO body) {
        return this.createEmployeeService.execute(body);
    }

    @GetMapping()
    public List<Employee> getEmployees() {
        return this.employeeRepository.findAll();
    }
}