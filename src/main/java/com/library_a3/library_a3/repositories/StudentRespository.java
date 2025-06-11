package com.library_a3.library_a3.repositories;

import com.library_a3.library_a3.domains.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface StudentRespository extends JpaRepository<Student, String> {
    Student findByPhone(String phone);
    Student findByCpf(String cpf);
    Optional<Student> getByCredentialId(String credentialId);

    List<Student> findAllByDeletedAtIsNull();
    List<Student> findAllByDeletedAtIsNotNull();
}
