package com.library_a3.library_a3.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.library_a3.library_a3.domains.Student;

public interface StudentRespository extends JpaRepository<Student, String> {
    Student findByPhone(String phone);
    Student findByCpf(String cpf);
    Optional<Student> getByCredentialId(String credentialId);

    List<Student> findAllByDeletedAtIsNull();
    List<Student> findAllByDeletedAtIsNotNull();

    @Query(value = "SELECT * FROM student e WHERE e.organization_id = :organizationId AND e.deleted_at IS NULL", nativeQuery= true)
    List<Student> getAllStudentsOrganization(String organizationId);
}
