package com.library_a3.library_a3.repositories;

import com.library_a3.library_a3.domains.Borrow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BorrowRepository extends JpaRepository<Borrow, String> {
    List<Borrow> findByStudentId(String studentId);
    @Query("SELECT b "
            + "FROM borrow b "
            + "JOIN FETCH b.student "
            + "JOIN FETCH b.book")
    List<Borrow> findAllWithRelationship();
}