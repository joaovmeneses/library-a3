package com.library_a3.library_a3.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.library_a3.library_a3.domains.Reserve;

public interface ReserveRepository extends JpaRepository<Reserve, String> {

    @Query("SELECT r "
            + "FROM reserve r "
            + "JOIN FETCH r.student "
            + "JOIN FETCH r.book")
    List<Reserve> findAllReserves();

}
