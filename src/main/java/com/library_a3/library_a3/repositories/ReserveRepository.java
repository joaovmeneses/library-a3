package com.library_a3.library_a3.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.library_a3.library_a3.shared.enums.reserve.ReservationStatusEnum;
import com.library_a3.library_a3.domains.Borrow;
import com.library_a3.library_a3.domains.Reserve;

public interface ReserveRepository extends JpaRepository<Reserve, String> {

    @Query("SELECT r "
            + "FROM reserve r "
            + "JOIN FETCH r.student "
            + "JOIN FETCH r.book")
    List<Reserve> findAllReserves();

    List<Reserve> findByStatus(ReservationStatusEnum status);

    @Query("SELECT r "
            + "FROM reserve r "
            + "JOIN FETCH r.student "
            + "JOIN FETCH r.book "
            + "WHERE r.studentId = :studentId"
    )
    List<Reserve> findByStudentId(String studentId);

    @Query(value = "SELECT r.id, r.book_id, r.student_id, r.organization_id, r.status, r.created_at, r.updated_at, r.date_to_return, r.deleted_at " +
            "FROM reserve r " +
            "WHERE r.organization_id = :organizationId AND b.deleted_at IS NULL",
            nativeQuery = true)
    List<Reserve> getAllReservesOrganization(String organizationId);
}
