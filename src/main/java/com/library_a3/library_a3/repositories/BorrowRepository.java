package com.library_a3.library_a3.repositories;

import com.library_a3.library_a3.domains.Borrow;

import com.library_a3.library_a3.shared.enums.borrows.BorrowStatusEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface BorrowRepository extends JpaRepository<Borrow, String> {
    @Query("SELECT b "
            + "FROM borrow b "
            + "JOIN FETCH b.student "
            + "JOIN FETCH b.book "
            + "WHERE b.studentId = :studentId"
    )
    List<Borrow> findByStudentId(String studentId);
    @Query("SELECT b "
            + "FROM borrow b "
            + "JOIN FETCH b.student "
            + "JOIN FETCH b.book")
    List<Borrow> findAllWithRelationship();
    
    @Query("SELECT b "
        + "FROM borrow b "
        + "JOIN FETCH b.student "
        + "JOIN FETCH b.book "
        + "WHERE b.id = :id")
    Optional<Borrow> findByIdWithRelationship(String id);

    @Query(
            "SELECT COUNT(*) "
            + "FROM borrow b "
            + "where b.status = :status "
            + "and b.studentId = :studentId"
    )
    Integer countByStudentIdAndStatus(BorrowStatusEnum status, String studentId);
}
