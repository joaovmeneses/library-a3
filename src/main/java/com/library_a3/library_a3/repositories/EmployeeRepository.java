package com.library_a3.library_a3.repositories;

import com.library_a3.library_a3.domains.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Employee, String> {
    Employee findByName(String name);
    Employee findByRole(String role);
}