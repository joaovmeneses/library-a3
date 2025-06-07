package com.library_a3.library_a3.repositories;

import com.library_a3.library_a3.domains.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, String> {
    Optional<Employee> getByCredentialId (String credentialId);
}
