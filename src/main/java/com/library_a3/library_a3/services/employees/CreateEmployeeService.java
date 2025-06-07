package com.library_a3.library_a3.services.employees;

import com.library_a3.library_a3.domains.Credentials;
import com.library_a3.library_a3.domains.Employee;
import com.library_a3.library_a3.repositories.EmployeeRepository;
import com.library_a3.library_a3.services.CreateCredentialService;
import com.library_a3.library_a3.shared.dtos.CreateEmployeeDTO;
import com.library_a3.library_a3.shared.enums.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateEmployeeService {
    @Autowired
    private CreateCredentialService createCredentialService;
    @Autowired
    private EmployeeRepository employeeRepository;

    public Employee execute(CreateEmployeeDTO data) {
        Credentials credentials = this.createCredentialService.execute(data.getEmail(), data.getPass(), Role.EMPLOYEE);
        return this.employeeRepository.save(new Employee(data.getName(), data.getCategory(), credentials.getId()));
    }
}
