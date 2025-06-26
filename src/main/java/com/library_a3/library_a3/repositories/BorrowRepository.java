package com.library_a3.library_a3.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.library_a3.library_a3.domains.Borrow;
import com.library_a3.library_a3.shared.enums.borrows.BorrowStatusEnum;

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


    @Query(value = "SELECT b.id AS id, b.book_id, b.student_id, b.organization_id, b.status, b.created_at, b.updated_at, b.date_to_return, b.deleted_at " +
            "FROM borrow b " +
            "WHERE b.organization_id = :organizationId AND b.deleted_at IS NULL",
            nativeQuery = true)
    List<Borrow> getAllBorrowsOrganization(String organizationId);
}