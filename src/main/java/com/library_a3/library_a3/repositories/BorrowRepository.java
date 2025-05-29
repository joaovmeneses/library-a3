package com.library_a3.library_a3.repositories;

import com.library_a3.library_a3.domains.Borrow;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BorrowRepository extends JpaRepository<Borrow, String> {
}
