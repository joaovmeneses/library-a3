package com.library_a3.library_a3.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.library_a3.library_a3.domains.Employee;
import com.library_a3.library_a3.repositories.EmployeeRepository;
import com.library_a3.library_a3.services.TokenService;
import com.library_a3.library_a3.services.employees.CreateEmployeeService;
import com.library_a3.library_a3.shared.dtos.CreateEmployeeDTO;

import jakarta.servlet.http.HttpServletRequest;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/employees")

public class EmployeeController {
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private CreateEmployeeService createEmployeeService;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private HttpServletRequest request;

    @PostMapping()
    public Employee create(@Validated @RequestBody CreateEmployeeDTO body) {
        String token = request.getHeader("Authorization");
        String organizationId = tokenService.getOrganizationId(token.replace("Bearer ", " ")).asString();
        return this.createEmployeeService.execute(body);
    }

    @GetMapping()
    public List<Employee> getEmployees() {
        String token = request.getHeader("Authorization");
        return this.employeeRepository.getAllEmployeesOrganization(tokenService.getOrganizationId(token.replace("Bearer ", "")).asString());
    }
}