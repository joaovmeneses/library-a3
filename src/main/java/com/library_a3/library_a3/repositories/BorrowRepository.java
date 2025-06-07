package com.library_a3.library_a3.repositories;

import com.library_a3.library_a3.domains.Borrow;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BorrowRepository extends JpaRepository<Borrow, String> {
    List<Borrow> findByStudentId(String studentId);
}