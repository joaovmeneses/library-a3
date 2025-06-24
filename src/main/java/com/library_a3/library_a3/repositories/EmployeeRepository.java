package com.library_a3.library_a3.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.library_a3.library_a3.domains.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, String> {
    Optional<Employee> getByCredentialId (String credentialId);

    @Query(value = "SELECT * FROM employee e WHERE e.organization_id = :organizationId AND e.deleted_at IS NULL", nativeQuery= true)
    List<Employee> getAllEmployeesOrganization(String organizationId);
}
