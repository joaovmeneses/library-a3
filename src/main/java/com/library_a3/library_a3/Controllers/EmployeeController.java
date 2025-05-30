package com.library_a3.library_a3.Controllers;

import com.library_a3.library_a3.domains.Employee;
import com.library_a3.library_a3.repositories.EmployeeRepository;
import com.library_a3.library_a3.shared.dtos.CreateEmployeeDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")

public class EmployeeController {
    @Autowired
    private EmployeeRepository employeeRepository;

    @PostMapping()
    public Employee create(@RequestBody CreateEmployeeDTO body) {
        Employee employee = new Employee(body.getName(),body.getCategory(), "qualquervalor");
        return this.employeeRepository.save(employee);
    }

    @GetMapping()
    public List<Employee> getEmployees() {
        return this.employeeRepository.findAll();
    }
}