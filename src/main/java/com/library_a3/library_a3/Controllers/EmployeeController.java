package com.library_a3.library_a3.Controllers;

import com.library_a3.library_a3.domains.Employee;
import com.library_a3.library_a3.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping()

public class RoleController {
    @Autowired
    private RoleRepository roleRepository;

    @GetMapping()
    public List<Employee> getEmployees() {
        return this.roleRepository.findAll();
    }
}