package com.library_a3.library_a3.repositories;

import com.library_a3.library_a3.domains.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRespository extends JpaRepository<Student, String> {

}
